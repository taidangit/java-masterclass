package com.timbuchalka;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, String> languages = new HashMap<>();
        if (languages.containsKey("Java")) {
            System.out.println("Java already exists");
        } else {
            languages.put("Java", "a compiled high level, object-oriented, platform independent language");
            System.out.println("Java added successfully");
        }

        languages.put("Python", "an interpreted, object-oriented, high level programming language with dynamic semantics");
        languages.put("Algol", "an algorithmic language");
        languages.put("BASIC", "Beginners all purposed symbolic instructor code");
        languages.put("Lisp", "There in lines madness");

        if (languages.containsKey("Java")) {
            System.out.println("Java is already in the map");
        } else {
            languages.put("Java", "this course is about Java");
        }

        System.out.println("=====================================");

        //languages.remove("Lisp");

        if (languages.remove("Algol", "an algorithmic language")) {
            System.out.println("algol removed");
        } else {
            System.out.println("Algol not removed, key or value not found");
        }

        if (languages.containsKey("Lisp")) {
            languages.replace("Lisp", "a functional programming language with imperative features");
            System.out.println("replaced successfully");
        } else {
            System.out.println("Lisp not found");
        }


        for (String key : languages.keySet()) {
            System.out.println(key + ":" + languages.get(key));
        }
    }
}
