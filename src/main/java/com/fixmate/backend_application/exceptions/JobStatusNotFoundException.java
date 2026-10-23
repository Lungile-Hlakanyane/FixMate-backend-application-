package com.fixmate.backend_application.exceptions;
import com.fixmate.backend_application.enums.JobStatus;

public class JobStatusNotFoundException extends RuntimeException{
    public JobStatusNotFoundException(JobStatus status){
        super("JobStatus: "+ status + " is not allowed for this operation.");
    }
    public JobStatusNotFoundException(String status){
        super("Invalid JobStatus: "+ status + " Allowed values: ALL, OPEN, PENDING, CONFIRMED, IN_PROGRESS, COMPLETED, CANCELLED, PAID");
    }
}
