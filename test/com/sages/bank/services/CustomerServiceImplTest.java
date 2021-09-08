package com.sages.bank.services;

import com.sages.bank.entity.Account;
import com.sages.bank.entity.Customer;
import com.sages.bank.entity.RetailCustomer;
import com.sages.bank.entity.SavingsAccount;
import com.sages.bank.exceptions.BankException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {
    private CustomerService customerService;
    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl();
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
                ()->customerService.openAccount(null,new SavingsAccount(BigDecimal.ZERO)));
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
            Account iseSavings = new SavingsAccount(BigDecimal.valueOf(500000));
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
            Account iseSavings = new SavingsAccount(BigDecimal.valueOf(500000));
        } catch (BankException e) {
            e.printStackTrace();
        }
    }
}