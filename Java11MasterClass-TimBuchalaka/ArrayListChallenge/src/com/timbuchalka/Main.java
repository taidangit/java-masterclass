package com.timbuchalka;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static MobilePhone mobilePhone = new MobilePhone("0328 130 248");

    public static void main(String[] args) {

        boolean quit = false;
        startPhone();
        printActions();

        while (!quit) {
            System.out.println("Enter action:");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Shutting down...");
                    quit = true;
                    break;
                case 1:
                    mobilePhone.printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printActions();
                    break;

            }
        }
    }

    private static void addNewContact() {
        System.out.println("Enter new contact name:");
        String name = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phone = scanner.nextLine();

        Contact contact = new Contact(name, phone);
        if (mobilePhone.addNewContact(contact)) {
            System.out.println("New contact added: name = " + name + ", phone = " + phone);
        } else {
            System.out.println("Cannot add, " + name);
        }
    }

    private static void updateContact() {
        System.out.println("Enter existing contact name:");
        String name = scanner.nextLine();
        Contact existingContact = mobilePhone.queryContact(name);
        if (existingContact == null) {
            System.out.println("Contact not found");
            return;
        }

        System.out.println("Enter new contact name:");
        String newName = scanner.nextLine();
        System.out.println("Enter new contact phone:");
        String newPhone = scanner.nextLine();
        Contact newContact = new Contact(newName, newPhone);
        if (mobilePhone.updateContact(existingContact, newContact)) {
            System.out.println("Successfully updated record.");
        } else {
            System.out.println("Error updating record.");
        }
    }

    private static void removeContact() {
        System.out.println("Enter existing contact name:");
        String name = scanner.nextLine();
        Contact existingContact = mobilePhone.queryContact(name);
        if (existingContact == null) {
            System.out.println("Contact not found");
            return;
        }

        if (mobilePhone.removeContact(existingContact)) {
            System.out.println("Successfully deleted");
        } else {
            System.out.println("Error deleting contact");
        }
    }

    private static void queryContact() {
        System.out.println("Enter existing contact name:");
        String name = scanner.nextLine();
        Contact existingContact = mobilePhone.queryContact(name);
        if (existingContact == null) {
            System.out.println("Contact not found");
            return;
        }

        System.out.println("Name: " + existingContact.getName() + ", phone number: " + existingContact.getPhoneNumber());
    }

    private static void startPhone() {
        System.out.println("Starting phone...");
    }

    private static void printActions() {
        System.out.println("\nPress");
        System.out.println("\t 0 - To shutdown");
        System.out.println("\t 1 - To print contacts");
        System.out.println("\t 2 - To add a new contact");
        System.out.println("\t 3 - To update an existing contact");
        System.out.println("\t 4 - To remove a contact");
        System.out.println("\t 5 - query a contact");
        System.out.println("\t 6 - To print a list of available");
    }
}
