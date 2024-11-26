package com.ssafy.housework.controller.advices;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseEntityAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        System.out.println("body to be written: " + body + " it's class: " + body.getClass().getName());

        // Request for Swagger URI
        String requestPath = request.getURI().getPath();
        if (requestPath.startsWith("/swagger-ui/") || requestPath.startsWith("/v3/api-docs/swagger-config")) {
            return body;
        }

        // DELETE REQUEST respond no content
        if (request.getMethod().equals(HttpMethod.DELETE)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(body);
    }
}