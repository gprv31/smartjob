package com.example.smartjob.application.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class UserAppException extends Exception {

    private final List<Error> errors;
    public UserAppException(Error error) {
        this.errors = Collections.singletonList(error);
    }

    public UserAppException(List<Error> errors) {
        this.errors = errors;
    }

    public UserAppException(Error error, Throwable cause) {
        super(cause);
        this.errors = Collections.singletonList(error);
    }
}
