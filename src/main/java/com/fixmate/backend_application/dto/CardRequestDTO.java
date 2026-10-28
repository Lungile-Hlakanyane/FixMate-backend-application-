package com.fixmate.backend_application.dto;
import java.time.LocalDateTime;

public class CardRequestDTO {

    private Long id;
    private String cardHolder;
    private String cvv;
    private String expiryDate;
    private String cardNumber;
    private String bankName;
    private String accountNumber;
    private Long userId;
    private LocalDateTime createdAt;

    public CardRequestDTO(Long id, LocalDateTime createdAt, Long userId, String accountNumber,
                          String bankName, String cardNumber, String expiryDate, String cvv, String cardHolder) {
        this.id = id;
        this.createdAt = createdAt;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardHolder = cardHolder;
    }

    public CardRequestDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
