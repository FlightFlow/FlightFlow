package com.flightcoordinator.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "algorithm_runs")
public class AlgorithmRunsEntity {
  @Id
  private String id;
}
