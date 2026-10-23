package com.fixmate.backend_application.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException{
    public EmailAlreadyRegisteredException(String email){
        super("Email "+ email +" already registered");
    }
}
