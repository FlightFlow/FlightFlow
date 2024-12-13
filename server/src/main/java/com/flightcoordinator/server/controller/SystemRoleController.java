package com.flightcoordinator.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightcoordinator.server.service.SystemRoleService;

@RestController
@RequestMapping("/api/${api.version}/system-role")
public class SystemRoleController {
  @Autowired
  private SystemRoleService systemRoleService;
}
