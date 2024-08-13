package com.redisplayground.service;

import com.redisplayground.payload.UpdateRequest;
import com.redisplayground.payload.UserRequest;
import com.redisplayground.payload.UserResponse;

import java.util.List;

public interface UserService {
    String saveUser(UserRequest request);

    UserResponse findByid(Integer id);

    List<UserResponse> getAllUsers();

    String deleteById(Integer id);

    UserResponse updateUser(UpdateRequest request);
}
