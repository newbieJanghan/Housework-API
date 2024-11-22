package com.ssafy.housework.core.auth.interceptor.token;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(Throwable cause) {
        super("Invalid Token", cause);
    }
}
