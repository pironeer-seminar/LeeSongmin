package com.example.pironeer.controller;

import com.example.pironeer.dto.UserDto;
import com.example.pironeer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto.Response> createUser(@RequestBody UserDto.CreateRequest request) {
        UserDto.Response response = userService.createUserDto(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto.Response> getUserById(@PathVariable Long id) {
        UserDto.Response response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserDto.Response>> getAllUsers() {
        List<UserDto.Response> users = userService.getAllUserDtos();
        return ResponseEntity.ok(users);
    }
}