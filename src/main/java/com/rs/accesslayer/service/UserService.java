package com.rs.accesslayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.accesslayer.entity.User;
import com.rs.accesslayer.repository.UserRepository;
import com.rs.accesslayer.utitlity.JwtUtility;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public User createUser(final User user, final String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String role = JwtUtility.extractRole(token);
        Long tenantId = JwtUtility.extractTenantId(token);

        if(!"ADMIN".equals(role)) {
            throw new RuntimeException("Access denied: Only ADMIN can create users");
        }

        user.setTenantId(tenantId);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByTenant(final String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String role = JwtUtility.extractRole(token);
        Long tenantId = JwtUtility.extractTenantId(token);
        
        if(!"ADMIN".equals(role)) {
            throw new RuntimeException("Access denied: Only ADMIN can fetch users");
        }
        return userRepository.findByTenantId(tenantId);
    }
}
