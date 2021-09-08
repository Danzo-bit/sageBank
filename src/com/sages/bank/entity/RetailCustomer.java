package com.sages.bank.entity;

import com.sages.bank.repository.Customerdb;

public class RetailCustomer extends Customer{
    public RetailCustomer(String firstName, String lastName) {
        this.setBvn(Customerdb.generateBvn());
        this.setFirstName(firstName);
        this.setLastName(lastName);

    }

    public RetailCustomer(long bvn, String firstName, String lastName, String email){
        this.setBvn(bvn);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
    }

    public RetailCustomer(long bvn, String firstName, String lastName) {
        this.setBvn(bvn);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }
}
