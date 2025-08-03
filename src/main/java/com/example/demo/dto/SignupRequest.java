package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phone;
    private User.Role role = User.Role.USER;
    private Long agentId;
}