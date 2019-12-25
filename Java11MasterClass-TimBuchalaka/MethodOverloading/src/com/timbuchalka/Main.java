package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        int newScore = calculateScore("Tai", 500);
        System.out.println("New score is " + newScore);

        calculateScore(75);

        calculateScore();

        double centimeters = calcFeetAbdInchesToCentimeters(6, -10);
        if (centimeters < 0.0) {
            System.out.println("Invalid parameters");
        }

        System.out.println(calcFeetAbdInchesToCentimeters(156));
    }

    public static int calculateScore(String playerName, int score) {
        System.out.println("Player " + playerName + " scored " + score);
        return score * 10000;
    }

    public static int calculateScore(int score) {
        System.out.println("Unnamed player scored " + score + " points");
        return score * 10000;
    }

    public static int calculateScore() {
        System.out.println("No player name, no player score");
        return 0;
    }

    public static double calcFeetAbdInchesToCentimeters(double feet, double inches) {
        if ((feet < 0) || (inches < 0) || (inches > 12)) {
            System.out.println("Invalid feet or inches parameters");
            return -1;
        }

        double centimeters = (feet * 12) * 2.54;
        centimeters += inches * 2.54;
        System.out.println(feet + " feet, " + inches + " inches = " + centimeters + " cm");
        return centimeters;
    }

    public static double calcFeetAbdInchesToCentimeters(double inches) {
        if (inches < 0) {
            System.out.println("Invalid feet or inches parameters");
            return -1;
        }

        double feet =  inches / 12;
        double remainingInches =  inches % 12;

        System.out.println(inches + " inches is equal to " + feet + " feet and " + remainingInches + " inches");
        return calcFeetAbdInchesToCentimeters(feet, inches);
    }
}
