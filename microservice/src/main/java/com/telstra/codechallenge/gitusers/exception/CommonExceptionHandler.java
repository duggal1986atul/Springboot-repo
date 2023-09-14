package com.telstra.codechallenge.gitusers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(APIException.class)
    public APIException apiException(APIException ex, WebRequest request) {

        APIException message = new APIException(
                HttpStatusCode.valueOf(500),
                ex.getMessage(),
                LocalDateTime.now());
        return message;
    }
}
