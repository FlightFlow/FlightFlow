package com.flightcoordinator.server.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "algorithm_runs")
public class AlgorithmRunsEntity {
  @Id
  private String id;

  @Field("algorithm_name")
  @NotBlank(message = "Algorithm name cannot be blank")
  private String algorithmName;

  @Field("start_time")
  @NotBlank(message = "Start time cannot be blank")
  private Date startTime;

  @Field("end_time")
  @NotBlank(message = "End time cannot be blank")
  private Date endTime;

  @Field("runtime_in_milliseconds")
  @NotBlank(message = "Runtime in ms cannot be blank")
  private long runtimeInMilliseconds;

  @Field("parameters")
  @NotBlank(message = "Parameters cannot be blank")
  private Map<String, Object> parameters;

  @Field("resources_met")
  @NotBlank(message = "Resources used cannot be blank")
  private Map<String, Object> resourcesUsed;

  @Field("constrains_met")
  @NotBlank(message = "Constraints met cannot be blank")
  private Map<String, Boolean> constrainsMet;

  @Field("logs")
  private List<String> logs;

  @Field("is_successful")
  private boolean isSuccessful;

  @Field("failure_reason")
  private String failureReason;

  @Field("is_results_saved")
  private boolean isResultsSaved;

  @Field("result_id")
  private String resultId;

  public AlgorithmRunsEntity() {
  }

  public AlgorithmRunsEntity(String id, @NotBlank(message = "Algorithm name cannot be blank") String algorithmName,
      @NotBlank(message = "Start time cannot be blank") Date startTime,
      @NotBlank(message = "End time cannot be blank") Date endTime,
      @NotBlank(message = "Runtime in ms cannot be blank") long runtimeInMilliseconds,
      @NotBlank(message = "Parameters cannot be blank") Map<String, Object> parameters,
      @NotBlank(message = "Resources used cannot be blank") Map<String, Object> resourcesUsed,
      @NotBlank(message = "Constraints met cannot be blank") Map<String, Boolean> constrainsMet, List<String> logs,
      boolean isSuccessful, String failureReason, boolean isResultsSaved, String resultId) {
    this.id = id;
    this.algorithmName = algorithmName;
    this.startTime = startTime;
    this.endTime = endTime;
    this.runtimeInMilliseconds = runtimeInMilliseconds;
    this.parameters = parameters;
    this.resourcesUsed = resourcesUsed;
    this.constrainsMet = constrainsMet;
    this.logs = logs;
    this.isSuccessful = isSuccessful;
    this.failureReason = failureReason;
    this.isResultsSaved = isResultsSaved;
    this.resultId = resultId;
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

  public long getRuntimeInMilliseconds() {
    return runtimeInMilliseconds;
  }

  public void setRuntimeInMilliseconds(long runtimeInMilliseconds) {
    this.runtimeInMilliseconds = runtimeInMilliseconds;
  }

  public Map<String, Object> getParameters() {
    return parameters;
  }

  public void setParameters(Map<String, Object> parameters) {
    this.parameters = parameters;
  }

  public Map<String, Object> getResourcesUsed() {
    return resourcesUsed;
  }

  public void setResourcesUsed(Map<String, Object> resourcesUsed) {
    this.resourcesUsed = resourcesUsed;
  }

  public Map<String, Boolean> getConstrainsMet() {
    return constrainsMet;
  }

  public void setConstrainsMet(Map<String, Boolean> constrainsMet) {
    this.constrainsMet = constrainsMet;
  }

  public List<String> getLogs() {
    return logs;
  }

  public void setLogs(List<String> logs) {
    this.logs = logs;
  }

  public boolean isSuccessful() {
    return isSuccessful;
  }

  public void setSuccessful(boolean isSuccessful) {
    this.isSuccessful = isSuccessful;
  }

  public String getFailureReason() {
    return failureReason;
  }

  public void setFailureReason(String failureReason) {
    this.failureReason = failureReason;
  }

  public boolean isResultsSaved() {
    return isResultsSaved;
  }

  public void setResultsSaved(boolean isResultsSaved) {
    this.isResultsSaved = isResultsSaved;
  }

  public String getResultId() {
    return resultId;
  }

  public void setResultId(String resultId) {
    this.resultId = resultId;
  }
}
