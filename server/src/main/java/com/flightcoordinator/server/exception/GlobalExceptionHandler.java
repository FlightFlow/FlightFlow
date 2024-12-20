package com.flightcoordinator.server.exception;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(AppError.class)
  public ResponseEntity<ResponseObject<Object>> handleAppError(AppError exception) {
    System.err.println(exception.getMessage());
    return ResponseHelper.generateResponse(
        exception.getStatus(),
        false,
        exception.getMessage(),
        null);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ResponseObject<Object>> handleEnumValidationError(
      MethodArgumentTypeMismatchException exception) {
    System.err.println(exception.getMessage());
    return ResponseHelper.generateResponse(
        HttpStatus.BAD_REQUEST.value(),
        false,
        "exceptions.enumValidationError",
        null);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ResponseObject<Object>> handleIllegalArgumentException(
      IllegalArgumentException exception) {
    System.err.println(exception.getMessage());
    return ResponseHelper.generateResponse(
        HttpStatus.BAD_REQUEST.value(),
        false,
        "exception.illegalArgumentException",
        null);
  }

  @ExceptionHandler(OptimisticLockingFailureException.class)
  public ResponseEntity<ResponseObject<Object>> handleOptimisticLockingFailureException(
      OptimisticLockingFailureException exception) {
    System.err.println(exception.getMessage());
    return ResponseHelper.generateResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        false,
        "exception.optimisticLockingFailureException",
        null);
  }

  @ExceptionHandler(JwtException.class)
  public ResponseEntity<ResponseObject<Object>> handleJwtException(JwtException exception) {
    System.err.println(exception.getMessage());
    return ResponseHelper.generateResponse(
        HttpStatus.UNAUTHORIZED.value(),
        false,
        "exception.jwtException",
        null);
  }

  @ExceptionHandler(MalformedJwtException.class)
  public ResponseEntity<ResponseObject<Object>> handleMalformedJwtException(MalformedJwtException exception) {
    System.err.println(exception.getMessage());
    return ResponseHelper.generateResponse(
        HttpStatus.UNAUTHORIZED.value(),
        false,
        "exception.jwtException",
        null);
  }

  @ExceptionHandler(ExpiredJwtException.class)
  public ResponseEntity<ResponseObject<Object>> handleExpiredJwtException(ExpiredJwtException exception) {
    System.err.println(exception.getMessage());
    return ResponseHelper.generateResponse(
        HttpStatus.UNAUTHORIZED.value(),
        false,
        "exception.jwtException",
        null);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ResponseObject<Object>> handleAuthenticationException(AuthenticationException exception) {
    System.err.println(exception.getMessage());
    return ResponseHelper.generateResponse(
        HttpStatus.UNAUTHORIZED.value(),
        false,
        "exception.authenticationException",
        null);
  }

  @ExceptionHandler(InternalAuthenticationServiceException.class)
  public ResponseEntity<ResponseObject<Object>> handleInternalAuthenticationServiceException(
      InternalAuthenticationServiceException exception) {
    System.err.println(exception.getMessage());
    return ResponseHelper.generateResponse(
        HttpStatus.UNAUTHORIZED.value(),
        false,
        "exception.authenticationException",
        null);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ResponseObject<Object>> handleBadCredentialsException(BadCredentialsException exception) {
    System.err.println(exception.getMessage());
    return ResponseHelper.generateResponse(
        HttpStatus.UNAUTHORIZED.value(),
        false,
        "exception.authenticationException",
        null);
  }
}
