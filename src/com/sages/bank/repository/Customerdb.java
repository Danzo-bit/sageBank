package com.sages.bank.repository;

import com.sages.bank.entity.Customer;
import com.sages.bank.entity.RetailCustomer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Customerdb {
    private static Map<Long, Customer> customers = new HashMap<>();
    private static long currentBvn = 10001100000L;
    private static long currentAccountNumber = Long.valueOf(0);
    private static long currentTransactionId = Long.valueOf(0);

    public static long getCurrentTransactionId() {
        currentTransactionId++;
        return currentTransactionId;
    }

    static {
        Customer ise = new RetailCustomer("iseoluwa","Fasoyin");
        addCustomer(ise);
        Customer kelvin = new RetailCustomer("Kelvin","Okoro");
        addCustomer(kelvin);
        Customer janet = new RetailCustomer("Janet","Badmus");
        addCustomer(janet);
    }


    public  static void addCustomer(Customer customer){
        customers.put(customer.getBvn(),customer);
    }

    public static Map<Long, Customer> getCustomers() {
        return customers;
    }

    private void setCustomers(Map<Long, Customer> customers) {
        Customerdb.customers = customers;
    }

    public static long generateBvn(){
        currentBvn++;
        return currentBvn;
    }

    public static long generateAccountNumber(){
        currentAccountNumber++;
        return currentAccountNumber;
    }

}
