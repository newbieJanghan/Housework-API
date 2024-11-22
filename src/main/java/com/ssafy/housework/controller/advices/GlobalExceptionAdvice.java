package com.ssafy.housework.controller.advices;

import com.ssafy.housework.controller.exceptions.AmbiguousTargetException;
import com.ssafy.housework.core.auth.exceptions.AdminOnlyException;
import com.ssafy.housework.core.auth.interceptor.token.InvalidTokenException;
import com.ssafy.housework.model.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidTokenException.class, AmbiguousTargetException.class})
    public ErrorResponse handleBadRequestException(Throwable ex) {
        log.error("Bad Request Exception", ex);
        return build(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(AdminOnlyException.class)
    public ErrorResponse handleForbiddenException(Throwable ex) {
        log.error("Forbidden Exception", ex);
        return build(HttpStatus.FORBIDDEN, ex);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalException(Throwable ex) {
        log.error("Exception from service", ex);

        HttpStatus innerStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        
        if (ex instanceof IllegalArgumentException) {
            innerStatusCode = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof ResourceNotFoundException) {
            innerStatusCode = HttpStatus.NOT_FOUND;
        }
        // TODO, not handled exception should not return detail message

        return build(HttpStatus.OK, ex, innerStatusCode);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (ex.getRootCause() instanceof IllegalArgumentException) {
            ProblemDetail body = this.buildBody(HttpStatus.BAD_REQUEST, ex.getRootCause());
            return new ResponseEntity<>(body, HttpStatus.OK);
        }

        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    private ErrorResponse build(HttpStatus status, Throwable ex) {
        return new ErrorResponseException(status, buildBody(status, ex), ex);
    }

    private ErrorResponse build(HttpStatus status, Throwable ex, HttpStatus bodyStatus) {
        return new ErrorResponseException(status, buildBody(bodyStatus, ex), ex);
    }

    private ProblemDetail buildBody(HttpStatus status, Throwable ex) {
        return ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    }
}
