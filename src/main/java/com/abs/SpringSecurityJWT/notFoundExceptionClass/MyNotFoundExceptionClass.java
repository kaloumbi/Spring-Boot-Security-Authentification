package com.abs.SpringSecurityJWT.notFoundExceptionClass;

public class MyNotFoundExceptionClass extends RuntimeException{

    public MyNotFoundExceptionClass(String message){
        super(message);
    }

    public MyNotFoundExceptionClass(String message, Throwable cause){
        super(message, cause);
    }
}
