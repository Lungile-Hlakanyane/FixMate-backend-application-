package com.fixmate.backend_application.dto;
import java.time.LocalDateTime;

public class CardResponseDTO {

    private Long id;
    private String cardHolder;
    private String cvv;
    private String expiryDate;
    private String cardNumber;
    private String bankName;
    private String accountNumber;
    private Long userId;
    private LocalDateTime createdAt;

    public CardResponseDTO() {}

    public CardResponseDTO(Long id, String cardHolder, String cvv,
                           String expiryDate, String cardNumber, String bankName,
                           String accountNumber, Long userId, LocalDateTime createdAt) {
        this.id = id;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardNumber = cardNumber;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
