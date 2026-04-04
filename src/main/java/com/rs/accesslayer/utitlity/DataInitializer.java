package com.rs.accesslayer.utitlity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rs.accesslayer.entity.User;
import com.rs.accesslayer.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByEmail("admin@mail.com").isEmpty()){
            User admin = new User();
            admin.setEmail("admin@mail.com");
            admin.setPassword("password");
            admin.setRole("ADMIN");
            admin.setTenantId(1L);

            userRepository.save(admin);
        }
    }
    
}
