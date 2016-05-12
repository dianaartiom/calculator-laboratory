package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by diana on 4/7/16.
 */
public class Calculator {
    /* Data definition */
    public String input;

    public static String parse(String string) {
        while(string.contains("+") || string.contains("*") || string.contains("/")) {
            List<String> tempElementsList2 = getListOfElements(string);
            List<String> tempElementsList3 = getListOfElements(string);
            List<String> answerList = new ArrayList<>();
            for (int i = 0; i < tempElementsList2.size(); i++) {
                answerList.add(parseLocally(tempElementsList2.get(i)));
                if(tempElementsList2.get(i).startsWith("+")){
                    string = string.replace(tempElementsList2.get(i), "+" + answerList.get(i));
                }
                else {
                    string = string.replace(tempElementsList2.get(i), answerList.get(i));
                }
                string = string.replace("(" + answerList.get(i) + ")", answerList.get(i));
                tempElementsList3 = fromStringToList(string);
            }
        }
        System.out.println(string);
        return string;
    }

    public static float computeExpresion(String number1, String number2, String sign) {
        float result = 0.0F;
        switch (sign) {
            case "+":
                result = Float.parseFloat(number1) + Float.parseFloat(number2);
                break;
            case "-":
                result = Float.parseFloat(number1) - Float.parseFloat(number2);
                break;
            case "*":
                result = Float.parseFloat(number1) * Float.parseFloat(number2);
                break;
            case "/":
                result = Float.parseFloat(number1) / Float.parseFloat(number2);
                break;
            case "^":
                result = Float.parseFloat(String.valueOf(Math.pow(Double.parseDouble(number1), Double.parseDouble(number2))));
                break;
            case "sqrt":
                result = Float.parseFloat(String.valueOf(Math.sqrt(Double.parseDouble(number1))));
                break;
        }
        return result;
    }

    public static String parseLocally(String string) { /// / ca parametru trimiti un math expresion
        String temp;
        List<String> listOfExpressionts = new ArrayList<>();
        String initialString = string;
        String signRegex = "(\\+|\\-|\\*|\\/|sqrt|\\^)";
        String floatingPointNumber = "(([-|+])?[0-9]+\\.?[0-9]*)";
        String pattern = floatingPointNumber + signRegex + floatingPointNumber;

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(string);
        float result = 0.0F;
        if(string.contains("sqrt")){
            signRegex = "(sqrt)";
            floatingPointNumber = "(([-|+])?[0-9]+\\.?[0-9]*)";
            pattern = signRegex + floatingPointNumber;
            Pattern r2 = Pattern.compile(pattern);
            Matcher m2 = r2.matcher(string);
            while (m2.find()) {
                result = computeExpresion(m2.group(2), m2.group(2), m2.group(1));
                string = string.replace(m2.group(), String.valueOf(result));
            }
        }
        else {
            while (m.find()) {
                result = computeExpresion(m.group(1), m.group(4), m.group(3));
                string = string.replace(m.group(), String.valueOf(result));
            }
            m = r.matcher(string);
        }
        return string;
    }

    public static List<String> fromStringToList(String string) {
        List<String> elementsList = new ArrayList<>();
        Pattern pattern = Pattern.compile("((\\d*\\.\\d+)|(\\d+)|([\\+\\-\\*/\\(\\)]))");
        Matcher m = pattern.matcher(string);

        while(m.find()) {
            elementsList.add(m.group());
        }
        return elementsList;
    }

    public static String checkIfSqrt(String string) {
        String signRegex = "(sqrt)";
        String floatingPointNumber = "(([-|+])?[0-9]+\\.?[0-9]*)";
        String pattern = signRegex + floatingPointNumber;

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(string);

        if(m.find())
            return m.group();
        else
            return string;
    }

    public static List<String> getListOfElements(String string) {
        String temp;
        List<String> listOfExpressionts = new ArrayList<>();
        String signRegex = "(\\+|\\-|\\*|\\/|sqrt|\\^)";
        String floatingPointNumber = "(([-|+])?[0-9]+\\.?[0-9]*)";
        String pattern = floatingPointNumber + "(" + signRegex + floatingPointNumber+ ")+";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(string);

        while(m.find()) {

            if(string.contains("sqrt")){
                temp = checkIfSqrt(string);
                listOfExpressionts.add(temp);
                string = string.replaceFirst(temp, "");
            }
            if(m.group().contains("^")) {
                String signRegex1 = "(\\^)";
                String pattern1 = floatingPointNumber  + signRegex1 + floatingPointNumber;
                Pattern r1 = Pattern.compile(pattern1);
                Matcher m1 = r1.matcher(m.group());
                while (m1.find()){
                    temp = m1.group().replace("+", "");
                    if(m1.group().contains("+")) {
                        temp = m1.group().replace("+", "");
                    }
                    listOfExpressionts.add(temp);
                }
            }
            else if(m.group().contains("*") || m.group().contains("/")){
                String signRegex2 = "(\\*|\\/|sin)";
                String pattern2 = floatingPointNumber  + signRegex2 + floatingPointNumber;
                Pattern r2 = Pattern.compile(pattern2);
                Matcher m2 = r2.matcher(m.group());
                while (m2.find()){
                    listOfExpressionts.add(m2.group());
                }
            }
            else if(m.group().contains("+") || m.group().contains("-")){
                String signRegex3 = "(\\+|\\-)";
                String pattern3 = floatingPointNumber  + signRegex3 + floatingPointNumber;
                Pattern r3 = Pattern.compile(pattern3);
                Matcher m3 = r3.matcher(m.group());
                while (m3.find()){
                    listOfExpressionts.add(m3.group());
                }
            }
        }
        return listOfExpressionts;
    }

    public static String fromListToString(List<String> elementsList) {
        String temp = new String();
        for (int i = 0; i<elementsList.size(); i++) {
            temp += elementsList.get(i);
        }
        return temp;
    }
}
