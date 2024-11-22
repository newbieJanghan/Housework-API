package com.ssafy.housework.controller.exceptions;

public class AmbiguousTargetException extends BadRequestException {
    public AmbiguousTargetException() {
        super("Path variable id and request body id are different");
    }
}
