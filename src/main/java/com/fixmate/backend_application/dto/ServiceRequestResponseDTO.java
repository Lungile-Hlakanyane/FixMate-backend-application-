package com.fixmate.backend_application.dto;
import com.fixmate.backend_application.enums.JobStatus;
import com.fixmate.backend_application.enums.ServiceCategory;
import com.fixmate.backend_application.enums.ServiceReviewStatus;
import com.fixmate.backend_application.enums.Urgency;
import java.time.LocalDateTime;

public class ServiceRequestResponseDTO {

    private Long id;
    private String title;
    private String description;
    private ServiceCategory category;
    private String location;
    private String status;
    private LocalDateTime scheduledDate;
    private Double budget;
    private Urgency urgency;
    private LocalDateTime createdAt;
    private Long customerId;
    private String customerName;
    private Long proId;
    private String proName;
    private ServiceReviewStatus serviceReviewStatus;
    private JobStatus jobStatus;

    public ServiceRequestResponseDTO() {}

    public ServiceRequestResponseDTO(Long id, String title, String description, ServiceCategory category, String location, String status, LocalDateTime scheduledDate,
                                     Double budget, Urgency urgency, LocalDateTime createdAt, Long customerId, String customerName, Long proId, String proName,
                                     ServiceReviewStatus serviceReviewStatus, JobStatus jobStatus
                                     ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.location = location;
        this.status = status;
        this.scheduledDate = scheduledDate;
        this.budget = budget;
        this.urgency = urgency;
        this.createdAt = createdAt;
        this.customerId = customerId;
        this.customerName = customerName;
        this.proId = proId;
        this.proName = proName;
        this.serviceReviewStatus = serviceReviewStatus;
        this.jobStatus = jobStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public ServiceCategory getCategory() {
        return category;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {this.proName = proName;}

    public ServiceReviewStatus getServiceReviewStatus() {return serviceReviewStatus;}

    public void setServiceReviewStatus(ServiceReviewStatus serviceReviewStatus) {this.serviceReviewStatus = serviceReviewStatus;}

    public JobStatus getJobStatus() {return jobStatus;}

    public void setJobStatus(JobStatus jobStatus) {this.jobStatus = jobStatus;}
}
