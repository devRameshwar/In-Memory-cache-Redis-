package com.redisplayground.controller;

import com.redisplayground.payload.UserRequest;
import com.redisplayground.payload.UserResponse;
import com.redisplayground.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService service;


    private final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public ResponseEntity<String> insert(@RequestBody UserRequest request){
        LOGGER.info("***** save data Apis called");
        return ResponseEntity.ok(service.saveUser(request));
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<UserResponse> findById(@PathVariable Integer id){
        LOGGER.info("***** Find by id apis called.");
        return ResponseEntity.ok(service.findByid(id));
    }

    @RequestMapping(path = "/get-all",method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        LOGGER.info("***** FindAll by id apis called.");
        return ResponseEntity.ok(service.getAllUsers());
    }

    @RequestMapping(path = "/delete-by-id/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        LOGGER.info("***** delete by id Apis called..");
        return ResponseEntity.ok(service.deleteById(id));
    }

}
