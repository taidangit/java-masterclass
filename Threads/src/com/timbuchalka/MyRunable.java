package com.timbuchalka;

public class MyRunable implements Runnable {
    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_RED + "Hello from MyRunable's implemention of run");
    }
}
