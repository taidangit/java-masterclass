package com.timbuchalka;

import javax.print.DocFlavor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	    String string="I am a string.Yes, I am.";
        System.out.println(string);
        String yourString=string.replaceAll("I", "You");
        System.out.println(yourString);

        String alpahnumeric="abcDeeeF12Ghhiiiijk199z";
        System.out.println(alpahnumeric.replaceAll(".","Y"));

        System.out.println(alpahnumeric.replaceAll("^abcDeee","YYY"));

        String secondString="abcDeeeF12GhhabcDeeeiiiijk199z";
        System.out.println(secondString.replaceAll("^abcDeee","YYY"));

        System.out.println(alpahnumeric.matches("^hello"));
        System.out.println(alpahnumeric.matches("^abcDeee"));
        System.out.println(alpahnumeric.matches("abcDeeeF12Ghhiiiijk199z"));
        System.out.println(alpahnumeric.replaceAll("ijk199z","THE END"));
        System.out.println(alpahnumeric.replaceAll("[aei]","X"));
        System.out.println(alpahnumeric.replaceAll("[aei]","I replaced a letter here"));
        System.out.println(alpahnumeric.replaceAll("[aei][Fj]","X"));

        System.out.println("==========================================================================");

        String newAlpahnumeric="abcDeeeF12Ghhiiiijk199z";
        System.out.println(newAlpahnumeric.replaceAll("[^ej]","X"));
        System.out.println(newAlpahnumeric.replaceAll("[abcdef345678]","X"));
        System.out.println(newAlpahnumeric.replaceAll("[A-Fa-f3-8]","X"));

        System.out.println(newAlpahnumeric.replaceAll("(?i)[a-f3-8]","X"));

        System.out.println(newAlpahnumeric.replaceAll("[0-9]","X"));
        System.out.println(newAlpahnumeric.replaceAll("\\d","X"));
        System.out.println(newAlpahnumeric.replaceAll("\\D","X"));

        String hasWhitespace="I have blanks and\ta tab, and also a newline\n";
        System.out.println(hasWhitespace);
        System.out.println(hasWhitespace.replaceAll("\\s",""));
        System.out.println(hasWhitespace.replaceAll("\t","X"));
        System.out.println(hasWhitespace.replaceAll("\\S",""));
        System.out.println(newAlpahnumeric.replaceAll("\\w","X"));
        System.out.println(hasWhitespace.replaceAll("\\w","X"));
        System.out.println(hasWhitespace.replaceAll("\\b","X"));

        String thirdAlphanumeric="abcDeeeF12Ghhiiiijk199z";
        System.out.println(alpahnumeric.replaceAll("^abcDe*","YYY"));
        System.out.println(alpahnumeric.replaceAll("^abcDe+","YYY"));
        System.out.println(alpahnumeric.replaceAll("^abcDe{3}","YYY"));
        System.out.println(alpahnumeric.replaceAll("^abcDe{2,5}","YYY"));
        System.out.println(alpahnumeric.replaceAll("h+i*j","YYY"));

        StringBuilder htmlText=new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub-heading</h2>");
        htmlText.append("<p>This is a paragraph about something</p>");
        htmlText.append("<p>This is another paragraph about something</p>");
        htmlText.append("<h2>Submary</h2>");
        htmlText.append("<p>Here is the sumary</p>");

        String h2Pattern="<h2>";
        Pattern pattern=Pattern.compile(h2Pattern);
        Matcher matcher=pattern.matcher(htmlText);
        System.out.println(matcher.matches());

        matcher.reset();
        int count=0;
        while (matcher.find()) {
            count++;
            System.out.println("Occurrence "+count+":"+matcher.start()+" to "+matcher.end());
        }

        String h2GroupPattern="(<h2>.*?</h2>)";
        Pattern groupPattern=Pattern.compile(h2GroupPattern);
        Matcher groupMatcher=groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while (groupMatcher.find()) {
            System.out.println("Occurrence: "+groupMatcher.group(1));
        }

        String h2TextGroup="(<h2>)(.+?)(</h2>)";
        Pattern h2TextPattern=Pattern.compile(h2TextGroup);
        Matcher h2TextMatches=h2TextPattern.matcher(htmlText);

        while (h2TextMatches.find()) {
            System.out.println("Occurrence: "+h2TextMatches.group(2));
        }
    }
}
