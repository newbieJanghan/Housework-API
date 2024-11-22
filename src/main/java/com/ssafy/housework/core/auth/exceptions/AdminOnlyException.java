package com.ssafy.housework.core.auth.exceptions;

public class AdminOnlyException extends RuntimeException {
    public AdminOnlyException(String message) {
        super(message);
    }
}
