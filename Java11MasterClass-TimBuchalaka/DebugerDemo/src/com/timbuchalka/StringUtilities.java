package com.timbuchalka;

public class StringUtilities {
    private int charsAdded=0;

    public void addChar(StringBuilder builder, char c) {
        builder.append(c);
        charsAdded++;
    }

    public String upperAndPrefix(String str) {
        String upper=str.toUpperCase();
        return "Prefix_"+upper;
    }

    public String addSuffix(String str) {
        return str+"__Suffix";
    }


}
