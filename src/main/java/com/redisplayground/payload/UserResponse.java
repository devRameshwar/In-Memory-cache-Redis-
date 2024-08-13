package com.redisplayground.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class UserResponse implements Serializable {

    private Integer id;
    private String name;
    private String email;
}
