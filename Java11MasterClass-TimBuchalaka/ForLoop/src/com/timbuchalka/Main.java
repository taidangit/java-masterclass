package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        System.out.println("10,000 at 2% interest = " + calculateInterest(10000, 2));
        System.out.println("10,000 at 3% interest = " + calculateInterest(10000, 3));
        System.out.println("10,000 at 4% interest = " + calculateInterest(10000, 4));
        System.out.println("10,000 at 5% interest = " + calculateInterest(10000, 5));
        System.out.println("10,000 at 6% interest = " + calculateInterest(10000, 6));

        for (int i = 1; i < 5; i++) {
            System.out.println("Lopp " + i + " hello!");
        }

        System.out.println("==================================================");

        for (int i = 2; i < 9; i++) {
            System.out.println("10,000 at " + i + "% interest = " + String.format("%.2f", calculateInterest(10000, i)));
        }
        System.out.println("==================================================");
        for (int i = 8; i >= 2; i--) {
            System.out.println("10,000 at " + i + "% interest = " + String.format("%.2f", calculateInterest(10000, i)));
        }

        System.out.println("==================================================");

        int count = 0;
        for (int i = 10; i < 50; i++) {
            if (isPrime(i)) {
                count++;
                System.out.println("Number " + i + " is a prime number");
                if (count == 10) {
                    System.out.println("Existing for loop");
                    break;
                }
            }
        }

    }

    private static double calculateInterest(double amount, double interestRate) {
        return (amount * (interestRate / 100));
    }

    private static boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }

        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
