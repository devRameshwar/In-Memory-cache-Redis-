package com.redisplayground.repository;

import com.redisplayground.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmailEqualsIgnoreCase(String email);

    Optional<User> findById(Integer id);
}
