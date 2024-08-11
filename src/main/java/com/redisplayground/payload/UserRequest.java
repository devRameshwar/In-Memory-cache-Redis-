package com.redisplayground.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserRequest {
    private String name;
    private String email;
    private String password;
}
