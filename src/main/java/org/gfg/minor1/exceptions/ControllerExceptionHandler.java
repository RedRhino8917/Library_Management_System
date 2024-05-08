package org.gfg.minor1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = TxnException.class)
    public ResponseEntity<Object> handle(TxnException txnException){
        return new ResponseEntity<>(txnException.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value =Exception.class)
//    public ResponseEntity<Object> handle(Exception e){
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }

    // use 'e.getBindingResult().getFieldError().getDefaultMessage()' instead of 'e.getmessage()' to get the default message
    // this exception handler will handle all the exceptions related to @NotNull and print default messages at Postman
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException e){
        return new ResponseEntity<>(e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

}
