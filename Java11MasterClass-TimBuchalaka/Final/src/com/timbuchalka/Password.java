package com.timbuchalka;

public class Password {
    private static final int KEY = 2342423;
    private final int encryptedPassword;

    public Password(int password) {
        this.encryptedPassword = encryptDecrypt(password);
    }

    private int encryptDecrypt(int password) {
        return password ^ KEY;
    }

    public void storePassword() {
        System.out.println("Saving password as " + this.encryptedPassword);
    }

    public boolean letMeIn(int password) {
        if (encryptDecrypt(password) == this.encryptedPassword) {
            System.out.println("Welcome");
            return true;
        } else {
            System.out.println("Nope, you can not come in.");
            return false;
        }
    }


}
