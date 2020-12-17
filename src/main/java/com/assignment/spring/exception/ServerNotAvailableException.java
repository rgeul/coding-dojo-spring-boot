package com.assignment.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerNotAvailableException extends RuntimeException {
    public ServerNotAvailableException() {
        super();
    }

    public ServerNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerNotAvailableException(String message) {
        super(message);
    }

    public ServerNotAvailableException(Throwable cause) {
        super(cause);
    }
}
