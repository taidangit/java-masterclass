package com.timbuchalka;

public interface ITelephone {

    void powerOn();

    void dial(int phoneNumber);

    void answer();

    boolean calPhone(int phoneNumber);

    boolean isRinging();
}
