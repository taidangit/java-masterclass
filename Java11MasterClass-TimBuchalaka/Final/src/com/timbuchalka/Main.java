package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        SomeClass one = new SomeClass("one");
        SomeClass two = new SomeClass("two");
        SomeClass three = new SomeClass("three");

        System.out.println(one.getInstanceNumber());
        System.out.println(two.getInstanceNumber());
        System.out.println(three.getInstanceNumber());

        System.out.println(Math.PI);

        int password=12345;
        Password password1=new ExtendedPassword(password);
        password1.storePassword();

        password1.letMeIn(1);
        password1.letMeIn(12345);
        password1.letMeIn(-1);
        password1.letMeIn(0);
        password1.letMeIn(42342);


    }
}
