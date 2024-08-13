package com.redisplayground.controller;

import com.redisplayground.payload.UserRequest;
import com.redisplayground.payload.UserResponse;
import com.redisplayground.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService service;


    private final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ResponseEntity<String> insert(@RequestBody UserRequest request){
        return ResponseEntity.ok(service.saveUser(request));
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<UserResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findByid(id));
    }

}
