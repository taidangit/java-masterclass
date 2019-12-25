package com.timbuchalka;

public class ExtendedPassword extends Password {
    private int decryptPassword;

    public ExtendedPassword(int password) {
        super(password);
        this.decryptPassword = password;
    }

    @Override
    public void storePassword() {
        System.out.println("Saving password as " + this.decryptPassword);
    }
}
