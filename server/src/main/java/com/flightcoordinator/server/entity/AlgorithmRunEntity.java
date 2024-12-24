package com.flightcoordinator.server.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "algorithm_run_table")
public class AlgorithmRunEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "algorithm_name", nullable = false)
  private String algorithmName;

  @Column(name = "start_time", nullable = false)
  private Date startTime;

  @Column(name = "end_time", nullable = false)
  private Date endTime;

  @Column(name = "runtime_in_milliseconds", nullable = false)
  private Long runtimeInMilliseconds;

  @Column(name = "parameters_json")
  private String parametersJson;

  @Column(name = "resources_json")
  private String resourcesJson;

  @ElementCollection
  @CollectionTable(name = "algorithm_run_constraints", joinColumns = @JoinColumn(name = "algorithm_run_id"))
  @MapKeyColumn(name = "constraint_name")
  @Column(name = "constraint_value")
  private Map<String, Boolean> constraintsMet;

  @ElementCollection
  @CollectionTable(name = "algorithm_run_logs", joinColumns = @JoinColumn(name = "algorithm_run_id"))
  @Column(name = "log_entry")
  private List<String> logs;

  @Column(name = "is_successful", nullable = false)
  private Boolean isSuccessful;

  @Column(name = "failure_reason", nullable = true)
  private String failureReason;

  @Column(name = "is_results_saved", nullable = false)
  private Boolean isResultsSaved;

  @OneToOne
  @JoinColumn(name = "result_id", nullable = false)
  private AlgorithmResultEntity result;

  public AlgorithmRunEntity() {
  }

  public AlgorithmRunEntity(String id, String algorithmName, Date startTime, Date endTime, Long runtimeInMilliseconds,
      String parametersJson, String resourcesJson, Map<String, Boolean> constraintsMet, List<String> logs,
      Boolean isSuccessful, String failureReason, Boolean isResultsSaved, AlgorithmResultEntity result) {
    this.id = id;
    this.algorithmName = algorithmName;
    this.startTime = startTime;
    this.endTime = endTime;
    this.runtimeInMilliseconds = runtimeInMilliseconds;
    this.parametersJson = parametersJson;
    this.resourcesJson = resourcesJson;
    this.constraintsMet = constraintsMet;
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

  public Boolean isSuccessful() {
    return isSuccessful;
  }

  public void setSuccessful(Boolean isSuccessful) {
    this.isSuccessful = isSuccessful;
  }

  public String getFailureReason() {
    return failureReason;
  }

  public void setFailureReason(String failureReason) {
    this.failureReason = failureReason;
  }

  public Boolean isResultsSaved() {
    return isResultsSaved;
  }

  public void setResultsSaved(Boolean isResultsSaved) {
    this.isResultsSaved = isResultsSaved;
  }

  public AlgorithmResultEntity getResult() {
    return result;
  }

  public void setResult(AlgorithmResultEntity result) {
    this.result = result;
  }
}
