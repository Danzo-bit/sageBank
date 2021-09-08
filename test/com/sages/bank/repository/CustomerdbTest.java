package com.sages.bank.repository;

import com.sages.bank.entity.Customer;
import com.sages.bank.entity.RetailCustomer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerdbTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCustomer(){
        assertFalse(Customerdb.getCustomers().isEmpty());
        Customer customer = new RetailCustomer("john","johnson");
        Customerdb.addCustomer(customer);
        assertFalse(Customerdb.getCustomers().isEmpty());
        assertEquals(4,Customerdb.getCustomers().size());
    }
}