package com.timbuchalka;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Double> transactions;

    public Customer(String name, double initialAmount) {
        this.name = name;
        this.transactions=new ArrayList<>();
        addTransaction(initialAmount);
    }

    public void addTransaction(double amount) {
        this.transactions.add(amount);
    }

    public String getName() {
        return name;
    }

    public List<Double> getTransactions() {
        return transactions;
    }
}
