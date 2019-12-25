package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
	    Bank bank=new Bank("National Australia");

	    bank.addBranch("Adelaide");

	    bank.addCustomer("Adelaide","Tim",50.05);
        bank.addCustomer("Adelaide","Mike",175.34);
        bank.addCustomer("Adelaide","Percy",220.12);
        bank.addBranch("Sydney");

        bank.addCustomer("Sydney","Bob",150.54);

        bank.addCustomerTransaction("Adelaide","Tim",44.22);
        bank.addCustomerTransaction("Adelaide","Tim",12.44);
        bank.addCustomerTransaction("Adelaide","Mike",1.65);

        bank.listCustomer("Adelaide",true);
        bank.listCustomer("Sydney",true);

        bank.addBranch("Melbourbe");

        if(!bank.addCustomer("Melbourbe","Brian",5.53)) {
            System.out.println("Error Melbourne branch doest not exists.");
        }

        if(!bank.addCustomer("Adelaide","Tim",12.21)){
            System.out.println("Customer Tim already exists.");
        }
    }
}
