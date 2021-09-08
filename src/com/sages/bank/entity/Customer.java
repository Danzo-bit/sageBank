package com.sages.bank.entity;

import com.sages.bank.repository.Customerdb;

public abstract class Customer {
    private long bvn;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Account savingsAccount;
    private Account currentAccount;


    public long getBvn() {
        return bvn;
    }

    public void setBvn(long bvn) {
        this.bvn = bvn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(Account savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }
}
