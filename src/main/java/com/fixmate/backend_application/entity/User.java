package com.fixmate.backend_application.entity;
import com.fixmate.backend_application.enums.Role;
import com.fixmate.backend_application.enums.UserStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; //BCryt hashed

    @Enumerated(EnumType.STRING)
    @Column(nullable=true)
    private Role role;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String location;

    @Column(nullable = true, unique = true)
    private String idNumber;

    private Boolean verified = false;

    private Boolean active = false;

    //default set to ACTIVE After user register in the application
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @CreationTimestamp
    private LocalDateTime createdAt;

    //for Pro
    @Column(nullable = true)
    private String occupation;

    //for pro
    @Column(nullable = true)
    private Boolean available;

    //for pro
    @Column(nullable = true)
    private String biography;

    public User() {}

    public User(Long id, Boolean active, Boolean verified,
                String idNumber, String location, LocalDateTime createdAt,
                String phone, Role role, String password, String email,UserStatus
                        status, String fullName, String occupation, Boolean available,
                String biography) {
        this.id = id;
        this.active = active;
        this.verified = verified;
        this.idNumber = idNumber;
        this.location = location;
        this.createdAt = createdAt;
        this.phone = phone;
        this.role = role;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
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

    public LocalDateTime getCreatedAt() { return createdAt;}

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserStatus getStatus() {return status;}

    public void setStatus(UserStatus status) {this.status = status;}

    public String getOccupation() {return occupation;}

    public void setOccupation(String occupation) {this.occupation = occupation;}

    public Boolean getAvailable() {return available;}

    public void setAvailable(Boolean available) {this.available = available;}

    public String getBiography() {return biography;}

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void markAsActive(){this.active = true;}

    public void markAsVerified(){this.verified = true;}

    //call this method in the user service to ban the active user
    public void markAsBanned(){this.status= UserStatus.BANNED;}

    //call this method in the user service to un-ban the banned user
    public void markAsUnbanned(){this.status = UserStatus.ACTIVE;}

    @PrePersist
    @PreUpdate
    public void handleAvailableLogic(){
        if(this.role == Role.PRO){
            if(this.available == null){
                this.available = false;
            }
        }else{
            this.available = null;
        }
    }
}
