package com.sages.bank.services;

import com.sages.bank.entity.Account;
import com.sages.bank.entity.SavingsAccount;
import com.sages.bank.entity.Transaction;
import com.sages.bank.enums.TransactionStatus;
import com.sages.bank.enums.TransactionType;
import com.sages.bank.exceptions.BankTransactionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceImplTest {
    TransactionServiceImpl txService;
    Account johnSavings;
    @BeforeEach
    void setUp() {
        txService = new TransactionServiceImpl();
       johnSavings = new SavingsAccount(BigDecimal.valueOf(10000));
    }

    @AfterEach
    void tearDown() {
        johnSavings = null;
    }

    @Test
    public void addTransaction() {

        assertEquals(10000,johnSavings.getBalance().intValue());
        Transaction initialDeposit = new Transaction(BigDecimal.valueOf(5000), TransactionType.CREDIT);

        try {
            BigDecimal newBalance = txService.addTransaction(johnSavings,initialDeposit);
            assertEquals(15000, johnSavings.getBalance().intValue());
        } catch (BankTransactionException e) {
            e.printStackTrace();
        }
        Transaction txHistory = johnSavings.getTransaction().get(0);
        assertEquals(initialDeposit,txHistory);
        assertEquals(BigDecimal.ONE.intValue(),johnSavings.getTransaction().size());
    }

    @Test
    public void addTransactionWithNegativeCredit() {
        assertEquals(10000,johnSavings.getBalance().intValue());
        Transaction initialDeposit = new Transaction(BigDecimal.valueOf(-5000), TransactionType.CREDIT);
        assertThrows(BankTransactionException.class, ()-> txService.addTransaction(johnSavings,initialDeposit));
        assertEquals(TransactionStatus.FAILED,initialDeposit.getStatus());
        assertEquals(0,johnSavings.getTransaction().size());
    }

    @Test
    public void addTransactionWithLargeCredit() {
        BigDecimal veryLargeCredit = new BigDecimal("10000000000000000000");
        assertEquals(10000,johnSavings.getBalance().intValue());
        Transaction initialDeposit = new Transaction(veryLargeCredit, TransactionType.CREDIT);

        try {
            BigDecimal newBalance = txService.addTransaction(johnSavings,initialDeposit);
            assertEquals(newBalance, johnSavings.getBalance());
        } catch (BankTransactionException e) {
            e.printStackTrace();
        }
        Transaction txHistory = johnSavings.getTransaction().get(0);


        assertEquals(initialDeposit,txHistory);
        assertEquals(BigDecimal.ONE.intValue(),johnSavings.getTransaction().size());

        assertEquals(TransactionStatus.SUCCESSFUL,txHistory.getStatus());
    }

    @Test
    public void addTransactionWithAccount() {
        Transaction initialDeposit = new Transaction(BigDecimal.valueOf(-5000), TransactionType.CREDIT);
        assertThrows(BankTransactionException.class, ()-> txService.addTransaction(null,initialDeposit));
    }

    @Test
     void addTransactionWithNullTransaction() {
        assertEquals(10000,johnSavings.getBalance().intValue());
        Transaction initialDeposit = new Transaction(BigDecimal.valueOf(-5000), TransactionType.CREDIT);
        assertThrows(BankTransactionException.class, ()-> txService.addTransaction(johnSavings,null));
        assertEquals(TransactionStatus.UNPROCESSED,initialDeposit.getStatus());
    }

    @Test
    void addDebitTransaction(){
        Transaction withdrawal = new Transaction(BigDecimal.valueOf(3000),TransactionType.DEBIT);
        try {
            assertEquals(10000,johnSavings.getBalance().intValue());
            BigDecimal ewBalance = txService.addTransaction(johnSavings,withdrawal);
            assertEquals(7000,johnSavings.getBalance().intValue());
            assertEquals(BigDecimal.ONE.intValue(),johnSavings.getTransaction().size());
        } catch (BankTransactionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addDebitTransactionWithAmountHigherThanBalance(){
        Transaction withdrawal = new Transaction(BigDecimal.valueOf(12000),TransactionType.DEBIT);
        try {
            assertEquals(10000,johnSavings.getBalance().intValue());
            BigDecimal ewBalance = txService.addTransaction(johnSavings,withdrawal);
            assertEquals(-2000,johnSavings.getBalance().intValue());
            assertEquals(BigDecimal.ONE.intValue(),johnSavings.getTransaction().size());
        } catch (BankTransactionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addDebitTransactionWithNegativeDebit(){
            assertEquals(10000,johnSavings.getBalance().intValue());
            Transaction withdrawal = new Transaction(BigDecimal.valueOf(-7000),TransactionType.DEBIT);
            assertThrows(BankTransactionException.class,()->txService.addTransaction(johnSavings,withdrawal));
            assertTrue(johnSavings.getTransaction().isEmpty());
            assertEquals(TransactionStatus.FAILED,withdrawal.getStatus());
        }
}