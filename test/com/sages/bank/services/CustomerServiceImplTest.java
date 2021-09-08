package com.sages.bank.services;

import com.sages.bank.entity.*;
import com.sages.bank.enums.TransactionType;
import com.sages.bank.exceptions.BankException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    private CustomerService customerService;
    private AccountService accountService;
    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl();
        accountService = new AccountServiceImpl();
    }

    @AfterEach
    void tearDown() {
        customerService = null;
    }

    @Test
    @DisplayName("Test that we can find a customer")
    void test1(){
        BigDecimal iseBvn = new BigDecimal(10001100001L);
        try {
            Customer ise = customerService.findCustomer(iseBvn.longValue());
            assertNotNull(ise);
        } catch (BankException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test that you cant find invalid customer")
    void test2(){
        assertThrows(BankException.class,
                ()->customerService.findCustomer(123546374));
    }

    @Test
    void openAccountWithNullCustomer(){
        assertThrows(BankException.class,
                ()->customerService.openAccount(null,new SavingsAccount()));
    }

    @Test
    void openAccountWithNullAccount(){
        Customer john = new RetailCustomer("ise","fashoyin");
        assertThrows(BankException.class,
                ()->customerService.openAccount(john,null));
    }

    @Test
    void openAccount(){
        BigDecimal iseBvn = new BigDecimal(10001100001L);
        try {
            Customer ise = customerService.findCustomer(iseBvn.longValue());
            assertNotNull(ise);
            Account iseSavings = new SavingsAccount();
            Transaction initialDeposit = new Transaction(BigDecimal.valueOf(5000), TransactionType.CREDIT);
            BigDecimal balance = accountService.addTransaction(iseSavings,initialDeposit);
            assertNull(ise.getSavingsAccount());
            customerService.addAccount(ise,iseSavings);
            ise.setSavingsAccount(iseSavings);
            assertNotNull(ise.getSavingsAccount());
        } catch (BankException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addAccount(){
        BigDecimal iseBvn = new BigDecimal(10001100001L);
        try {
            Customer ise = customerService.findCustomer(iseBvn.longValue());
            assertNotNull(ise);
            Account iseSavings = new SavingsAccount();
        } catch (BankException e) {
            e.printStackTrace();
        }
    }
}