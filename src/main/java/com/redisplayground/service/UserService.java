package com.redisplayground.service;

import com.redisplayground.payload.UserRequest;
import com.redisplayground.payload.UserResponse;

public interface UserService {
    String saveUser(UserRequest request);

    UserResponse findByid(Integer id);

}
