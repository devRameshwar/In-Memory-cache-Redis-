package com.redisplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisPlayGroundApplication {

    public static void main(String[] args) {

        SpringApplication.run(RedisPlayGroundApplication.class, args);

        System.out.println("Application is running....");
    }

}
