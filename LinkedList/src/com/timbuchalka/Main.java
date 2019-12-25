package com.timbuchalka;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer("Tai", 54.96);

        Customer anotherCutomer;
        anotherCutomer = customer;
        anotherCutomer.setBalance(12.18);

        System.out.println("Balance for customer " + customer.getName() + " is " + customer.getBalance());


        List<Integer> intList = new ArrayList<>();

        intList.add(1);
        intList.add(3);
        intList.add(4);

        for (int i = 0; i < intList.size(); i++) {
            System.out.print(intList.get(i) + "\t");
        }
        System.out.println("\n===================================");
        intList.add(1, 2);
        for (int i = 0; i < intList.size(); i++) {
            System.out.print(intList.get(i) + "\t");
        }
    }
}
