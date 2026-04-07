package com.rs.accesslayer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rs.accesslayer.model.ResponseModel;
import com.rs.accesslayer.service.AuditService;

@RestController
public class AuditController {
    @Autowired private AuditService auditService;

    @GetMapping("/audit-logs/{tenantId}")
    public ResponseModel getAuditLogs(@PathVariable final Long tenantId, @RequestHeader("Authorization") final String authHeader) {
        return ResponseModel.success(auditService.getAuditLogs(authHeader, tenantId));
    }
}
