package com.redisplayground.service.serviceImp;

import com.redisplayground.entity.User;
import com.redisplayground.payload.UserRequest;
import com.redisplayground.payload.UserResponse;
import com.redisplayground.repository.UserRepository;
import com.redisplayground.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final Logger LOGGER= LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserRepository repository;

    @Override
    @CachePut(key = "#user.Id",value = "User")
    public String saveUser(UserRequest request) {
        LOGGER.info("data in service: "+request);
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        User save = repository.save(user);
        if (save!=null){
            return "User saved";
        }
        return "Something went wrong..";
    }

    @Override
    @Cacheable(key = "#user.Id",value = "User")
    public UserResponse findByid(Integer id) {
        User byId = repository.findById(id);
        if (byId!=null){
          return UserResponse.builder().id(byId.getId()).name(byId.getName()).email(byId.getEmail()).build();
        }
        return null;
    }
}
