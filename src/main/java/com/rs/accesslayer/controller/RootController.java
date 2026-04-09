package com.rs.accesslayer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> root() {
        return ResponseEntity.ok(Map.of(
            "message", "Welcome to Access Layer API",
            "version", "1.0.0",
            "description", "Multi-tenant backend service for secure access management",
            "documentation", Map.of(
                "swagger-ui", "/swagger-ui.html",
                "api-docs", "/v3/api-docs"
            ),
            "endpoints", Map.of(
                "auth", Map.of(
                    "login", "POST /auth/login"
                ),
                "users", Map.of(
                    "create", "POST /users",
                    "list", "GET /users"
                ),
                "tenants", Map.of(
                    "create", "POST /tenants",
                    "list", "GET /tenants"
                ),
                "audit", Map.of(
                    "logs", "GET /audit-logs/{tenantId}"
                )
            ),
            "health", "/actuator/health"
        ));
    }
}