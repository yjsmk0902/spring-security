package com.example.security.domain.dto;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    private String auth;
}
