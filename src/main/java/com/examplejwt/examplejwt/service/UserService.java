package com.examplejwt.examplejwt.service;

import com.examplejwt.examplejwt.dto.UserDto;
import com.examplejwt.examplejwt.entity.User;
import com.examplejwt.examplejwt.respository.UserRepository;
import com.examplejwt.examplejwt.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // Crear un método para obtener un userDTO
    private UserDto toDto(User user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
    // Obtener todos los usuarios en la base de datos
    public List<UserDto> getALl(){
        return userRepository.findAll().stream()
            .map(this::toDto)
                .toList();
    }

    // Obtener un solo usuario por id
    public UserDto getById(String id){
        return userRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    // Guardar un nuevo usuario en la base de datos
    public UserDto save(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));

        user.setRole(Role.ADMIN);
        User userSaved = userRepository.save(user);
        return this.toDto(userSaved);
    }

    // Borrar un usuario de la base de datos
    public void delete(String id){
        User user =  this.userRepository.findById(id).orElse(null);
        assert user != null;
        this.userRepository.delete(user);
    }

    // Actualizar el usuario
    public UserDto update(String id, UserDto userDto){
        User user =  this.userRepository.findById(id).orElse(null);
        assert user != null;
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        User entityUpdated = userRepository.save(user);
        return this.toDto(entityUpdated);
    }



}
