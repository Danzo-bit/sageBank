package com.sages.bank.services;

import com.sages.bank.entity.Account;
import com.sages.bank.entity.Transaction;
import com.sages.bank.enums.TransactionType;
import com.sages.bank.exceptions.BankTransactionException;

import java.math.BigDecimal;

public class TransactionServiceImpl implements TransactionService{

    @Override
    public BigDecimal addTransaction(Account account, Transaction tx) throws BankTransactionException {
        validateTransaction(account, tx);
        BigDecimal newBalance = null;
        if (tx.getTransactionType().equals(TransactionType.CREDIT)){
            newBalance=account.getBalance().add(tx.getAmount());
        }else if(tx.getTransactionType().equals(TransactionType.DEBIT)){
            newBalance = account.getBalance().subtract(tx.getAmount());
        }
        account.setBalance(newBalance);
        account.getTransaction().add(tx);
        return newBalance;
    }

    private void validateTransaction(Account account ,Transaction tx) throws BankTransactionException {
        if (account == null || tx == null){
            throw new BankTransactionException("Account is reqired");

        }
        if (tx.getTransactionType().equals(TransactionType.CREDIT)
                && tx.getAmount().compareTo(BigDecimal.ZERO)<BigDecimal.ZERO.intValue()){
            throw new BankTransactionException("Credit amount cannot be negative");
        }
    }
}
