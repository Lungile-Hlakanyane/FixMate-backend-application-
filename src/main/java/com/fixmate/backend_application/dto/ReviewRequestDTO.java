package com.fixmate.backend_application.dto;
import com.fixmate.backend_application.enums.Experience;

import java.time.LocalDateTime;


public class ReviewRequestDTO {

    private Long id;
    private Long proId;
    private Long customerId;
    private Integer stars;
    private String comment;
    private Long serviceRequestId;
    private Experience experience;
    private LocalDateTime createdAt;

    public ReviewRequestDTO() {}

    public ReviewRequestDTO(Long id, Experience experience, Long serviceRequestId,
                            String comment, Integer stars, Long customerId, Long proId, LocalDateTime createdAt) {
        this.id = id;
        this.experience = experience;
        this.serviceRequestId = serviceRequestId;
        this.comment = comment;
        this.stars = stars;
        this.customerId = customerId;
        this.proId = proId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Long getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(Long serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
