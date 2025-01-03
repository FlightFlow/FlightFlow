package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.dto.AlgorithmRunDTO;
import com.flightcoordinator.server.dto.EntityIdDTO;
import com.flightcoordinator.server.entity.AlgorithmResultEntity;
import com.flightcoordinator.server.entity.AlgorithmRunEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AlgorithmResultRepository;
import com.flightcoordinator.server.repository.AlgorithmRunRepository;
import com.flightcoordinator.server.utils.ObjectMapper;

@Service
public class AlgorithmRunService {
  @Autowired
  private AlgorithmRunRepository algorithmRunRepository;

  @Autowired
  private AlgorithmResultRepository algorithmResultRepository;

  public AlgorithmRunDTO getSingleAlgorithmRunById(EntityIdDTO entityIdDTO) {
    AlgorithmRunEntity algorithmRun = algorithmRunRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.algorithmRun", HttpStatus.NOT_FOUND.value()));
    AlgorithmRunDTO algorithmRunDTO = ObjectMapper.toAlgorithmRunDTO(algorithmRun);
    return algorithmRunDTO;
  }

  public List<AlgorithmRunDTO> getMultipleAlgorithmRunsById(List<EntityIdDTO> entityIdDTOs) {
    List<AlgorithmRunEntity> algorithmRuns = algorithmRunRepository.findAllById(
        entityIdDTOs.stream().map(entityId -> entityId.getId()).collect(Collectors.toList()));
    if (algorithmRuns.isEmpty()) {
      throw new AppError("notFound.algorithmRun", HttpStatus.NOT_FOUND.value());
    }
    List<AlgorithmRunDTO> algorithmRunDTOs = algorithmRuns.stream().map(ObjectMapper::toAlgorithmRunDTO)
        .collect(Collectors.toList());
    return algorithmRunDTOs;
  }

  public List<AlgorithmRunDTO> getAllAlgorithmRuns() {
    List<AlgorithmRunEntity> algorithmRuns = algorithmRunRepository.findAll();
    if (algorithmRuns.isEmpty()) {
      throw new AppError("notFound.algorithmRun", HttpStatus.NOT_FOUND.value());
    }
    List<AlgorithmRunDTO> algorithmRunDTOs = algorithmRuns.stream().map(ObjectMapper::toAlgorithmRunDTO)
        .collect(Collectors.toList());
    return algorithmRunDTOs;
  }

  public void triggerAlgorithmRun(String algorithmName) {
    // TODO
  }

  public void createAlgorithmRun(AlgorithmRunDTO newAlgorithmRunDTO) {
    AlgorithmResultEntity algorithmResultEntity = algorithmResultRepository.findById(newAlgorithmRunDTO.getResultId())
        .orElseThrow(() -> new AppError("genericMessages.badRequest", HttpStatus.BAD_REQUEST.value()));

    AlgorithmRunEntity algorithmRunEntity = new AlgorithmRunEntity();
    algorithmRunEntity.setAlgorithmName(newAlgorithmRunDTO.getAlgorithmName());
    algorithmRunEntity.setStartTime(newAlgorithmRunDTO.getStartTime());
    algorithmRunEntity.setEndTime(newAlgorithmRunDTO.getEndTime());
    algorithmRunEntity.setRuntimeInMilliseconds(newAlgorithmRunDTO.getRuntimeInMilliseconds());
    algorithmRunEntity.setParametersJson(newAlgorithmRunDTO.getParametersJson());
    algorithmRunEntity.setResourcesJson(newAlgorithmRunDTO.getResourcesJson());
    algorithmRunEntity.setConstraintsMet(newAlgorithmRunDTO.getConstraintsMet());
    algorithmRunEntity.setLogs(newAlgorithmRunDTO.getLogs());
    algorithmRunEntity.setSuccessful(newAlgorithmRunDTO.getIsSuccessful());
    algorithmRunEntity.setFailureReason(newAlgorithmRunDTO.getFailureReason());
    algorithmRunEntity.setResultsSaved(newAlgorithmRunDTO.getIsResultsSaved());
    algorithmRunEntity.setResult(algorithmResultEntity);

    algorithmRunRepository.save(algorithmRunEntity);
  }

  public void deleteAlgorithmRun(EntityIdDTO entityIdDTO) {
    AlgorithmRunEntity existingAlgorithmRun = algorithmRunRepository.findById(entityIdDTO.getId())
        .orElseThrow(() -> new AppError("notFound.algorithmRun", HttpStatus.NOT_FOUND.value()));
    algorithmRunRepository.delete(existingAlgorithmRun);
  }

  public Boolean doesSingleAlgorithmRunExist(AlgorithmRunEntity algorithmRun) {
    String id = algorithmRun.getId();
    Optional<AlgorithmRunEntity> algorithmRunFound = algorithmRunRepository.findById(id);
    return algorithmRunFound.isPresent();
  }

  public Boolean doesMultipleAlgorithmRunsExist(List<AlgorithmRunEntity> algorithmRuns) {
    List<String> ids = new ArrayList<>();
    algorithmRuns.forEach(algorithmRun -> ids.add(algorithmRun.getId()));
    List<AlgorithmRunEntity> algorithmRunsFound = algorithmRunRepository.findAllById(ids);
    if (algorithmRuns.size() != algorithmRunsFound.size()) {
      return false;
    }
    return algorithmRunsFound.isEmpty();
  }
}
