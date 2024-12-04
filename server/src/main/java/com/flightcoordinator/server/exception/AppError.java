package com.flightcoordinator.server.exception;

import org.springframework.http.HttpStatus;

public class AppError extends RuntimeException {
  private final HttpStatus status;

  public AppError(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return this.status;
  }
}
