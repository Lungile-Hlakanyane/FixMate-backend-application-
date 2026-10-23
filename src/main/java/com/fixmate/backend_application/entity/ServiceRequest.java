package com.fixmate.backend_application.entity;
import com.fixmate.backend_application.enums.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="service_requests")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceCategory category; // PLUMBING, ELECTRICAL, CLEANING etc

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    private LocalDateTime scheduledDate;
    private Double budget;

    @Enumerated(EnumType.STRING)
    private Urgency urgency; // LOW, NORMAL, URGENT

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private User assignedPro;

    @Enumerated(EnumType.STRING)
    private ServiceReviewStatus serviceReviewStatus = ServiceReviewStatus.APPROVE;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus = JobStatus.OPEN;

    public ServiceRequest(){}

    public ServiceRequest(Long id, User assignedPro, User customer, LocalDateTime updatedAt, LocalDateTime createdAt,
                          Urgency urgency, Double budget, LocalDateTime scheduledDate, RequestStatus status,
                          String location, ServiceCategory category, String description, String title, ServiceReviewStatus
                                  serviceReviewStatus, JobStatus jobStatus) {
        this.id = id;
        this.assignedPro = assignedPro;
        this.customer = customer;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.urgency = urgency;
        this.budget = budget;
        this.scheduledDate = scheduledDate;
        this.status = status;
        this.location = location;
        this.category = category;
        this.description = description;
        this.title = title;
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

    public ServiceCategory getCategory() {
        return category;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getAssignedPro() {
        return assignedPro;
    }

    public void setAssignedPro(User assignedPro) {
        this.assignedPro = assignedPro;
    }

    public ServiceReviewStatus getServiceReviewStatus() {
        return serviceReviewStatus;
    }

    public void setServiceReviewStatus(ServiceReviewStatus serviceReviewStatus) {
        this.serviceReviewStatus = serviceReviewStatus;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    //to reject
    public void markAsRejected(){
        this.serviceReviewStatus = ServiceReviewStatus.REJECT;
    }

    //to flag a service - by admin
    public void markAsFlagged(){
        this.serviceReviewStatus = ServiceReviewStatus.FLAG;
    }

    //to flag a service - by admin
    public void markAsApprove(){
        this.serviceReviewStatus = ServiceReviewStatus.APPROVE;
    }

    //confirm status to confirm
    public void markAsConfirmed(){this.jobStatus = JobStatus.CONFIRMED;}

    //complete job
    public void markAsComplete(){this.jobStatus = JobStatus.COMPLETED;}

    //mark as paid request
    public void markAsPaid(){this.jobStatus = JobStatus.PAID;}

}
