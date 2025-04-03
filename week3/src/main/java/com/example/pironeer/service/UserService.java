package com.example.pironeer.service;

import com.example.pironeer.domain.User;
import com.example.pironeer.dto.UserDto;
import com.example.pironeer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long createUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.id;
    }

    @Transactional
    public UserDto.Response createUserDto(UserDto.CreateRequest request) {
        User user = new User(request.getUsername(), request.getEmail());
        User savedUser = userRepository.save(user);
        return new UserDto.Response(savedUser.id, savedUser.username, savedUser.email);
    }

    @Transactional(readOnly = true)
    public UserDto.Response getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return new UserDto.Response(user.id, user.username, user.email);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<UserDto.Response> getAllUserDtos() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto.Response(user.id, user.username, user.email))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public User findUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }
}