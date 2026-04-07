package com.rs.accesslayer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.accesslayer.entity.Auth;
import com.rs.accesslayer.entity.User;
import com.rs.accesslayer.repository.UserRepository;
import com.rs.accesslayer.utitlity.JwtUtility;

@Service
public class AuthService {
    @Autowired private UserRepository userRepository;
    @Autowired private AuditService auditService;

    public String login(final Auth auth) {
        final User user = userRepository.findByEmail(auth.getEmail()).getFirst();
        if(user.getPassword().equals(auth.getPassword())) {
            auditService.log(user.getId(), "LOGIN_USER", "ADMIN", null, user.getTenantId());
            return JwtUtility.generateToken(user);
        }
        auditService.log(user.getId(), "FALED_LOGIN_USER", "ADMIN", null, user.getTenantId());
        return "No password match"; 
    }
}
