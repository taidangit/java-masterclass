package com.timbuchalka;

public class Main {

    private static int multiply = 7;

    public static void main(String[] args) {
        StaticTest firstInstance = new StaticTest("1st instance");
        System.out.println(firstInstance.getName() + " is instance number " + StaticTest.getNumInstances());

        StaticTest secondInstance = new StaticTest("2nd instance");
        System.out.println(secondInstance.getName() + " is instance number " + StaticTest.getNumInstances());

        StaticTest thirdInstance = new StaticTest("3rd instance");
        System.out.println(thirdInstance.getName() + " is instance number " + StaticTest.getNumInstances());

        int answer = multiply(6);
        System.out.println(answer);
        System.out.println(multiply);
    }

    public static int multiply(int number) {
        return number * multiply;
    }
}
