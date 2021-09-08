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

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {
    AccountServiceImpl accountService;
    Account johnSavings;
    Transaction initialDeposit;
    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl();
       johnSavings = new SavingsAccount();
        initialDeposit = new Transaction(BigDecimal.valueOf(10000),TransactionType.CREDIT);
    }

    @AfterEach
    void tearDown() {
        johnSavings = null;
    }

    @Test
    public void addTransaction() {

        assertEquals(0,johnSavings.getBalance().intValue());
//        Transaction initialDeposit = new Transaction(BigDecimal.valueOf(5000), TransactionType.CREDIT);

        try {
            BigDecimal newBalance = accountService.addTransaction(johnSavings,initialDeposit);
            assertEquals(10000, johnSavings.getBalance().intValue());
        } catch (BankTransactionException e) {
            e.printStackTrace();
        }
        Transaction txHistory = johnSavings.getTransaction().get(0);
        assertEquals(initialDeposit,txHistory);
        assertEquals(BigDecimal.ONE.intValue(),johnSavings.getTransaction().size());
    }

    @Test
    public void addTransactionWithNegativeCredit() {
        assertEquals(0,johnSavings.getBalance().intValue());
//        Transaction initialDeposit = new Transaction(BigDecimal.valueOf(-5000), TransactionType.CREDIT);
        initialDeposit.setAmount(BigDecimal.valueOf(-5000));
        assertThrows(BankTransactionException.class, ()-> accountService.addTransaction(johnSavings,initialDeposit));
        assertEquals(TransactionStatus.FAILED,initialDeposit.getStatus());
        assertEquals(0,johnSavings.getTransaction().size());
    }

    @Test
    public void addTransactionWithLargeCredit() {
        BigDecimal veryLargeCredit = new BigDecimal("10000000000000000000");
        assertEquals(0,johnSavings.getBalance().intValue());
        Transaction initialDeposit = new Transaction(veryLargeCredit, TransactionType.CREDIT);

        try {
            BigDecimal newBalance = accountService.addTransaction(johnSavings,initialDeposit);
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
//        Transaction initialDeposit = new Transaction(BigDecimal.valueOf(-5000), TransactionType.CREDIT);
        assertThrows(BankTransactionException.class, ()-> accountService.addTransaction(null,initialDeposit));
    }

    @Test
     void addTransactionWithNullTransaction() {
        assertEquals(0,johnSavings.getBalance().intValue());
        Transaction initialDeposit = new Transaction(BigDecimal.valueOf(-5000), TransactionType.CREDIT);
        assertThrows(BankTransactionException.class, ()-> accountService.addTransaction(johnSavings,null));
        assertEquals(TransactionStatus.UNPROCESSED,initialDeposit.getStatus());
    }

    @Test
    void addDebitTransaction(){

        try {
            Transaction withdrawal = new Transaction(BigDecimal.valueOf(3000),TransactionType.DEBIT);
            accountService.addTransaction(johnSavings,initialDeposit);
            assertEquals(10000,johnSavings.getBalance().intValue());
            assertEquals(BigDecimal.ONE.intValue(),johnSavings.getTransaction().size());
            BigDecimal ewBalance = accountService.addTransaction(johnSavings,withdrawal);
            assertEquals(7000,johnSavings.getBalance().intValue());
            assertEquals(2,johnSavings.getTransaction().size());
        } catch (BankTransactionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addDebitTransactionWithAmountHigherThanBalance(){
        Transaction withdrawal = new Transaction(BigDecimal.valueOf(12000),TransactionType.DEBIT);
        try {
            accountService.addTransaction(johnSavings,initialDeposit);
            assertEquals(10000,johnSavings.getBalance().intValue());
            assertEquals(BigDecimal.ONE.intValue(),johnSavings.getTransaction().size());
            BigDecimal ewBalance = accountService.addTransaction(johnSavings,withdrawal);
            assertEquals(-2000,johnSavings.getBalance().intValue());
            assertEquals(2,johnSavings.getTransaction().size());
        } catch (BankTransactionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addDebitTransactionWithNegativeDebit(){
            assertEquals(0,johnSavings.getBalance().intValue());
            Transaction withdrawal = new Transaction(BigDecimal.valueOf(-7000),TransactionType.DEBIT);
            assertThrows(BankTransactionException.class,()-> accountService.addTransaction(johnSavings,withdrawal));
            assertTrue(johnSavings.getTransaction().isEmpty());
            assertEquals(TransactionStatus.FAILED,withdrawal.getStatus());
        }
}