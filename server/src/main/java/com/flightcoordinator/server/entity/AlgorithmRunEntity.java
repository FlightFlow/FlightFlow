package com.flightcoordinator.server.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "algorithm_runs_table")
public class AlgorithmRunEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotBlank(message = "Algorithm name cannot be blank")
  @Column(name = "algorithm_name", nullable = false)
  private String algorithmName;

  @NotBlank(message = "Start time cannot be blank")
  @Column(name = "start_time", nullable = false)
  private Date startTime;

  @NotBlank(message = "End time cannot be blank")
  @Column(name = "end_time", nullable = false)
  private Date endTime;

  @NotBlank(message = "Runtime in ms cannot be blank")
  @Column(name = "runtime_in_milliseconds", nullable = false)
  private long runtimeInMilliseconds;

  @NotBlank(message = "Parameters cannot be blank")
  @Column(name = "runtime_in_milliseconds", nullable = false)
  private Map<String, Object> parameters;

  @NotBlank(message = "Resources used cannot be blank")
  @Column(name = "resources_used", nullable = false)
  private Map<String, Object> resourcesUsed;

  @NotBlank(message = "Constraints met cannot be blank")
  @Column(name = "constraints_met", nullable = false)
  private Map<String, Boolean> constrainsMet;

  @Column(name = "logs", nullable = false)
  private List<String> logs;

  @Column(name = "is_successful", nullable = false)
  private boolean isSuccessful;

  @Column(name = "failure_reason", nullable = false)
  private String failureReason;

  @Column(name = "is_results_saved", nullable = false)
  private boolean isResultsSaved;

  @OneToOne
  @JoinColumn(name = "result_id", nullable = false)
  private AlgorithmResultEntity result;

  public AlgorithmRunEntity() {
  }

  public AlgorithmRunEntity(String id, @NotBlank(message = "Algorithm name cannot be blank") String algorithmName,
      @NotBlank(message = "Start time cannot be blank") Date startTime,
      @NotBlank(message = "End time cannot be blank") Date endTime,
      @NotBlank(message = "Runtime in ms cannot be blank") long runtimeInMilliseconds,
      @NotBlank(message = "Parameters cannot be blank") Map<String, Object> parameters,
      @NotBlank(message = "Resources used cannot be blank") Map<String, Object> resourcesUsed,
      @NotBlank(message = "Constraints met cannot be blank") Map<String, Boolean> constrainsMet, List<String> logs,
      boolean isSuccessful, String failureReason, boolean isResultsSaved, AlgorithmResultEntity result) {
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
    this.result = result;
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

  public AlgorithmResultEntity getResult() {
    return result;
  }

  public void setResult(AlgorithmResultEntity result) {
    this.result = result;
  }
}
