package com.assignment.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class NotAvailableException extends RuntimeException {
    public NotAvailableException() {
        super();
    }

    public NotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAvailableException(String message) {
        super(message);
    }

    public NotAvailableException(Throwable cause) {
        super(cause);
    }
}
