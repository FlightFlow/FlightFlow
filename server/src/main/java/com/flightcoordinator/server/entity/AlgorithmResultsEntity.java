package com.flightcoordinator.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "algorithm_results")
public class AlgorithmResultsEntity {
  @Id
  private String id;
}
