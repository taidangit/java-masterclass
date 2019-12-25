package com.timbuchalka;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Button btnPrint = new Button("print");

    public static void main(String[] args) {
        /*Gearbox mcLaren=new Gearbox(6);
        Gearbox.Gear first=mcLaren.new Gear(1,12.3);
        System.out.println(first.driveSpeed(1000));*/
/*
        Gearbox mcLaren=new Gearbox(6);

        mcLaren.operateClutch(true);
        mcLaren.changeGear(1);
        mcLaren.operateClutch(false);
        System.out.println(mcLaren.wheelSpeed(1000));
        mcLaren.changeGear(2);
        System.out.println(mcLaren.wheelSpeed(3000));*/

       /* class ClickListener implements Button.OnClickListener {

            public ClickListener() {
                System.out.println("I have been attached");
            }

            @Override
            public void onClick(String title) {
                System.out.println(title+" was clicked");
            }
        }

        btnPrint.setOnclickListener(new ClickListener());*/

        btnPrint.setOnclickListener(new Button.OnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println(title + " was clicked");
            }
        });
        listen();

    }

    private static void listen() {
        boolean quit = false;

        while (!quit) {
            System.out.println("Enter choice:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    btnPrint.onClick();
                    break;
            }
        }
    }
}
