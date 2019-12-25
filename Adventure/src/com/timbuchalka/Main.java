package com.timbuchalka;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<Integer, Location> locationMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Integer> tempExit=null;

    public static void main(String[] args) {

        locationMap.put(0, new Location(
                0,
                "You are sitting in font of a computer learning Java",
                null));

        tempExit = new HashMap<>();
        tempExit.put("W", 2);
        tempExit.put("E", 3);
        tempExit.put("S", 4);
        tempExit.put("N", 5);
        locationMap.put(1, new Location(
                1,
                "You are standing at the end of the road before a small brick building",
                tempExit));

        tempExit = new HashMap<>();
        tempExit.put("N", 5);
        locationMap.put(2, new Location(
                2,
                "You are at the top of the hill",
                tempExit));

        tempExit = new HashMap<>();
        tempExit.put("W", 1);
        locationMap.put(3, new Location(
                3,
                "You are inside a building, a well house for a small spring",
                tempExit));

        tempExit = new HashMap<>();
        tempExit.put("N", 1);
        tempExit.put("W", 2);
        locationMap.put(4, new Location(
                4,
                "You are in a valley beside a stream",
                tempExit));

        tempExit = new HashMap<>();
        tempExit.put("S", 1);
        tempExit.put("W", 2);
        locationMap.put(5, new Location(
                5,
                "You are in the forest",
                tempExit));

        Map<String, String> vocabulary = new HashMap<>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");


        int loc = 1;
        while (true) {
            System.out.println(locationMap.get(loc).getDescription());

            if (loc == 0) {
                break;
            }

            Map<String, Integer> exits = locationMap.get(loc).getExits();
            System.out.println("avalaible exits are ");
            for (String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            System.out.println("Enter direction: ");
            String direction = scanner.nextLine().toUpperCase();
            if (direction.length() > 1) {
                String[] words = direction.split(" ");
                for (String word : words) {
                    if (vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }
            if (exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("You can not go in that direction");
            }

        }

        System.out.println("=============================================================================");

        String[] road = "You are standing at the end of the road before a small brick building".split(" ");
        for (String i : road) {
            System.out.println(i);
        }

        System.out.println("=====================================");
        String[] building = "You are inside a building, a well house for a small spring".split(",");
        for (String i : building) {
            System.out.println(i);
        }
    }
}
