package com.timbuchalka;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> items=new ArrayList();
        items.add(1);
        items.add(2);
        items.add(3);
        //items.add("Tai");
        items.add(4);
        items.add(5);

        printDoubled(items);

    }

    private static void printDoubled(List<Integer> n) {
        for(int i : n) {
            System.out.println(i*2);
        }
    }
}
