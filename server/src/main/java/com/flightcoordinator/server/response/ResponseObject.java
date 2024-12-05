package com.flightcoordinator.server.response;

public class ResponseObject<T> {
  private boolean isSuccess = false;

  @SuppressWarnings("FieldMayBeFinal")
  private String message;

  @SuppressWarnings("FieldMayBeFinal")
  private T data;

  public ResponseObject(Boolean isSuccess, String message, T data) {
    this.isSuccess = isSuccess;
    this.message = message;
    this.data = data;
  }

  public boolean getIsSuccess() {
    return this.isSuccess;
  }

  public String getMessage() {
    return this.message;
  }

  public T getData() {
    return this.data;
  }
}
