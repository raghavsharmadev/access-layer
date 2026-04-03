package com.rs.accesslayer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rs.accesslayer.entity.User;
import com.rs.accesslayer.service.UserService;

@RestController
public class UserController {
    @Autowired private UserService userService;

    @PostMapping(path = "/users")
    public User createUser(@RequestBody final User user, @RequestHeader("X-Tenant-Id") final Long tenantId, @RequestHeader("X-Role") final String role) {
        return userService.createUser(user, tenantId, role);
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers(@RequestHeader("X-Tenant-Id") final Long tenantId, @RequestHeader("X-Role") final String role) {
        return userService.getUsersByTenant(tenantId, role);
    }
}
