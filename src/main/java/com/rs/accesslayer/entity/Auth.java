package com.rs.accesslayer.entity;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class Auth {
    @Email(message = "Invalid email format")
    private String email;
    private String password;
}
