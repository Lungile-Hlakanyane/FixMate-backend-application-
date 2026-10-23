package com.fixmate.backend_application.exceptions;
import com.fixmate.backend_application.enums.Role;

public class InvalidEnumException extends RuntimeException{
    public InvalidEnumException(Role role){
        super("Invalid Enum Exception: " + role);
    }
}
