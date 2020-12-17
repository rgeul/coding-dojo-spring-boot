package com.assignment.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClientErrorException extends RuntimeException {
    public ClientErrorException() {
        super();
    }

    public ClientErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientErrorException(String message) {
        super(message);
    }

    public ClientErrorException(Throwable cause) {
        super(cause);
    }
}
