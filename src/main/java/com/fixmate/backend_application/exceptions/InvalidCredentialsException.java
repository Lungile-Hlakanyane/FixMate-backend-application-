package com.fixmate.backend_application.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String email){
        super("Invalid Credentials: "+email +" or password is invalid");
    }
}
