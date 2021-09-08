package com.sages.bank.entity;

import com.sages.bank.enums.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private Long accountNumber;

    private BigDecimal balance;
    private List<Transaction> transactions = new ArrayList<>();

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void addTransaction(Transaction tx){
       this.getTransaction().add(tx);

    }
    public List<Transaction> getTransaction(){
        return transactions;
    }
}
