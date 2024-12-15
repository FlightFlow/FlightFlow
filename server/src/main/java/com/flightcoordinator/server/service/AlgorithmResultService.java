package com.flightcoordinator.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightcoordinator.server.entity.AlgorithmResultEntity;
import com.flightcoordinator.server.exception.AppError;
import com.flightcoordinator.server.repository.AlgorithmResultRepository;

@Service
public class AlgorithmResultService {
  @Autowired
  private AlgorithmResultRepository algorithmResultRepository;

  public AlgorithmResultEntity getSingleAlgorithmResultById(String algorithmResultId) {
    Optional<AlgorithmResultEntity> algorithmResult = algorithmResultRepository.findById(algorithmResultId);
    return algorithmResult.orElseThrow(() -> new AppError(
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        HttpStatus.NOT_FOUND.value()));
  }

  public List<AlgorithmResultEntity> getMultipleAlgorithmResultsById(List<String> algorithmResultIds) {
    List<AlgorithmResultEntity> algorithmResults = algorithmResultRepository.findAllById(algorithmResultIds);
    if (algorithmResults.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return algorithmResults;
  }

  public List<AlgorithmResultEntity> getAllAlgorithmResults() {
    List<AlgorithmResultEntity> algorithmResults = algorithmResultRepository.findAll();
    if (algorithmResults.isEmpty()) {
      throw new AppError(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
    return algorithmResults;
  }

  public void createAlgorithmResult(AlgorithmResultEntity newAlgorithmResult) {
    algorithmResultRepository.save(newAlgorithmResult);
  }

  public void deleteAlgorithmResult(String algorithmResultId) {
    AlgorithmResultEntity existingAlgorithmResult = getSingleAlgorithmResultById(algorithmResultId);
    algorithmResultRepository.delete(existingAlgorithmResult);
  }

  public Boolean doesSingleAlgorithmResultExist(AlgorithmResultEntity algorithmResult) {
    String algorithmResultId = algorithmResult.getId();
    Optional<AlgorithmResultEntity> algorithmResultFound = algorithmResultRepository.findById(algorithmResultId);
    return algorithmResultFound.isPresent();
  }

  public Boolean doesMultipleAlgorithmResultsExist(List<AlgorithmResultEntity> algorithmResults) {
    List<String> algorithmResultIds = new ArrayList<>();
    algorithmResults.forEach(algorithmResult -> algorithmResultIds.add(algorithmResult.getId()));
    List<AlgorithmResultEntity> algorithmResultsFound = algorithmResultRepository.findAllById(algorithmResultIds);
    if (algorithmResults.size() != algorithmResultsFound.size()) {
      return false;
    }
    return algorithmResultsFound.isEmpty();
  }
}
