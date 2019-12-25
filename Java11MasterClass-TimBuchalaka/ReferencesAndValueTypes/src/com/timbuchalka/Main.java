package com.timbuchalka;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int myInt = 10;
        int anotherInt = myInt;

        System.out.println("myInt = " + myInt);
        System.out.println("anotherInt = " + anotherInt);

        anotherInt++;
        System.out.println("myInt = " + myInt);
        System.out.println("anotherInt = " + anotherInt);

        int[] myIntArray = new int[5]
        int[] anotherArray = myIntArray;;
        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));

        myIntArray[0] = 1;
        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));

        modifyArray(anotherArray);
        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));
    }

    private static void modifyArray(int[] array) {
        array[0] = 2;
    }
}
