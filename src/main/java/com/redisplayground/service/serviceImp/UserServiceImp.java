package com.redisplayground.service.serviceImp;

import com.redisplayground.dbHelper.UserDbHelper;
import com.redisplayground.entity.User;
import com.redisplayground.exception.SomethingWentWrongException;
import com.redisplayground.payload.UpdateRequest;
import com.redisplayground.payload.UserRequest;
import com.redisplayground.payload.UserResponse;
import com.redisplayground.repository.UserRepository;
import com.redisplayground.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@CacheConfig(cacheNames = "user-cache")
public class UserServiceImp implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserDbHelper dbHelper;

    @Override
    public String saveUser(UserRequest request) {
        if (repository.findByEmailEqualsIgnoreCase(request.getEmail()).isPresent()) {
            throw new SomethingWentWrongException("User already exits");
        }
        LOGGER.info("data in service: " + request);
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        User save = repository.save(user);
        if (save != null) {
            return "User saved";
        }
        return "Something went wrong..";
    }

    @Override
    @CacheEvict(value="User", key="#id")
    // @CacheEvict(value="User", allEntries=true) //in case there are multiple records to delete
    public String deleteById(Integer id) {
        LOGGER.info("****** Delete method called in service");
        User user = dbHelper.findById(id).get();
        Boolean delete = dbHelper.deleteUser(user);
        if (delete){
            return "Delete data Successfully";
        }
        else {
            throw new SomethingWentWrongException("data not found");
        }
    }

    @Override
    @CachePut(value="User", key="#result.id")
    public UserResponse updateUser(UpdateRequest request) {
        LOGGER.info("*******Update method in service class");
        User user = dbHelper.findByEmail(request.getEmail()).orElseThrow(() -> new SomethingWentWrongException("User Not Found..."));
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(request.getPassword());

        User updated = dbHelper.updateUser(user);
        //making response
        UserResponse updateResponse = UserResponse.builder()
                .name(updated.getName())
                .email(updated.getEmail())
                .id(updated.getId())
                .build();

        return updateResponse;
    }

    @Override
    @Cacheable(value = "User")
    public List<UserResponse> getAllUsers() {

        LOGGER.info("***Getting all user in service method..");
        List<User> allUsers = dbHelper.getAllUsers();
        List<UserResponse> allUserResponse = new ArrayList<>();
        if (allUsers != null) {
            LOGGER.info("********Making response ");
            Iterator<User> iterator = allUsers.iterator();
            while (iterator.hasNext()){
                User user = iterator.next();
                UserResponse userResponse = UserResponse.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .id(user.getId())
                        .build();

                allUserResponse.add(userResponse);
            }
        }else{
            throw new SomethingWentWrongException("Something went wrong....");
        }
        return allUserResponse;
    }

    @Override
    @Cacheable(value = "User", key = "#id")
    public UserResponse findByid(Integer id) {
        LOGGER.debug("Find by id method called..");
        User user = dbHelper.findById(id).get();
        LOGGER.debug("***** making response");
        if (user != null) {
            LOGGER.info("Making a response");
            return UserResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build();
        }else {
            throw new SomethingWentWrongException("Something went wrong.");
        }
    }
}
