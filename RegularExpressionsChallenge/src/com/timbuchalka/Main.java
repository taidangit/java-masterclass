package com.timbuchalka;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String challenge1 = "I want a bike.";
        System.out.println(challenge1.matches("I want a bike."));

        String regExp = "I want a \\w+.";
        System.out.println(challenge1.matches(regExp));
        String challenge2 = "I want a ball.";
        System.out.println(challenge2.matches(regExp));

        String regExp1 = "I want a (bike|ball).";
        System.out.println(challenge1.matches(regExp1));
        System.out.println(challenge2.matches(regExp1));

        String regExp3 = "I want a \\w+.";
        Pattern pattern = Pattern.compile(regExp3);
        Matcher matcher = pattern.matcher(challenge1);
        System.out.println(matcher.matches());

        matcher = pattern.matcher(challenge2);
        System.out.println(matcher.matches());

        String challenge3 = "Replace all blanks with underscores.";
        System.out.println(challenge3.replaceAll(" ", "_"));
        System.out.println(challenge3.replaceAll("\\s", "_"));

        String challenge4 = "aaabccccccccdddefffg";
        System.out.println(challenge4.matches("[abcdefg]+"));
        System.out.println(challenge4.matches("[a-g]+"));

        System.out.println(challenge4.matches("^a{3}bc{8}d{3}ef{3}g$"));
        System.out.println(challenge4.replaceAll("^a{3}bc{8}d{3}ef{3}g$", "Replace All"));

        String challenge5 = "abcd.135";
        System.out.println(challenge5.matches("^[A-Z][a-z]+\\.\\d+$"));

        String challenge6 = "abcd.135uvqz.7tzik.999";
        Pattern pattern1 = Pattern.compile("[A-Za-z]+\\.(\\d+)");
        Matcher matcher1 = pattern1.matcher(challenge6);
        while (matcher1.find()) {
            System.out.println("occurrence: " + matcher1.group(1));
        }

        String challenge7 = "abcd.135\tuvqz.7\ttzik.999\n";
        Pattern pattern2 = Pattern.compile("[A-Za-z]+\\.(\\d+)\\s");
        Matcher matcher2 = pattern2.matcher(challenge7);
        while (matcher2.find()) {
            System.out.println("Occurence: " + matcher2.group(1));
        }

        String challenge8 = "abcd.135\tuvqz.7\ttzik.999\n";
        Pattern pattern3 = Pattern.compile("[A-Za-z]+\\.(\\d+)\\s");
        Matcher matcher3 = pattern3.matcher(challenge8);
        while (matcher3.find()) {
            System.out.println("Occurence: " + matcher3.start(1) + " end=" + (matcher3.end(1) - 1));
        }

        String challenge9 = "{0,2},{0,5},{1,3},{2,4}";
        Pattern pattern4 = Pattern.compile("\\{(.+?)\\}");
        Matcher matcher4 = pattern4.matcher(challenge9);
        while (matcher4.find()) {
            System.out.println("Occurence: " + matcher4.group(1));
        }

        String challenge10 = "{0,2},{0,5},{1,3},{2,4},{x,y},{6,34},{11,22}";
        Pattern pattern5 = Pattern.compile("\\{(\\d+, \\d+)\\}");
        Matcher matcher5 = pattern4.matcher(challenge10);
        while (matcher5.find()) {
            System.out.println("Occurence: " + matcher5.group(1));
        }

        String challenge11="11111";
        System.out.println(challenge11.matches("^\\d{5}$"));

        String challenge12="11111-1111";
        System.out.println(challenge12.matches("^\\d{5}-\\d{4}$"));
        System.out.println(challenge12.matches("^\\d{5}-\\d{4}?$"));
        System.out.println(challenge12.matches("^\\d{5}-\\d{4}?$"));

    }
}
