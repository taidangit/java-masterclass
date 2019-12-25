package com.timbuchalka;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountName;
    private int balance = 0;
    private List<Integer> transactions;

    public Account(String accountName) {
        this.accountName = accountName;
        transactions = new ArrayList<>();
    }


    public void deposit(int amount) {
        if (amount > 0) {
            transactions.add(amount);
            this.balance += amount;
            System.out.println(amount + " deposited.Balance is now " + this.balance);
        } else {
            System.out.println("Cannot deposit negative sums");
        }
    }

    public void withdraw(int amount) {
        int withdrawl = -amount;
        if (withdrawl < 0) {
            this.transactions.add(withdrawl);
            this.balance += withdrawl;
            System.out.println(amount + " withdraw.Balance is now " + this.balance);
        } else {
            System.out.println("Cannot deposit negative sums");
        }
    }

    public void calculateBalance() {
        this.balance = 0;
        for (int i : this.transactions) {
            this.balance += i;
        }
        System.out.println("Caculated balance is " + this.balance);
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<Integer> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Integer> transactions) {
        this.transactions = transactions;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}
