package com.timbuchalka;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter number:");
        int number = scanner.nextInt();

        int[] returnedArray = getIntegers(number);
        int returnesMin = findMin(returnedArray);
        System.out.println("min=" + returnesMin);
    }

    private static int[] getIntegers(int number) {
        int[] array = new int[number];

        for (int i = 0; i < array.length; i++) {
            System.out.print("array[" + i + "]=");
            array[i] = scanner.nextInt();
        }
        return array;
    }

    private static int findMin(int[] array) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
}
