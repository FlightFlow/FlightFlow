package com.flightcoordinator.server.exception;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(AppError.class)
  public ResponseEntity<ResponseObject<Object>> handleAppError(AppError exception) {
    return ResponseHelper.generateResponse(
        exception.getStatus(),
        false,
        exception.getMessage(),
        null);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ResponseObject<Object>> handleEnumValidationError(
      MethodArgumentTypeMismatchException exception) {
    return ResponseHelper.generateResponse(
        HttpStatus.BAD_REQUEST.value(),
        false,
        exception.getMessage(),
        null);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ResponseObject<Object>> handleIllegalArgumentException(
      IllegalArgumentException exception) {
    return ResponseHelper.generateResponse(
        HttpStatus.BAD_REQUEST.value(),
        false,
        exception.getMessage(),
        null);
  }

  @ExceptionHandler(OptimisticLockingFailureException.class)
  public ResponseEntity<ResponseObject<Object>> handleOptimisticLockingFailureException(
      OptimisticLockingFailureException exception) {
    return ResponseHelper.generateResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        false,
        exception.getMessage(),
        null);
  }
}
