package com.timbuchalka;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<>();
    }

    public boolean addBranch(String branchName) {
        if (findBranch(branchName) == null) {
            this.branches.add(new Branch(branchName));
            return true;
        }
        return false;
    }

    public boolean addCustomer(String branchName, String customerName, double initialAmount) {
        Branch branch = findBranch(branchName);
        if (branch != null) {
            return branch.newCustomer(customerName, initialAmount);
        }
        return false;
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double amount) {
        Branch branch = findBranch(branchName);
        if (branch != null) {
            return branch.addCustomerTransaction(customerName, amount);
        }
        return false;
    }

    private Branch findBranch(String branchName) {
        for (int i = 0; i < this.branches.size(); i++) {
            if (this.branches.get(i).getName().equals(branchName)) {
                return this.branches.get(i);
            }
        }
        return null;
    }

    public boolean listCustomer(String branchName, boolean showTransactions) {
        Branch branch = findBranch(branchName);
        if (branch != null) {
            System.out.println("customer details for branch " + branch.getName());

            List<Customer> branchCustomers = branch.getCustomers();
            for (int i = 0; i < branchCustomers.size(); i++) {
                Customer branchCutomer = branchCustomers.get(i);
                System.out.println("Customer: " + branchCutomer.getName() + "[" + (i + 1) + "]");
                if (showTransactions) {
                    System.out.println("Transactions");
                    List<Double> transactions = branchCutomer.getTransactions();
                    for (int j = 0; j < transactions.size(); j++) {
                        System.out.println("[" + (j + 1) + "] Amount " + transactions.get(j));
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
