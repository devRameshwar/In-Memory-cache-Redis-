package com.redisplayground.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {

    private Integer id;
    private String name;
    private String email;
}
