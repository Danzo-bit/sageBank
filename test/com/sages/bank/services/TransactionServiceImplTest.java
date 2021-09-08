package com.sages.bank.services;

import com.sages.bank.entity.Account;
import com.sages.bank.entity.SavingsAccount;
import com.sages.bank.entity.Transaction;
import com.sages.bank.enums.TransactionType;
import com.sages.bank.exceptions.BankTransactionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceImplTest {
    TransactionServiceImpl tx;
    @BeforeEach
    void setUp() {
        tx = new TransactionServiceImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void addTransaction() {
        Account johnSavings = new SavingsAccount(BigDecimal.valueOf(10000));
        assertEquals(10000,johnSavings.getBalance().intValue());
        Transaction initialDeposit = new Transaction(BigDecimal.valueOf(5000), TransactionType.CREDIT);

        try {
            BigDecimal newBalance = tx.addTransaction(johnSavings,initialDeposit);
            assertEquals(15000, johnSavings.getBalance().intValue());
        } catch (BankTransactionException e) {
            e.printStackTrace();
        }

    }
}