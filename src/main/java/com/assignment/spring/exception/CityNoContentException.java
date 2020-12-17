package com.assignment.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class CityNoContentException extends RuntimeException {
    public CityNoContentException() {
        super();
    }

    public CityNoContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CityNoContentException(String message) {
        super(message);
    }

    public CityNoContentException(Throwable cause) {
        super(cause);
    }
}
