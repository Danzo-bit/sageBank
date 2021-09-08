package com.sages.bank.services;

import com.sages.bank.entity.Account;
import com.sages.bank.entity.Transaction;
import com.sages.bank.enums.TransactionType;
import com.sages.bank.exceptions.BankTransactionException;

import java.math.BigDecimal;

public class TransactionServiceImpl implements TransactionService{

    @Override
    public BigDecimal addTransaction(Account account, Transaction tx) throws BankTransactionException {
        BigDecimal newBalance = null;
        if (tx.getTransactionType().equals(TransactionType.CREDIT)){
            newBalance=account.getBalance().add(tx.getAmount());
            account.setBalance(newBalance);
        }
        return newBalance;
    }
}
