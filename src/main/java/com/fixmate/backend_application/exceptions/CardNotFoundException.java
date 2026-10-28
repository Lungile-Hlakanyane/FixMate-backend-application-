package com.fixmate.backend_application.exceptions;

public class CardNotFoundException extends RuntimeException{
    public CardNotFoundException(Long cardId){
        super("Card with id: "+ cardId + " not found");
    }
}
