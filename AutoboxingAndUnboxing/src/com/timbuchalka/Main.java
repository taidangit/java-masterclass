package com.timbuchalka;

import java.util.ArrayList;
import java.util.List;

class IntClass {

    private int myValue;

    public IntClass(int myValue) {
        this.myValue = myValue;
    }

    public int getMyValue() {
        return myValue;
    }

    public void setMyValue(int myValue) {
        this.myValue = myValue;
    }
}

public class Main {

    public static void main(String[] args) {
        String[] strArray = new String[10];
        int[] intArray = new int[10];

        List<String> strArrayList = new ArrayList<>();
        strArrayList.add("Tai");

        List<IntClass> intClassArrayList = new ArrayList<>();
        intClassArrayList.add(new IntClass(54));

        Integer integer = new Integer(54);
        Double doubleValue = new Double(12.25);

        List<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            integerArrayList.add(Integer.valueOf(i));
        }

        for (int i = 0; i <= 10; i++) {
            System.out.println(i + "==>" + integerArrayList.get(i).intValue());
        }

        System.out.println("==================================================");

        List<Double> myDoubleValue = new ArrayList<>();
        for (double db = 0.0; db <= 10.0; db += 0.5) {
            myDoubleValue.add(Double.valueOf(db));
        }

        for (int i = 0; i < myDoubleValue.size(); i++) {
            System.out.println(i + " ==>" + myDoubleValue.get(i).doubleValue());
        }


    }
}
