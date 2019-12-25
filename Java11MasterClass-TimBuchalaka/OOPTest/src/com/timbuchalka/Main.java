package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        Hamburger hamburger = new Hamburger("Basic", "Sausage", 3.56, "white");

        hamburger.addHamburgerAddition1("Tomato", 0.27);
        hamburger.addHamburgerAddition2("Letture", 0.75);
        hamburger.addHamburgerAddition3("Cheese", 1.12);
        double price = hamburger.itemizeHamburger();
        System.out.println("Total Burger price is " + price);

        HealthyBurger healthyBurger = new HealthyBurger("Bacon", 5.57);
        healthyBurger.addHamburgerAddition1("Egg", 5.43);
        healthyBurger.itemizeHamburger();
        healthyBurger.addHealthAddition1("Lentils", 3.41);
        System.out.println("Total healthy burger price is " + healthyBurger.itemizeHamburger());

        DeluxeBurger deluxeBurger = new DeluxeBurger();
        deluxeBurger.addHamburgerAddition3("Should not do this", 50.53);
        deluxeBurger.itemizeHamburger();
    }
}
