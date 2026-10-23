package com.fixmate.backend_application.exceptions;

import com.fixmate.backend_application.enums.ServiceReviewStatus;

public class InvalidReviewStatusException extends RuntimeException{
    public InvalidReviewStatusException(ServiceReviewStatus reviewStatus){
        super("Invalid Review Status:  "+ reviewStatus);
    }
}
