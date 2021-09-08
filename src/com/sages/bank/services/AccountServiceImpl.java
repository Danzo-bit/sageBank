package com.sages.bank.services;

import com.sages.bank.entity.Account;
import com.sages.bank.entity.Transaction;
import com.sages.bank.enums.TransactionStatus;
import com.sages.bank.enums.TransactionType;
import com.sages.bank.exceptions.BankTransactionException;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {

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
        tx.setStatus(TransactionStatus.SUCCESSFUL);
        account.getTransaction().add(tx);
        return newBalance;
    }

    private void validateTransaction(Account account ,Transaction tx) throws BankTransactionException {
        if (account == null || tx == null){
            throw new BankTransactionException("Account is reqired");
        }
        tx.setStatus(TransactionStatus.PENDING);
        if (tx.getAmount().compareTo(BigDecimal.ZERO)<BigDecimal.ZERO.intValue()){
            tx.setStatus(TransactionStatus.FAILED);
            throw new BankTransactionException("Credit amount cannot be negative");
        }
    }
}
