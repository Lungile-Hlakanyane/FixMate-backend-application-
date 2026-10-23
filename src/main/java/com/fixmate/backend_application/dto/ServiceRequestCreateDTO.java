package com.fixmate.backend_application.dto;
import com.fixmate.backend_application.enums.JobStatus;
import com.fixmate.backend_application.enums.ServiceCategory;
import com.fixmate.backend_application.enums.ServiceReviewStatus;
import com.fixmate.backend_application.enums.Urgency;
import java.time.LocalDateTime;

public class ServiceRequestCreateDTO {

    private String title;
    private String description;
    private ServiceCategory category;
    private String location;
    private LocalDateTime scheduledDate;
    private Double budget;
    private Urgency urgency;
    private Long customerId;
    private ServiceReviewStatus serviceReviewStatus = ServiceReviewStatus.APPROVE;
    private JobStatus jobStatus = JobStatus.OPEN;

    public ServiceRequestCreateDTO(){}

    public ServiceRequestCreateDTO(String title, Long customerId, Urgency urgency,
                                   Double budget, LocalDateTime scheduledDate, String location,
                                   ServiceCategory category, String description,
                                   ServiceReviewStatus serviceReviewStatus, JobStatus jobStatus) {
        this.title = title;
        this.customerId = customerId;
        this.urgency = urgency;
        this.budget = budget;
        this.scheduledDate = scheduledDate;
        this.location = location;
        this.category = category;
        this.description = description;
        this.serviceReviewStatus = serviceReviewStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceCategory getCategory() {
        return category;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    public ServiceReviewStatus getServiceReviewStatus() {
        return serviceReviewStatus;
    }

    public JobStatus getJobStatus() {return jobStatus;}

    public void setJobStatus(JobStatus jobStatus) {this.jobStatus = jobStatus;}

    public void setServiceReviewStatus(ServiceReviewStatus serviceReviewStatus) {this.serviceReviewStatus = serviceReviewStatus;}
}
