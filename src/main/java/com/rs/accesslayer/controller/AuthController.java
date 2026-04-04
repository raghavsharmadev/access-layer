package com.rs.accesslayer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rs.accesslayer.entity.Auth;
import com.rs.accesslayer.service.AuthService;

@RestController
public class AuthController {
    @Autowired private AuthService authService;

    @PostMapping("/auth/login")
    public String login(@RequestBody final Auth auth) {
        return authService.login(auth);
    }
}
