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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/${api.version}/user")
@Tag(name = "User Controller", description = "Endpoints for managing system users.")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/auth/login")
  @Operation(summary = "Login to the system", description = "Login to system by using an email and password.")
  public ResponseEntity<ResponseObject<AuthDetailsDTO>> login(
      @RequestBody LoginDetailsDTO loginDetails,
      HttpServletResponse response) {
    AuthDetailsDTO authDetails = userService.login(loginDetails);
    response.addCookie(authDetails.getAccessTokenCookie());
    response.addCookie(authDetails.getRefreshTokenCookie());
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }

  @PostMapping("/auth/register")
  @Operation(summary = "Register to the system", description = "Register to the system.")
  public ResponseEntity<ResponseObject<Object>> register(@RequestBody RegisterDetailsDTO registerDetails) {
    userService.register(registerDetails);
    return ResponseHelper.generateResponse(HttpStatus.CREATED.value(), true, HttpStatus.CREATED.getReasonPhrase(),
        null);
  }

  @PostMapping("/auth/newAccessToken")
  @Operation(summary = "Get a new access token", description = "Get a new access token using a refresh token.")
  public ResponseEntity<ResponseObject<Object>> newAccessToken(
      @CookieValue("accessToken") String accessToken,
      @CookieValue("refreshToken") String refreshToken,
      HttpServletResponse response) {
    Cookie newAccessToken = userService.getNewAccessTokenAsCookie(accessToken, refreshToken);
    response.addCookie(newAccessToken);
    return ResponseHelper.generateResponse(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), null);
  }
}
