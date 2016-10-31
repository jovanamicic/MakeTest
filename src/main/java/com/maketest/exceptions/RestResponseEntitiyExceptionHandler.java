package com.maketest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Created by Jovana Micic on 31-Oct-16.
 */
@ControllerAdvice
public class RestResponseEntitiyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Error> userNotFound (UserNotFoundException e){
        String email = e.getEmail();
        long timestamp = System.currentTimeMillis();
        Error error = new Error(1,"User not found","User with email: "+email+" and password not found.", timestamp);
        System.out.println("User with email: "+email+" not found.");
        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }
}
