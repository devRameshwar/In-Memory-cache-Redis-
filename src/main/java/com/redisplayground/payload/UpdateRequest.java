package com.redisplayground.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest implements Serializable {
    private String name;
    private String email;
    private String password;
}
