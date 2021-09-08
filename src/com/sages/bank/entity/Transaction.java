package com.sages.bank.entity;

import com.sages.bank.enums.TransactionStatus;
import com.sages.bank.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private LocalDateTime tDate;
    private BigDecimal amount ;
    private TransactionType transactionType;
    private TransactionStatus status = TransactionStatus.UNPROCESSED;

    public Transaction(BigDecimal txAmount,TransactionType type){
        tDate = LocalDateTime.now();
        this.amount = txAmount;
        this.transactionType = type;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTransactionDate() {
        return tDate;
    }

    public void setTransactionDate(LocalDateTime tDate) {
        this.tDate = tDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
