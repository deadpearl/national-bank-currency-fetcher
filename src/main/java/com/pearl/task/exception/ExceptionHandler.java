package com.pearl.task.exception;

import org.springframework.http.HttpStatus;

public class ExceptionHandler extends RuntimeException  {
    private HttpStatus status;
    private final String message;


    public ExceptionHandler(String description, HttpStatus status) {
        this.status = status;
        this.message = description;
    }
    public ExceptionHandler(String description){
        this.message = description;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
