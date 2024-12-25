package com.flightcoordinator.server.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AlgorithmRunDTO {
  private String id;
  private String algorithmName;
  private Date startTime;
  private Date endTime;
  private Long runtimeInMilliseconds;
  private String parametersJson;
  private String resourcesJson;
  private Map<String, Boolean> constraintsMet;
  private List<String> logs;
  private Boolean isSuccessful;
  private String failureReason;
  private Boolean isResultsSaved;
  private String resultId;

  public AlgorithmRunDTO() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAlgorithmName() {
    return algorithmName;
  }

  public void setAlgorithmName(String algorithmName) {
    this.algorithmName = algorithmName;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Long getRuntimeInMilliseconds() {
    return runtimeInMilliseconds;
  }

  public void setRuntimeInMilliseconds(Long runtimeInMilliseconds) {
    this.runtimeInMilliseconds = runtimeInMilliseconds;
  }

  public String getParametersJson() {
    return parametersJson;
  }

  public void setParametersJson(String parametersJson) {
    this.parametersJson = parametersJson;
  }

  public String getResourcesJson() {
    return resourcesJson;
  }

  public void setResourcesJson(String resourcesJson) {
    this.resourcesJson = resourcesJson;
  }

  public Map<String, Boolean> getConstraintsMet() {
    return constraintsMet;
  }

  public void setConstraintsMet(Map<String, Boolean> constraintsMet) {
    this.constraintsMet = constraintsMet;
  }

  public List<String> getLogs() {
    return logs;
  }

  public void setLogs(List<String> logs) {
    this.logs = logs;
  }

  public Boolean getIsSuccessful() {
    return isSuccessful;
  }

  public void setIsSuccessful(Boolean isSuccessful) {
    this.isSuccessful = isSuccessful;
  }

  public String getFailureReason() {
    return failureReason;
  }

  public void setFailureReason(String failureReason) {
    this.failureReason = failureReason;
  }

  public Boolean getIsResultsSaved() {
    return isResultsSaved;
  }

  public void setIsResultsSaved(Boolean isResultsSaved) {
    this.isResultsSaved = isResultsSaved;
  }

  public String getResultId() {
    return resultId;
  }

  public void setResultId(String resultId) {
    this.resultId = resultId;
  }
}
