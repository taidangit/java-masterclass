package com.timbuchalka;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player tai=new Player("Tai",10,15);
        System.out.println(tai);
        savedObject(tai);

        tai.setHitPoints(8);
        System.out.println(tai);
        tai.setWeapon("Stormbringer");
        savedObject(tai);
        //loadObject(tai);
        //System.out.println(tai);

        ISaveable wereOlf=new Monster("Weewloft", 20,40);
        System.out.println(((Monster) wereOlf).getName());
        System.out.println(wereOlf);
        savedObject(wereOlf);
    }

    public static List<String> readValues() {
        List<String> values = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int index = 0;
        printMenu();

        while (!quit) {
            System.out.println("Enter choice:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    System.out.println("Enter a string:");
                    String strInput = scanner.nextLine();
                    values.add(index, strInput);
                    index++;
                    break;
            }
        }
        return values;
    }

    public static void printMenu() {
        System.out.println("\nPress");
        System.out.println("\t 0-to quit");
        System.out.println("\t 1-to enter a string");
    }

    public static void savedObject(ISaveable objectSave) {
        for (int i = 0; i < objectSave.write().size(); i++) {
            System.out.println("Saving " + objectSave.write().get(i) + " to storage device.");
        }
    }

    public static void loadObject(ISaveable objectToLoad) {
        List<String> values=readValues();
        objectToLoad.read(values);
    }
}
