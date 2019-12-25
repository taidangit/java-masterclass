package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        ITelephone taiPhone;

        taiPhone = new DeskPhone(123456);
        taiPhone.powerOn();
        taiPhone.calPhone(123456);
        taiPhone.answer();

        taiPhone = new MobilePhone(242354);
        taiPhone.powerOn();
        taiPhone.calPhone(242354);
        taiPhone.answer();
    }
}
