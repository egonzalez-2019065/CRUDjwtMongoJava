package com.examplejwt.examplejwt.controller;

import com.examplejwt.examplejwt.dto.UserDto;
import com.examplejwt.examplejwt.dto.auth.AuthDto;
import com.examplejwt.examplejwt.dto.auth.LoginDto;
import com.examplejwt.examplejwt.dto.auth.RegisterDto;
import com.examplejwt.examplejwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto login){
        AuthDto authDto = this.authService.login(login);
        return ResponseEntity.ok(authDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterDto dto){
        UserDto userDto = this.authService.register(dto);
        return ResponseEntity.ok(userDto);
    }

}
