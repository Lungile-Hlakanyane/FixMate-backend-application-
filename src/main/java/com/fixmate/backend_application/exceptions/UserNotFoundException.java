package com.fixmate.backend_application.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("User with id "+ id + " not found");
    }
}
