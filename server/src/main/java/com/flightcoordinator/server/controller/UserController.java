package com.flightcoordinator.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.service.UserService;

@RestController
@RequestMapping("/api/${api.version}/user")
public class UserController {
  @Autowired
  private UserService userService;
}
