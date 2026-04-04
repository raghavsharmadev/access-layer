package com.rs.accesslayer.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.accesslayer.entity.AuditLog;
import com.rs.accesslayer.repository.AuditLogRepository;
import com.rs.accesslayer.utitlity.JwtUtility;

@Service
public class AuditService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public void log(Long userId, String action, String entity, Long entityId, Long tenantId) {

        AuditLog log = new AuditLog();
        log.setUserId(userId);
        log.setAction(action);
        log.setEntity(entity);
        log.setEntityId(entityId);
        log.setTenantId(tenantId);
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(log);
    }

    public List<AuditLog> getAuditLogs(final String authHeader, final Long tenantId) {
        String token = authHeader.replace("Bearer ", "");
        String role = JwtUtility.extractRole(token);

        if(!"ADMIN".equals(role)) {
            throw new  RuntimeException("Only ADMIN can access audit logs");
        }

        return auditLogRepository.findByTenantId(tenantId);
    }
}