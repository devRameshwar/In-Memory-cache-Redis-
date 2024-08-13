package com.redisplayground.dbHelper;

import com.redisplayground.entity.User;
import com.redisplayground.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDbHelper {
    @Autowired
    private UserRepository repository;

    private final Logger LOGGER= LoggerFactory.getLogger(UserDbHelper.class);


    public Optional<User> findById(Integer id) {

        Optional<User> user = this.repository.findById(id);
        LOGGER.info("******* find by db helper called.."+user);
        return user;
    }
}
