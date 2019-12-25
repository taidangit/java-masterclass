package com.timbuchalka;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = 0;
        int sum = 0;
        while (true) {
            int order = count + 1;
            System.out.println("Enter number #" + order + ":");
            boolean isAnInt = scanner.hasNextInt();

            if (isAnInt) {
                int number = scanner.nextInt();
                count++;
                sum += number;
                if (count == 5) {
                    break;
                }
            } else {
                System.out.println("Invalid number");
                break;
            }
        }
        System.out.println("sum = "+sum);
        scanner.close();
    }
}
