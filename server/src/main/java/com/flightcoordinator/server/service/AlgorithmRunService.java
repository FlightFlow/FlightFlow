package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.AlgorithmRunEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AlgorithmRunRepository;

@Service
public class AlgorithmRunService {
  @Autowired
  private AlgorithmRunRepository algorithmRunRepository;

  public AlgorithmRunEntity getSingleAlgorithmRunById(String algorithmRunId) {
    Optional<AlgorithmRunEntity> algorithmRun = algorithmRunRepository.findById(algorithmRunId);
    return algorithmRun.orElseThrow(() -> new AppError(
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        HttpStatus.NOT_FOUND.value()));
  }

  public List<AlgorithmRunEntity> getMultipleAlgorithmRunsById(List<String> algorithmRunIds) {
    List<AlgorithmRunEntity> algorithmRuns = algorithmRunRepository.findAllById(algorithmRunIds);
    if (algorithmRuns.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return algorithmRuns;
  }

  public List<AlgorithmRunEntity> getAllAlgorithmRuns() {
    List<AlgorithmRunEntity> algorithmRuns = algorithmRunRepository.findAll();
    if (algorithmRuns.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return algorithmRuns;
  }

  public void createAlgorithmRun(AlgorithmRunEntity newAlgorithmRun) {
    algorithmRunRepository.save(newAlgorithmRun);
  }

  public void updateAlgorithmRun(String algorithmRunId, AlgorithmRunEntity updatedAlgorithmRun) {
    AlgorithmRunEntity existingAlgorithmRun = getSingleAlgorithmRunById(algorithmRunId);

    existingAlgorithmRun.setAlgorithmName(updatedAlgorithmRun.getAlgorithmName());
    existingAlgorithmRun.setStartTime(updatedAlgorithmRun.getStartTime());
    existingAlgorithmRun.setEndTime(updatedAlgorithmRun.getEndTime());
    existingAlgorithmRun.setRuntimeInMilliseconds(updatedAlgorithmRun.getRuntimeInMilliseconds());
    existingAlgorithmRun.setParameters(updatedAlgorithmRun.getParameters());
    existingAlgorithmRun.setResourcesUsed(updatedAlgorithmRun.getResourcesUsed());
    existingAlgorithmRun.setConstrainsMet(updatedAlgorithmRun.getConstrainsMet());
    existingAlgorithmRun.setLogs(updatedAlgorithmRun.getLogs());
    existingAlgorithmRun.setSuccessful(updatedAlgorithmRun.isSuccessful());
    existingAlgorithmRun.setFailureReason(updatedAlgorithmRun.getFailureReason());
    existingAlgorithmRun.setResultsSaved(updatedAlgorithmRun.isResultsSaved());
    existingAlgorithmRun.setResult(updatedAlgorithmRun.getResult());

    algorithmRunRepository.save(existingAlgorithmRun);
  }

  public void deleteAlgorithmRun(String algorithmRunId) {
    AlgorithmRunEntity existingAlgorithmRun = getSingleAlgorithmRunById(algorithmRunId);
    algorithmRunRepository.delete(existingAlgorithmRun);
  }

  public Boolean doesSingleAlgorithmRunExist(AlgorithmRunEntity algorithmRun) {
    String algorithmRunId = algorithmRun.getId();
    Optional<AlgorithmRunEntity> algorithmRunFound = algorithmRunRepository.findById(algorithmRunId);
    return algorithmRunFound.isPresent();
  }

  public Boolean doesMultipleAlgorithmRunsExist(List<AlgorithmRunEntity> algorithmRuns) {
    List<String> algorithmRunIds = new ArrayList<>();
    algorithmRuns.forEach(algorithmRun -> algorithmRunIds.add(algorithmRun.getId()));
    List<AlgorithmRunEntity> algorithmRunsFound = algorithmRunRepository.findAllById(algorithmRunIds);
    if (algorithmRuns.size() != algorithmRunsFound.size()) {
      return false;
    }
    return algorithmRunsFound.isEmpty();
  }
}
