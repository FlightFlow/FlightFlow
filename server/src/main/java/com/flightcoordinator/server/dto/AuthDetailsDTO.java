package com.flightcoordinator.server.dto;

import jakarta.servlet.http.Cookie;

public class AuthDetailsDTO {
  private Cookie accessTokenCookie;
  private Cookie refreshTokenCookie;

  public AuthDetailsDTO() {
  }

  public AuthDetailsDTO(Cookie accessTokenCookie, Cookie refreshTokenCookie) {
    this.accessTokenCookie = accessTokenCookie;
    this.refreshTokenCookie = refreshTokenCookie;
  }

  public Cookie getAccessTokenCookie() {
    return accessTokenCookie;
  }

  public void setAccessTokenCookie(Cookie accessTokenCookie) {
    this.accessTokenCookie = accessTokenCookie;
  }

  public Cookie getRefreshTokenCookie() {
    return refreshTokenCookie;
  }

  public void setRefreshTokenCookie(Cookie refreshTokenCookie) {
    this.refreshTokenCookie = refreshTokenCookie;
  }
}
