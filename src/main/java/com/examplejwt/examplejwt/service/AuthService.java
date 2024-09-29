package com.examplejwt.examplejwt.service;

import com.examplejwt.examplejwt.config.JwtService;
import com.examplejwt.examplejwt.dto.UserDto;
import com.examplejwt.examplejwt.dto.auth.AuthDto;
import com.examplejwt.examplejwt.dto.auth.LoginDto;
import com.examplejwt.examplejwt.dto.auth.RegisterDto;
import com.examplejwt.examplejwt.entity.User;
import com.examplejwt.examplejwt.respository.UserRepository;
import com.examplejwt.examplejwt.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthDto login(final LoginDto request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByEmail(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return new AuthDto(token);
    }

    public UserDto register(final RegisterDto request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        this.userRepository.save(user);
        // Mapear los datos del usuario a un UserDto y devolverlo
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

}
