package com.fixmate.backend_application.dto;
import com.fixmate.backend_application.enums.Role;
import com.fixmate.backend_application.enums.UserStatus;

import java.time.LocalDateTime;

public class UserResponse {

    public Long id;
    public String fullName;
    public String email;
    public Role role;
    public String phone;
    public String location;
    public Boolean verified;
    public Boolean active;
    public String idNumber;
    private UserStatus status;
    public LocalDateTime createdAt;

    //for Pro
    private String occupation;
    //for pro
    private Boolean available;
    //for pro
    private String biography;


    public UserResponse() {}

    public UserResponse(Long id, String fullName, String email, Role role,
                        Boolean verified, Boolean active,String idNumber,
                        UserStatus status, LocalDateTime createdAt,
                        String occupation, Boolean available, String biography) {
        this.id = id;
        this.fullName = fullName;
        this.email=email;
        this.role=role;
        this.verified=verified;
        this.active=active;
        this.idNumber = idNumber;
        this.createdAt=createdAt;
        this.status = status;
        this.occupation = occupation;
        this.available = available;
        this.biography = biography;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public UserStatus getStatus() { return status;}

    public void setStatus(UserStatus status) {this.status = status;}

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
