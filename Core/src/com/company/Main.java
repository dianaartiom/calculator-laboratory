package com.company;

public class Main {
    public static void main(String[] args){
        String mathExpression = "";
        for (String s: args) {
            mathExpression = mathExpression + s.toString();
        }

        if(mathExpression != null){
            Calculator calculator = new Calculator();
            calculator.parse(mathExpression);

        }
    }
}
