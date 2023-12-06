package br.com.dogins.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String message) {
        super(message);
    }
}