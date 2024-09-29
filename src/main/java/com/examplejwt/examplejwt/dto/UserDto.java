package com.examplejwt.examplejwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String password;

    public UserDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
