package com.redisplayground.dbHelper;

import com.redisplayground.entity.User;
import com.redisplayground.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDbHelper {
    private final Logger LOGGER = LoggerFactory.getLogger(UserDbHelper.class);
    @Autowired
    private UserRepository repository;

    public Optional<User> findById(Integer id) {

        Optional<User> user = this.repository.findById(id);
        LOGGER.info("******* find by db helper called.." + user);
        return user;
    }

    public List<User> getAllUsers() {
        LOGGER.info("***** findAll method called in dbHelper class...");
        return repository.findAll();
    }

    public Boolean deleteUser(User user) {
        LOGGER.info("******* delete method called in dbHelper ");
        repository.delete(user);
        LOGGER.info("******* Delete data by id Successfully.");
        return true;
    }

    public Optional<User> findByEmail(String email) {
        LOGGER.info("******* Find By Email called..");
        return repository.findByEmailEqualsIgnoreCase(email);
    }

    public User updateUser(User user) {
        LOGGER.info("******* Update User Method called ");
        User update = repository.save(user);
        LOGGER.info("Updated User after setting: "+update);
        return update;
    }
}
