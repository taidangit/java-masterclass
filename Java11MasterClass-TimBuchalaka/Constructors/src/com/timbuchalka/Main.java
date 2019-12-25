package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        Account bobAccount=new Account(
                "12345",
                0.0,
                "Bob Brown",
                "myemail@bob.com",
                "(087) 123-4567");

        Account bobAccount1 = new Account();
        System.out.println(bobAccount);

        bobAccount.withdrawal(100.0);

        bobAccount.deposit(50.0);
        bobAccount.withdrawal(100.0);

        bobAccount.deposit(51.0);
        bobAccount.withdrawal(100.0);

        Account timsAccount = new Account("Tim", "tim@email.com", "12345");
        System.out.println(timsAccount);

        VipPerson person1 = new VipPerson();
        System.out.println(person1);

        VipPerson person2 = new VipPerson("Bob", 25000.0);
        System.out.println(person2);

        VipPerson person3 = new VipPerson("Tim", 100.0, "tim@email.com");
        System.out.println(person3);
    }
}
