package com.timbuchalka;

public class Motherboard {

    private String model;
    private String manufatures;
    private int ramSlots;
    private String bios;

    public Motherboard(String model, String manufatures, int ramSlots, String bios) {
        this.model = model;
        this.manufatures = manufatures;
        this.ramSlots = ramSlots;
        this.bios = bios;
    }

    public void loadProgram(String programName) {
        System.out.println("Program " + programName + " is now loading...");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufatures() {
        return manufatures;
    }

    public void setManufatures(String manufatures) {
        this.manufatures = manufatures;
    }

    public int getRamSlots() {
        return ramSlots;
    }

    public void setRamSlots(int ramSlots) {
        this.ramSlots = ramSlots;
    }

    public String getBios() {
        return bios;
    }

    public void setBios(String bios) {
        this.bios = bios;
    }
}
