package com.timbuchalka;

public class VipPerson {
    private String name;
    private double creditLimit;
    private String emailAddress;

    public VipPerson() {
        this("Default name", 50000.0, "default@email.com");
    }

    public VipPerson(String name, double creditLimit) {
        this(name, creditLimit, "unknown@email.com");
    }

    public VipPerson(String name, double creditLimit, String emailAddress) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "VipPerson{" +
                "name='" + name + '\'' +
                ", creditLimit=" + creditLimit +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
