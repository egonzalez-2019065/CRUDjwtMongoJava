package com.examplejwt.examplejwt.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDto {
    private String name;
    private String email;
    private String password;
}
