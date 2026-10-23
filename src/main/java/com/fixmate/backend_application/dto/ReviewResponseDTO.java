package com.fixmate.backend_application.dto;
import com.fixmate.backend_application.enums.Experience;
import java.time.LocalDateTime;

public class ReviewResponseDTO {

    private Long id;

    private Long proId;

    private Long customerId;

    private Integer stars; //stars max to 5

    private String comment;

    private Long serviceRequestId;

    private Experience experience;

    private LocalDateTime created;

    public ReviewResponseDTO() {}

    public ReviewResponseDTO(Long id, Experience experience,
                             LocalDateTime created, Long serviceRequestId,
                             String comment, Integer stars, Long customerId, Long proId) {
        this.id = id;
        this.experience = experience;
        this.created = created;
        this.serviceRequestId = serviceRequestId;
        this.comment = comment;
        this.stars = stars;
        this.customerId = customerId;
        this.proId = proId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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
}
