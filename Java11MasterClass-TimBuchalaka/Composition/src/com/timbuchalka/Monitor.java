package com.timbuchalka;

public class Monitor {

    private String model;
    private String manufactures;
    private int size;
    private Resolution nativeResolution;

    public Monitor(String model, String manufactures, int size, Resolution nativeResolution) {
        this.model = model;
        this.manufactures = manufactures;
        this.size = size;
        this.nativeResolution = nativeResolution;
    }

    public void drawPixelAt(int x, int y, String color) {
        System.out.println("Drawing pixel at " + x + "," + y + " in colour " + color);
    }
}
