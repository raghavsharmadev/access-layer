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
        if(userRepository.findByEmail("admin1@mail.com").isEmpty()){
            User admin1 = new User();
            admin1.setEmail("admin1@mail.com");
            admin1.setPassword("password");
            admin1.setRole("ADMIN");
            admin1.setTenantId(1L);

            userRepository.save(admin1);
        }

        if(userRepository.findByEmail("admin2@mail.com").isEmpty()){
            User admin2 = new User();
            admin2.setEmail("admin2@mail.com");
            admin2.setPassword("password");
            admin2.setRole("ADMIN");
            admin2.setTenantId(2L);

            userRepository.save(admin2);
        }
    }
    
}
