package com.ssafy.housework.core.auth.web.filter;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Invalid Token");
    }
}
