package com.redisplayground.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SomethingWentWrongException extends RuntimeException {
    private static final Integer serialNumber = 13214_13;
    private String message;
}
