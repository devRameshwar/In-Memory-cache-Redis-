package com.redisplayground.repository;

import com.redisplayground.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, CriteriaBuilder.In> {

    Optional<User> findByEmailEqualsIgnoreCase(String email);

    User findById(Integer id);
}
