package com.flightcoordinator.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.dto.AuthDetailsDTO;
import com.flightcoordinator.server.dto.LoginDetailsDTO;
import com.flightcoordinator.server.dto.RegisterDetailsDTO;
import com.flightcoordinator.server.response.ResponseHelper;
import com.flightcoordinator.server.response.ResponseObject;
import com.flightcoordinator.server.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/${api.version}/user")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/auth/login")
  @Operation
  public ResponseEntity<ResponseObject<AuthDetailsDTO>> login(
      @RequestBody LoginDetailsDTO loginDetails,
      HttpServletResponse response) {
    AuthDetailsDTO authDetails = userService.login(loginDetails);
    response.addCookie(authDetails.getAccessTokenCookie());
    response.addCookie(authDetails.getRefreshTokenCookie());
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @PostMapping("/auth/register")
  @Operation
  public ResponseEntity<ResponseObject<Object>> register(@RequestBody RegisterDetailsDTO registerDetails) {
    userService.register(registerDetails);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  }

  @PostMapping("/auth/newRefreshToken")
  public ResponseEntity<ResponseObject<Object>> newRefreshToken(
      @CookieValue("accessToken") String accessToken,
      @CookieValue("refreshToken") String refreshToken,
      HttpServletResponse response) {
    Cookie newAccessToken = userService.getNewAccessTokenAsCookie(accessToken, refreshToken);
    response.addCookie(newAccessToken);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
