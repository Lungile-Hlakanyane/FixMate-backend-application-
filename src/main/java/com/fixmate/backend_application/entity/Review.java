package com.fixmate.backend_application.entity;
import com.fixmate.backend_application.enums.Experience;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name ="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long proId;

    private Long customerId;

    private Integer stars; //stars max to 5

    private String comment;

    private Long serviceRequestId;

    @Enumerated(EnumType.STRING)
    private Experience experience;

    @CreationTimestamp
    private LocalDateTime created;

    //constructor
    public Review() {}

    //non-argument constructor
    public Review(Long id, LocalDateTime created,
                  Experience experience, String comment, Integer stars,
                  Long customerId, Long proId, Long serviceRequestId) {
        this.id = id;
        this.created = created;
        this.experience = experience;
        this.comment = comment;
        this.stars = stars;
        this.customerId = customerId;
        this.proId = proId;
        this.serviceRequestId = serviceRequestId;
    }

    //getters and setters
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Long getCustomerId() {
        return customerId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(Long serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }
}

//TODO; FINISH WORKING ON THE DTO'S MAPPER, REPO, SERVICE, SERVICEIMP AND CONTROLLER IF POSSIBLE EXCEPTIONS