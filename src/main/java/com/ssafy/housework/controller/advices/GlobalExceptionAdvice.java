package com.ssafy.housework.controller.advices;

import com.ssafy.housework.controller.exceptions.BadRequestException;
import com.ssafy.housework.core.auth.service.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    public ErrorResponseException handleBadRequestException(Exception ex) {
        System.out.println(ex.getCause() + " / " + ex.getMessage());
        return new ErrorResponseException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(AuthException.class)
    public ErrorResponseException handleAuthException(AuthException ex) {
        return new ErrorResponseException(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponseException handleException(Exception ex) {
        return new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
}
