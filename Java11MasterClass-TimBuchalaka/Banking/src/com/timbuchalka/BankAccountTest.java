package com.timbuchalka;

import org.junit.After;

import static org.junit.Assert.*;

public class BankAccountTest {

    private BankAccount account;
    private static int count;

    @org.junit.BeforeClass
    public static void beforeClass() {
        System.out.println("this execute before any test cases, Count=" + count);
    }

    @org.junit.Before
    public void setup() {
        account = new BankAccount("Tim", "Buchalka", 1000.00, BankAccount.SAVINGS);
        System.out.println("Running a test...");
    }

    @org.junit.Test
    public void deposit() {
        double balance = account.deposit(200.00, true);
        assertEquals(1200, balance, 0);
    }

    @org.junit.Test
    public void withdraw() {
        try {
            account.withdraw(600.00,false);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void getBalance() {
        account.deposit(200.00, true);
        assertEquals(1200.00, account.getBalance(), 0);
    }

    @org.junit.Test
    public void isChecking_true() {
        assertFalse(account.isChecking());
    }

    @org.junit.AfterClass
    public static void afterClass() {
        System.out.println("this execute after any test cases.Count =" + count);
    }

    @org.junit.After
    public void teardown() {
        System.out.println("Count = " + (count++));
    }

}