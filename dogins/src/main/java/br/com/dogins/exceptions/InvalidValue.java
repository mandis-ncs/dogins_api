package br.com.dogins.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class InvalidValue extends RuntimeException {
    public InvalidValue(String message) {
        super(message);
    }
}