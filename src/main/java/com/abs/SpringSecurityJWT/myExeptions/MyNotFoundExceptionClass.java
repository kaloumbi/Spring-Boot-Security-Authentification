package com.abs.SpringSecurityJWT.myExeptions;

public class MyNotFoundExceptionClass extends RuntimeException{

    public MyNotFoundExceptionClass(String message){
        super(message);
    }

    public MyNotFoundExceptionClass(String message, Throwable cause){
        super(message, cause);
    }
}
