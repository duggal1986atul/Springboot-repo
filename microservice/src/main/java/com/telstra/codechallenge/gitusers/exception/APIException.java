package com.telstra.codechallenge.gitusers.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public class APIException extends RuntimeException{
    private HttpStatusCode statusCode;
    private String error;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public APIException(HttpStatusCode statusCode, String error,LocalDateTime dateTime) {
        super(error);
        this.statusCode = statusCode;
        this.error = error;
        this.timestamp=dateTime;
    }
}
