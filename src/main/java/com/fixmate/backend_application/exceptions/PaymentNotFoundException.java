package com.fixmate.backend_application.exceptions;

public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException(Long paymentId){
        super("Payment with id: " + paymentId + " not found");
    }
}
