package com.rs.accesslayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.accesslayer.entity.User;
import com.rs.accesslayer.exception.AccessDeniedException;
import com.rs.accesslayer.repository.UserRepository;
import com.rs.accesslayer.utitlity.JwtUtility;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private AuditService auditService;

    public User createUser(final User user, final String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = JwtUtility.extractUserId(token);
        String role = JwtUtility.extractRole(token);
        Long tenantId = JwtUtility.extractTenantId(token);

        if(!"ADMIN".equals(role)) {
            throw new AccessDeniedException("Access denied: Only ADMIN can create users");
        }

        user.setTenantId(tenantId);
        User savedUser = userRepository.save(user);

        auditService.log(userId, "CREATE_USER", savedUser.getRole(), savedUser.getId(), tenantId);
        return savedUser;
    }


    public List<User> getUsersByTenant(final String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = JwtUtility.extractUserId(token);
        String role = JwtUtility.extractRole(token);
        Long tenantId = JwtUtility.extractTenantId(token);
        
        if(!"ADMIN".equals(role)) {
            throw new AccessDeniedException("Access denied: Only ADMIN can fetch users");
        }

        auditService.log(userId, "VIEW_USERS", "USER", null, tenantId);
        return userRepository.findByTenantId(tenantId);
    }
}
