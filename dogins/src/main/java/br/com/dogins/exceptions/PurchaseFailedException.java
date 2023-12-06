package br.com.dogins.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PurchaseFailedException extends RuntimeException {
    public PurchaseFailedException(String message) {
        super(message);
    }
}
