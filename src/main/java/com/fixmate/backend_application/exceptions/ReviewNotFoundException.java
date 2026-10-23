package com.fixmate.backend_application.exceptions;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(Long id){
        super("Review with id " + id + " Not found ");
    }
}
