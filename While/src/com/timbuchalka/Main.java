package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        int count = 1;
        while (count != 6) {
            System.out.println("Count value = " + count);
            count++;
        }
        System.out.println("=============================");
        count = 1;
        while (true) {
            if (count == 6) {
                break;
            }
            System.out.println("Count value = " + count);
            count++;
        }

        System.out.println("===============================");
        count = 1;
        do {
            System.out.println("Count value = " + count);
            count++;
        } while (count != 6);

        int number = 4;
        int finishNumber = 20;
        int evenNumbersFound = 0;

        System.out.println("===============================");

        while (number <= finishNumber) {
            number++;
            if (!isEvenNumber(number)) {
                continue;
            }

            System.out.println("Even number = " + number);

            evenNumbersFound++;
            if (evenNumbersFound >= 5) {
                break;
            }
        }

        System.out.println("Total even numbers found = " + evenNumbersFound);
    }


    private static boolean isEvenNumber(int number) {
        if (number % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
