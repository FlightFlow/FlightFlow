package com.flightcoordinator.server.helpers;

public enum StatusHelper {
  NOT_FOUND("Not Found", 400),
  INTERNAL_SERVER_ERROR("Internal server error", 500);

  public final String message;
  public final int status;

  private StatusHelper(String message, int status) {
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
