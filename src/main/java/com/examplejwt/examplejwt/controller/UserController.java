package com.examplejwt.examplejwt.controller;

import com.examplejwt.examplejwt.dto.UserDto;
import com.examplejwt.examplejwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> userslit = userService.getALl();
        return ResponseEntity.ok(userslit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") String id){
        UserDto userDto = userService.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto){
        UserDto usuario = userService.save(userDto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void > delete(@PathVariable("id") String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") String id, @RequestBody UserDto userDto){
       UserDto newUser = userService.update(id, userDto);
       return ResponseEntity.ok(newUser);
    }

}
