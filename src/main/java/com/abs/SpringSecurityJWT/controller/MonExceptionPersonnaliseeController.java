package com.abs.SpringSecurityJWT.controller;

import com.abs.SpringSecurityJWT.dto.ErrorResponseDTO;
import com.abs.SpringSecurityJWT.notFoundExceptionClass.MyNotFoundExceptionClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MonExceptionPersonnaliseeController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex, WebRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setStatusCode(500);
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MyNotFoundExceptionClass.class)
    public final ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(MyNotFoundExceptionClass ex, WebRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
