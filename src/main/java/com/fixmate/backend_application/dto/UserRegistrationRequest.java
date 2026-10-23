package com.fixmate.backend_application.dto;
import com.fixmate.backend_application.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

public class UserRegistrationRequest {

    @NotBlank
    private String fullName;

    @Email @NotBlank
    private String email;

    @NotBlank @Size(min=6)
    private String password;

    @NotNull
    private Role role;

    private Boolean active = false;
    private Boolean verified = false;

    @NotBlank
    private String phone;
    public String location;

    @NotBlank
    private String idNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    //for Pro
    private String occupation;
    //for pro
    private Boolean available;
    //for pro
    private String biography;


    public UserRegistrationRequest() {}

    public UserRegistrationRequest(String fullName, String email,
                                   String password, Role role, String phone, String location, String idNumber,
                                   LocalDateTime createdAt, Boolean verified, Boolean active,
                                   String occupation, Boolean available, String biography) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.location = location;
        this.idNumber = idNumber;
        this.createdAt = createdAt;
        this.active = active;
        this.verified = verified;
        this.occupation = occupation;
        this.available = available;
        this.biography = biography;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
