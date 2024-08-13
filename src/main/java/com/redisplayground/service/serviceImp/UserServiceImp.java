package com.redisplayground.service.serviceImp;

import com.redisplayground.dbHelper.UserDbHelper;
import com.redisplayground.entity.User;
import com.redisplayground.exception.SomethingWentWrongException;
import com.redisplayground.payload.UserRequest;
import com.redisplayground.payload.UserResponse;
import com.redisplayground.repository.UserRepository;
import com.redisplayground.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "user-cache")
public class UserServiceImp implements UserService {

    private final Logger LOGGER= LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserDbHelper dbHelper;

    @Override
    public String saveUser(UserRequest request) {
        if(repository.findByEmailEqualsIgnoreCase(request.getEmail()).isPresent()){
            throw new SomethingWentWrongException("User already exits");
        }
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
    @Cacheable(value="User", key="#id")
    public UserResponse findByid(Integer id) {
        LOGGER.debug("Find by id method called..");
        User user = dbHelper.findById(id).get();
        LOGGER.debug("***** making response");
        if (user!=null){
            LOGGER.info("Making a response");
          return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build();
        }
        return null;
    }
}
