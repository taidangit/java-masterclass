package com.timbuchalka;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = {1, 5, 3, 7, 11, 9, 15, 9};
        System.out.println("Array = " + Arrays.toString(array));

        reverse(array);
        System.out.println("Reversed Array = " + Arrays.toString(array));

    }

    private static void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
}
