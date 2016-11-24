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
        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({UserEmailNotFoundException.class})
    public ResponseEntity<Error> userEmailNotFound (UserEmailNotFoundException e){
        String email = e.getEmail();
        long timestamp = System.currentTimeMillis();
        Error error = new Error(1,"User email not found","Email address: "+email+" not found.", timestamp);
        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserTokenNotFoundException.class})
    public ResponseEntity<Error> userTokenNotFoundException (UserTokenNotFoundException e){
        String id = e.getId();
        long timestamp = System.currentTimeMillis();
        Error error = new Error(1,"Token not found","Token: "+ id +" not found.", timestamp);
        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ExpiredTokenException.class})
    public ResponseEntity<Error> expiredTokenException (ExpiredTokenException e){
        String id = e.getSessionToken();
        long timestamp = System.currentTimeMillis();
        Error error = new Error(1,"Token expired","Token: "+ id +" expired.", timestamp);
        return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
    }
}
