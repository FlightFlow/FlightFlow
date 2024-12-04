package com.flightcoordinator.server.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AppError.class)
  public ResponseEntity<ErrorResponse> handleAppError(AppError exception) {
    ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), exception.getStatus().value());
    return new ResponseEntity<>(errorResponse, exception.getStatus());
  }

  public static class ErrorResponse {
    private final String message;
    private final int status;

    public ErrorResponse(String message, int status) {
      this.message = message;
      this.status = status;
    }

    public String getMessage() {
      return this.message;
    }

    public int getStatus() {
      return this.status;
    }
  }
}
