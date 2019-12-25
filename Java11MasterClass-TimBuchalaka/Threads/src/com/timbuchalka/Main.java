package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        System.out.println(ThreadColor.ANSI_PURPLE + "Hello from the main thread.");

        Thread anotherThread = new AnotherThread();
        anotherThread.setName("Another Thread");
        anotherThread.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_GREEN + "Hello from the anonymous class thread.");
            }
        }.start();

        Thread myRunableThread = new Thread(new MyRunable() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_RED + "Hello from the anonymous class's implemention of run");
                try {
                    anotherThread.join();
                    System.out.println(ThreadColor.ANSI_RED+"Another Thread Terminated.So I'm running again.");
                } catch (InterruptedException e) {
                    System.out.println(ThreadColor.ANSI_RED+"I could not wait after all. I was interrupted");
                }
            }
        });

        myRunableThread.start();

        System.out.println(ThreadColor.ANSI_PURPLE + "Hello again from the main thread.");

    }
}
