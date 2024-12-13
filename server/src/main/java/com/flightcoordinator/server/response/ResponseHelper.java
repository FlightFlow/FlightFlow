package com.flightcoordinator.server.response;

import org.springframework.http.ResponseEntity;

public class ResponseHelper<T> {
  public static <T> ResponseEntity<ResponseObject<T>> generateResponse(
      int status,
      boolean isSuccess,
      String message,
      T data) {
    return ResponseEntity.status(status).body(new ResponseObject<>(isSuccess, message, data));
  }
}
