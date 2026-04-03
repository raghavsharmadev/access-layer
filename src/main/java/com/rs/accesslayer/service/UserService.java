package com.rs.accesslayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.accesslayer.entity.User;
import com.rs.accesslayer.repository.UserRepository;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public User createUser(final User user, final Long tenantId, final String role) {
        if(!"ADMIN".equals(role)) {
            throw new RuntimeException("Access denied: Only ADMIN can create users");
        }

        user.setTenantId(tenantId);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByTenant(final Long tenantId, final String role) {
        if(!"ADMIN".equals(role)) {
            throw new RuntimeException("Access denied: Only ADMIN can fetch users");
        }
        return userRepository.findByTenantId(tenantId);
    }
}
