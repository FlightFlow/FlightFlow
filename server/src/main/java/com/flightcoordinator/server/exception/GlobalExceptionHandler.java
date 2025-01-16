package com.flightcoordinator.server.exception;

import java.util.ArrayList;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(AppError.class)
  public ResponseEntity<ResponseObject<Object>> handleAppError(AppError exception) {
    boolean isNotFound = exception.getStatus() == HttpStatus.NOT_FOUND.value();
    return ResponseHelper.generateResponse(
        exception.getStatus(),
        isNotFound,
        exception.getMessage(),
        isNotFound ? new ArrayList<>() : null);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ResponseObject<Object>> handleEnumValidationError(
      MethodArgumentTypeMismatchException exception) {
    return ResponseHelper.generateResponse(
        HttpStatus.BAD_REQUEST.value(),
        false,
        "exception.enumValidationError",
        null);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ResponseObject<Object>> handleIllegalArgumentException(
      IllegalArgumentException exception) {
    return ResponseHelper.generateResponse(
        HttpStatus.BAD_REQUEST.value(),
        false,
        "exception.illegalArgumentException",
        null);
  }

  @ExceptionHandler(OptimisticLockingFailureException.class)
  public ResponseEntity<ResponseObject<Object>> handleOptimisticLockingFailureException(
      OptimisticLockingFailureException exception) {
    return ResponseHelper.generateResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        false,
        "exception.optimisticLockingFailureException",
        null);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ResponseObject<Object>> handleAuthenticationException(AuthenticationException exception) {
    return ResponseHelper.generateResponse(
        HttpStatus.UNAUTHORIZED.value(),
        false,
        "exception.authenticationException",
        null);
  }

  @ExceptionHandler(InternalAuthenticationServiceException.class)
  public ResponseEntity<ResponseObject<Object>> handleInternalAuthenticationServiceException(
      InternalAuthenticationServiceException exception) {
    return ResponseHelper.generateResponse(
        HttpStatus.UNAUTHORIZED.value(),
        false,
        "exception.authenticationException",
        null);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ResponseObject<Object>> handleBadCredentialsException(BadCredentialsException exception) {
    return ResponseHelper.generateResponse(
        HttpStatus.UNAUTHORIZED.value(),
        false,
        exception.getMessage(),
        null);
  }

  @ExceptionHandler(Forbidden.class)
  public ResponseEntity<ResponseObject<Object>> handleForbiddenException(Forbidden exception) {
    return ResponseHelper.generateResponse(
        HttpStatus.FORBIDDEN.value(),
        false,
        "exception.authenticationException",
        null);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ResponseObject<Object>> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex) {
    return ResponseHelper.generateResponse(
        HttpStatus.FORBIDDEN.value(),
        false,
        "exception.dataIntegrityViolationException",
        null);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ResponseObject<Object>> handleConstrainViolationException(ConstraintViolationException ex) {
    return ResponseHelper.generateResponse(
        HttpStatus.FORBIDDEN.value(),
        false,
        "exception.constraintValidationException",
        null);
  }
}
