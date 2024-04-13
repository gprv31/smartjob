package com.example.smartjob.application.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class SmartJobExceptionResponse {
    private String message;
}
