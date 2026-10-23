package com.fixmate.backend_application.exceptions;

public class RequestServiceNotFoundException extends RuntimeException{

    public RequestServiceNotFoundException(Long id){
        super("Request Service with Id "+ id + " Not found");
    }
}
