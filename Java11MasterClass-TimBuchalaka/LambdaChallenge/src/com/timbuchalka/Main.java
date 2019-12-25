package com.timbuchalka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String myString = "Let's split this up into an array";
                String[] parts = myString.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };

        Runnable runnable11 = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };

        Function<String, String> lambdaFunction = s -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 1) {
                    builder.append(s.charAt(i));
                }
            }
            return builder.toString();
        };

        System.out.println(everySecondCharacter(lambdaFunction, "1234567890"));

        Supplier<String> iLoveJava = () -> {
            return "I love Java";
        };
        System.out.println(iLoveJava.get());

        System.out.println("=========================================================");

        List<String> topNames2019 = Arrays.asList(
                "amelia",
                "olivia",
                "emily",
                "isla",
                "ava",
                "oliver",
                "jack",
                "charlie",
                "harry",
                "jacob"
        );

        List<String> firstUpperCaseList = new ArrayList<>();
        topNames2019.forEach(name ->
                firstUpperCaseList.add(name.substring(0, 1).toUpperCase() + name.substring(1)));
        //firstUpperCaseList.sort((s1, s2) -> s1.compareTo(s2));
        //firstUpperCaseList.forEach(s -> System.out.println(s));
        firstUpperCaseList.sort(String::compareTo);
        firstUpperCaseList.forEach(System.out::println);

        System.out.println("===================================================");
        topNames2019
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .sorted(String::compareTo)
                .forEach(System.out::println);
        System.out.println("=======================================================");
        long namesBeginingWithA = topNames2019
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .filter(name -> name.startsWith("A"))
                .count();

        System.out.println("Number of names beginning with A is :" + namesBeginingWithA);

        topNames2019
                .stream()
                .map(name->name.substring(0,1).toUpperCase()+name.substring(1))
                .peek(System.out::println)
                .sorted(String::compareTo)
                .collect(Collectors.toList());


    }

    private static String everySecondCharacter(Function<String, String> func, String source) {
        return func.apply(source);
    }
}
