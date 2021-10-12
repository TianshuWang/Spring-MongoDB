package com.tianshu.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class GroceryItemResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        return new ResponseEntity<>(getResponseException(ex,request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({GroceryItemNotFoundException.class,GroceryItemsNotFoundByCategoryException.class})
    public final ResponseEntity<Object> handleGroceryItemNotFoundException(Exception ex, WebRequest request){
        return new ResponseEntity<>(getResponseException(ex,request), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseException exception = new ResponseException(new Date(), "Validation Failed", ex.getBindingResult().toString());

        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseException getResponseException(Exception ex, WebRequest request){
        return new ResponseException(new Date(),ex.getMessage(),request.getDescription(false));
    }
}
