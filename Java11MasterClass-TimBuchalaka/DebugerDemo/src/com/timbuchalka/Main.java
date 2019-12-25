package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        StringUtilities utilities = new StringUtilities();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 10) {
            utilities.addChar(sb, 'a');
        }
        System.out.println(sb.toString());

        String str="abcdefg";
        String result=utilities.upperAndPrefix(utilities.addSuffix(str));
        System.out.println(result);
    }
}
