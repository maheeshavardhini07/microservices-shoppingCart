package com.microservice.userservice.service;

import java.util.List;

import com.microservice.userservice.dto.ResponseDto;
import com.microservice.userservice.entity.User;

public interface UserService {
    User saveUser(User user);

    ResponseDto getUser(Long userId);

    List<User> getAllUsers();
}
