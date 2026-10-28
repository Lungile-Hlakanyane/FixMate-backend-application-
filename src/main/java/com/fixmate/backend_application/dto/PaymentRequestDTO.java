package com.fixmate.backend_application.dto;
import com.fixmate.backend_application.enums.TypeOfPayment;

import java.time.LocalDateTime;

public class PaymentRequestDTO {

    private Long id;
    private Long jobPaidId;
    private Long customerId;
    private Long proId;
    private TypeOfPayment typeOfPayment;
    private Double amount;
    private LocalDateTime dateOfPayment;
    public PaymentRequestDTO() {}

    public PaymentRequestDTO(Long id, Long jobPaidId, Long customerId, Long proId,
                             TypeOfPayment typeOfPayment, Double amount, LocalDateTime dateOfPayment) {
        this.id = id;
        this.jobPaidId = jobPaidId;
        this.customerId = customerId;
        this.proId = proId;
        this.typeOfPayment = typeOfPayment;
        this.dateOfPayment = dateOfPayment;
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

    public LocalDateTime getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDateTime dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }
}
