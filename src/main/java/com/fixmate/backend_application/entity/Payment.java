package com.fixmate.backend_application.entity;
import com.fixmate.backend_application.enums.TypeOfPayment;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name="job_paid_id")
    private Long jobPaidId;

    @Column(nullable = false, name="date_of_payment")
    private LocalDateTime dateOfPayment;

    @Column(name="customer_id", nullable = false)
    private Long customerId;

    @Column(nullable = false, name="pro_id")
    private Long proId;

    @Enumerated(EnumType.STRING)
    @Column(name="type_of_payment", nullable = false)
    private TypeOfPayment typeOfPayment;

    @Column(name="amount", nullable = false)
    private Double amount;

    @PrePersist
    public void prePersist(){
     if(this.dateOfPayment == null){
         this.dateOfPayment = LocalDateTime.now();
     }
    }
    public Payment() {}

    public Payment(Long id, Long jobPaidId, LocalDateTime dateOfPayment,
                   Long customerId, Long proId, TypeOfPayment typeOfPayment, Double amount) {
        this.id = id;
        this.jobPaidId = jobPaidId;
        this.dateOfPayment = dateOfPayment;
        this.customerId = customerId;
        this.proId = proId;
        this.typeOfPayment = typeOfPayment;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobPaidId() {
        return jobPaidId;
    }

    public void setJobPaidId(Long jobPaidId) {
        this.jobPaidId = jobPaidId;
    }

    public LocalDateTime getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDateTime dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
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

    public TypeOfPayment getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(TypeOfPayment typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
