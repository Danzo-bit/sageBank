package com.sages.bank.services;

import com.sages.bank.entity.Account;
import com.sages.bank.entity.Customer;
import com.sages.bank.exceptions.BankException;

public interface CustomerService {
    Customer findCustomer(long bvn) throws BankException;
    Customer openAccount(Customer customer, Account account) throws BankException;
    boolean addAccount(Customer customer, Account account) throws BankException;
}
