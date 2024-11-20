package com.ssafy.housework.controller.advices;

import com.ssafy.housework.controller.exceptions.BadRequestException;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    public ErrorResponseException handleBadRequestException(Exception ex) {
        System.out.println(ex.getCause() + " / " + ex.getMessage());
        return makeResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(AuthException.class)
    public ErrorResponseException handleAuthException(AuthException ex) {
        System.out.println(ex.getCause() + " / " + ex.getMessage());
        return makeResponse(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponseException handleException(Exception ex) {
        System.out.println(ex.getCause() + " / " + ex.getMessage());
        return makeResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    private ErrorResponseException makeResponse(HttpStatus status, Exception ex) {
        return new ErrorResponseException(status, ProblemDetail.forStatusAndDetail(status, ex.getMessage()), ex);
    }
}
