package com.company;

public class Main {

    public static void main(String[] args) {
        String mathExpression = "";
        if(args != null){
            for (String s: args) {
                mathExpression = mathExpression + s.toString();
            }

            if(mathExpression != null){
                Calculator calculator = new Calculator();
                System.out.println(calculator.lastChance2(mathExpression));
            }
        }
    }
}
