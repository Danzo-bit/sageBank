package com.sages.bank.entity;

import com.sages.bank.repository.Customerdb;

import java.math.BigDecimal;

public class SavingsAccount extends Account{
    private double interestRate;
    public SavingsAccount(BigDecimal initialDeposit){
        setAccountNumber(Customerdb.generateAccountNumber());
        setBalance(initialDeposit);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
