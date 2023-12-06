package br.com.dogins.exceptions.handler;

import br.com.dogins.exceptions.*;
import br.com.dogins.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PurchaseFailedException.class)
    public final ResponseEntity<ExceptionResponse> handlerPurchaseFailedException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerResourceNotFoundException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ListIsEmptyException.class)
    public final ResponseEntity<ExceptionResponse> handlerListIsEmptyException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InvalidValueException.class)
    public final ResponseEntity<ExceptionResponse> handlerInvalidValue(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
