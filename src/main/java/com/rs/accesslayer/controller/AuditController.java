package com.rs.accesslayer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rs.accesslayer.entity.AuditLog;
import com.rs.accesslayer.service.AuditService;

@RestController
public class AuditController {
    @Autowired private AuditService auditService;

    @GetMapping("/audit-logs/{tenantId}")
    public List<AuditLog> getAuditLogs(@PathVariable final Long tenantId, @RequestHeader("Authorization") final String authHeader) {
        return auditService.getAuditLogs(authHeader, tenantId);
    }
}
