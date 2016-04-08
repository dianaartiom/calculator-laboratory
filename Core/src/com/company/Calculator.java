package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by diana on 4/7/16.
 */
public class Calculator {
    /* Data definition */
    public String input;

    //    /* Constructor definition */
    public Calculator() {
        this.input = "1+3";
    }

    private static List<Character> getSymbols2(String string) {
        List<Character> inputSymbols=new ArrayList<Character>();
        for(int i=0;i<string.length();i++)
        {
            char symbol=string.charAt(i);

            if(symbol=='-' || symbol=='+' || symbol=='*' || symbol=='/' || symbol=='(' || symbol==')' || symbol=='^') {
                inputSymbols.add(symbol);
            }
        }
        return inputSymbols;
    }

    private static List<String> getSymbols3(String string) {
        List<String> inputSymbols = new ArrayList<String>();
        for(int i=0;i<string.length();i++)
        {

            String symbol=String.valueOf(string.charAt(i));
            if(symbol.equals("-") || symbol.equals("+") || symbol.equals("*") || symbol.equals("/") || symbol.equals("(") || symbol.equals(")") || symbol.equals("^")) {
                inputSymbols.add(symbol);
            }
//            String symbol=String.valueOf(string.charAt(i));
            else if(string.charAt(i)=='s' && string.charAt(i+1)=='q'){
                symbol = "sqrt(";
                string = string.substring(0, i) + string.substring(i+5);
                inputSymbols.add(symbol);
            }
            else if (string.charAt(i) == 's' && string.charAt(i+1) =='i'){
                symbol = "signInverse(";
                inputSymbols.add(symbol);
                string = string.substring(0, i) + string.substring(i+12);
            }
        }
        return inputSymbols;
    }

    private static List<String> getOperands(String string) {
        String[] operandsArray=string.split("\\^|\\(|\\)|\\-|\\+|\\*|\\/");
        List<String> inputOperands=new ArrayList<String>();
        String character;
        int index;

        for(int i=0;i<operandsArray.length;i++) {

            character = operandsArray[i];
            if (!(character.isEmpty() || Character.isLetter(character.charAt(0)))) {
                inputOperands.add(operandsArray[i]);
            }
        }
        return inputOperands;
    }

    private static void listUpdater(List<String> inputSymbols,List<String> inputOperands,int position,float result) {
        inputSymbols.remove(position);
        inputOperands.remove(position);
        inputOperands.remove(position);
        inputOperands.add(position,String.valueOf(result));
    }

    public static List<Integer> getSymbolOccurence(List<Character> inputSymbol, char character) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < inputSymbol.size(); i++) {
            if(inputSymbol.get(i) == character) {
                result.add(i);
            }
        }
        return result;
    }

    public static int countParanthesis(List<Character> inputSymbols, char character) {
        int count = 0;
        for (int i=0; i < inputSymbols.size(); i++) {
            if(inputSymbols.get(i) == '(' || inputSymbols.get(i) == ')') {
                count++;
            }
        }
        return count;
    }

    private static void listUpdater(List<String> inputSymbols,List<String> inputOperands,int position, int numberOfParanthesis, float result)
    {
        inputSymbols.remove(position+numberOfParanthesis);
        inputOperands.remove(position);
        inputOperands.remove(position);
        inputOperands.add(position,String.valueOf(result));
    }
    /* trial of implementation the power */

    public static float lastChance2(String string) {
        int currentPositionLeftParanthesis;
        int currentPostinonRightParanthesis;
        List<String> inputSymbols = getSymbols3(string); // contine absolut toate simbolurile
        List<String> inputOperands = getOperands(string);
        int counter = inputSymbols.size();
        String leftParanthesis = "(";
        String rightParanthesis = ")";
        float operand1 = 0.0F;
        float operand2 = 0.0F;
        float result = 0.0F;
        int numberOfParanthesis = 0;
        int sizeTillNow = 0;
        int sizeTillNowWithoutParanthesis = 0;

        int lastSqrtOccurrance = 0;
        int countSqrtOccurrance = 0;
        int countParanthesisOccurance = 0;
        int lastLeftParanthesisOccurance = 0;
        int firstRightParanthesisOccurance = 0;
        List<String> tempSublist = new ArrayList<String>();
        List<String> symbolsTillNow = new ArrayList<String>();
        int position = 0;

        while (counter > 0) {
            if (inputSymbols.contains("sqrt(")) {
                for (int i = 0; i < inputSymbols.size(); i++) {
                    if (inputSymbols.get(i).equals(leftParanthesis)) {
                        countParanthesisOccurance += 1;
                    } else if (inputSymbols.get(i).equals(rightParanthesis)) {
                        firstRightParanthesisOccurance = i;
                        break;
                    }
                }
                for (int i = 0; i < inputSymbols.size(); i++) {
                    if (inputSymbols.get(i).equals("sqrt(")) {
                        lastSqrtOccurrance = i;
                        countSqrtOccurrance += 1;
                    } else if (inputSymbols.get(i).equals(rightParanthesis)) {
                        firstRightParanthesisOccurance = i;
                        break;
                    }
                }
                symbolsTillNow = inputSymbols.subList(0, lastSqrtOccurrance); /* now is the position at which the loop stopped */
                sizeTillNow = symbolsTillNow.size();
                sizeTillNowWithoutParanthesis = sizeTillNow - countSqrtOccurrance; /* this is used as index for operands and signs */
                position = sizeTillNowWithoutParanthesis + 1;

                /* sublist to peform calculations on it */
                tempSublist = inputSymbols.subList(lastSqrtOccurrance + 1, firstRightParanthesisOccurance);
                countSqrtOccurrance += countParanthesisOccurance;
                if (lastSqrtOccurrance + 1 == firstRightParanthesisOccurance) {
                    result = Float.parseFloat(inputOperands.get(sizeTillNowWithoutParanthesis));
                    inputOperands.remove(sizeTillNowWithoutParanthesis);
                    inputOperands.add(sizeTillNowWithoutParanthesis, String.valueOf(Math.sqrt(result)));
                    inputSymbols.remove(lastSqrtOccurrance);
                    inputSymbols.remove(lastSqrtOccurrance);
                    counter--;
                    countSqrtOccurrance = 0;
                    countParanthesisOccurance = 0;
                    continue;
                }

                /* calculations */
                if (tempSublist.contains("^")) {
                    int currentPostionPower = tempSublist.indexOf("^");
                    operand1 = Float.parseFloat(inputOperands.get(currentPostionPower + sizeTillNowWithoutParanthesis));
                    operand2 = Float.parseFloat(inputOperands.get(currentPostionPower + 1 + sizeTillNowWithoutParanthesis));
                    result = Float.parseFloat(String.valueOf(Math.pow(operand1, operand2)));
                    listUpdater(inputSymbols, inputOperands, currentPostionPower + sizeTillNowWithoutParanthesis, countParanthesisOccurance, result);
                }
                else if (tempSublist.contains("*") || tempSublist.contains("/")) {
                    int currentPositionMultiplication = tempSublist.indexOf("*");
                    int currentPositionDividation = tempSublist.indexOf("/");

                    if((currentPositionMultiplication < currentPositionDividation && currentPositionMultiplication != -1) || currentPositionDividation == -1) {
                        operand1 = Float.parseFloat(inputOperands.get(currentPositionMultiplication + sizeTillNowWithoutParanthesis));
                        operand2 = Float.parseFloat(inputOperands.get(currentPositionMultiplication + 1 + sizeTillNowWithoutParanthesis));
                        result = operand1 * operand2;
                        listUpdater(inputSymbols, inputOperands, currentPositionMultiplication + sizeTillNowWithoutParanthesis, countParanthesisOccurance, result);
                    }
                    else if ((currentPositionMultiplication > currentPositionDividation && currentPositionDividation != -1) || currentPositionMultiplication == -1) {
                        operand1 = Float.parseFloat(inputOperands.get(currentPositionDividation + sizeTillNowWithoutParanthesis));
                        operand2 = Float.parseFloat(inputOperands.get(currentPositionDividation + 1 + sizeTillNowWithoutParanthesis));
                        result = operand1 / operand2;
                        listUpdater(inputSymbols, inputOperands, currentPositionDividation + sizeTillNowWithoutParanthesis, countParanthesisOccurance, result);
                    }
                }
                else if (tempSublist.contains("-") || tempSublist.contains("+")) {
                    int currentPositionSubstraction = tempSublist.indexOf("-");
                    int currentPositionAddition = tempSublist.indexOf("+");

                    if ((currentPositionSubstraction < currentPositionAddition && currentPositionSubstraction != -1) || currentPositionAddition == -1) {
                        operand1 = Float.parseFloat(inputOperands.get(currentPositionSubstraction + sizeTillNowWithoutParanthesis));
                        operand2 = Float.parseFloat(inputOperands.get(currentPositionSubstraction + 1 + sizeTillNowWithoutParanthesis));
                        result = operand1 - operand2;
                        listUpdater(inputSymbols, inputOperands, currentPositionSubstraction + sizeTillNow + 1, countParanthesisOccurance, result);
                    } else if ((currentPositionSubstraction > currentPositionAddition && currentPositionAddition != -1) || currentPositionSubstraction == -1) {

                        operand1 = Float.parseFloat(inputOperands.get(currentPositionAddition + sizeTillNowWithoutParanthesis));
                        operand2 = Float.parseFloat(inputOperands.get(currentPositionAddition + 1 + sizeTillNowWithoutParanthesis));
                        result = operand1 + operand2;
                        listUpdater(inputSymbols, inputOperands, currentPositionAddition + sizeTillNowWithoutParanthesis, countParanthesisOccurance + 1, result);
                    }

                }
                countSqrtOccurrance = 0;
                countParanthesisOccurance = 0;
            }
            else if (inputSymbols.contains(leftParanthesis) || inputSymbols.contains(rightParanthesis)) {
                for (int i = 0; i < inputSymbols.size(); i++) {
                    if (inputSymbols.get(i).equals(leftParanthesis)) {
                        lastLeftParanthesisOccurance = i;
                        countParanthesisOccurance += 1;
                    } else if (inputSymbols.get(i).equals(rightParanthesis)) {
                        firstRightParanthesisOccurance = i;
                        break;
                    }
                }

                if (lastLeftParanthesisOccurance + 1 == firstRightParanthesisOccurance) {
                    inputSymbols.remove(lastLeftParanthesisOccurance);
                    inputSymbols.remove(lastLeftParanthesisOccurance);
                    counter--;
                    countParanthesisOccurance = 0;
                    continue;
                }

                symbolsTillNow = inputSymbols.subList(0, lastLeftParanthesisOccurance); /* now is the position at which the loop stopped */
                sizeTillNow = symbolsTillNow.size();
                sizeTillNowWithoutParanthesis = sizeTillNow - countParanthesisOccurance + 1; /* this is used as index for operands and signs */

                /* sublist to peform calculations on it */
                tempSublist = inputSymbols.subList(lastLeftParanthesisOccurance+1, firstRightParanthesisOccurance);

                /* calculations */
                if(tempSublist.contains("^")){
                    int currentPostionPower = tempSublist.indexOf("^");

                    operand1 = Float.parseFloat(inputOperands.get(currentPostionPower + sizeTillNowWithoutParanthesis));
                    operand2 = Float.parseFloat(inputOperands.get(currentPostionPower + 1 + sizeTillNowWithoutParanthesis));
                    result = Float.parseFloat(String.valueOf(Math.pow(operand1, operand2)));
                    listUpdater(inputSymbols, inputOperands, currentPostionPower + sizeTillNowWithoutParanthesis, countParanthesisOccurance, result);
                }
                else if (tempSublist.contains("*") || tempSublist.contains("/")) {
                    int currentPositionMultiplication = tempSublist.indexOf("*");
                    int currentPositionDividation = tempSublist.indexOf("/");

                    if ((currentPositionMultiplication < currentPositionDividation && currentPositionMultiplication != -1) || currentPositionDividation == -1) {
                        operand1 = Float.parseFloat(inputOperands.get(currentPositionMultiplication + sizeTillNowWithoutParanthesis));
                        operand2 = Float.parseFloat(inputOperands.get(currentPositionMultiplication + 1 + sizeTillNowWithoutParanthesis));
                        result = operand1 * operand2;
                        listUpdater(inputSymbols, inputOperands, currentPositionMultiplication + sizeTillNowWithoutParanthesis, countParanthesisOccurance, result);
                    } else if ((currentPositionMultiplication > currentPositionDividation && currentPositionDividation != -1) || currentPositionMultiplication == -1) {
                        operand1 = Float.parseFloat(inputOperands.get(currentPositionDividation + sizeTillNowWithoutParanthesis));
                        operand2 = Float.parseFloat(inputOperands.get(currentPositionDividation + 1 + sizeTillNowWithoutParanthesis));
                        result = operand1 / operand2;
                        listUpdater(inputSymbols, inputOperands, currentPositionDividation + sizeTillNowWithoutParanthesis, countParanthesisOccurance, result);
                    }

                } else if (tempSublist.contains("-") || tempSublist.contains("+")) {
                    int currentPositionSubstraction = tempSublist.indexOf("-");
                    int currentPositionAddition = tempSublist.indexOf("+");

                    if ((currentPositionSubstraction < currentPositionAddition && currentPositionSubstraction != -1) || currentPositionAddition == -1) {
                        operand1 = Float.parseFloat(inputOperands.get(currentPositionSubstraction + sizeTillNowWithoutParanthesis));
                        operand2 = Float.parseFloat(inputOperands.get(currentPositionSubstraction + 1 + sizeTillNowWithoutParanthesis));
                        result = operand1 - operand2;
                        listUpdater(inputSymbols, inputOperands, currentPositionSubstraction + sizeTillNowWithoutParanthesis, countParanthesisOccurance, result);
                    }
                    else if ((currentPositionSubstraction > currentPositionAddition && currentPositionAddition != -1) || currentPositionSubstraction == -1) {

                        operand1 = Float.parseFloat(inputOperands.get(currentPositionAddition + sizeTillNowWithoutParanthesis));
                        operand2 = Float.parseFloat(inputOperands.get(currentPositionAddition + 1 + sizeTillNowWithoutParanthesis));
                        result = operand1 + operand2;
                        listUpdater(inputSymbols, inputOperands, currentPositionAddition + sizeTillNowWithoutParanthesis, countParanthesisOccurance, result);
                    }

                }
            } // aici se inchide if-ul
            else {
                tempSublist = inputSymbols;
                if(tempSublist.contains("^")){
                    int currentPostionPower = tempSublist.indexOf("^");

                    operand1 = Float.parseFloat(inputOperands.get(currentPostionPower + sizeTillNowWithoutParanthesis));
                    operand2 = Float.parseFloat(inputOperands.get(currentPostionPower + 1 + sizeTillNowWithoutParanthesis));
                    result = Float.parseFloat(String.valueOf(Math.pow(operand1, operand2)));
                    listUpdater(inputSymbols, inputOperands, currentPostionPower + sizeTillNowWithoutParanthesis, countParanthesisOccurance, result);
                }
                else if(inputSymbols.contains("*") || inputSymbols.contains("/"))
                {
                    int currentPositionMultiplication=inputSymbols.indexOf("*");
                    int currentPositionDividation=inputSymbols.indexOf("/");

                    if((currentPositionMultiplication<currentPositionDividation && currentPositionMultiplication!=-1) || currentPositionDividation==-1)
                    {
                        operand1=Float.parseFloat(inputOperands.get(currentPositionMultiplication));
                        operand2=Float.parseFloat(inputOperands.get(currentPositionMultiplication+1));
                        result=operand1*operand2;
                        listUpdater(inputSymbols,inputOperands,currentPositionMultiplication,result);
                    }
                    else if((currentPositionMultiplication>currentPositionDividation && currentPositionDividation!=-1) || currentPositionMultiplication==-1)
                    {
                        operand1=Float.parseFloat(inputOperands.get(currentPositionDividation));
                        operand2=Float.parseFloat(inputOperands.get(currentPositionDividation+1));
                        result=operand1/operand2;
                        listUpdater(inputSymbols,inputOperands,currentPositionDividation,result);
                    }
                }
                else if(inputSymbols.contains("-") || inputSymbols.contains("+"))
                {
                    int currentPositionSubstraction=inputSymbols.indexOf("-");
                    int currentPositionAddition=inputSymbols.indexOf("+");

                    if((currentPositionSubstraction<currentPositionAddition && currentPositionSubstraction!=-1) || currentPositionAddition==-1)
                    {
                        operand1=Float.parseFloat(inputOperands.get(currentPositionSubstraction));
                        operand2=Float.parseFloat(inputOperands.get(currentPositionSubstraction+1));
                        result=operand1-operand2;
                        listUpdater(inputSymbols,inputOperands,currentPositionSubstraction,result);
                    }
                    else if((currentPositionSubstraction>currentPositionAddition && currentPositionAddition!=-1) || currentPositionSubstraction==-1)
                    {

                        operand1=Float.parseFloat(inputOperands.get(currentPositionAddition));
                        operand2=Float.parseFloat(inputOperands.get(currentPositionAddition+1));
                        result=operand1+operand2;
                        listUpdater(inputSymbols,inputOperands,currentPositionAddition, currentPositionAddition, result);
                    }
                }
            }
            countSqrtOccurrance = 0;
            countParanthesisOccurance = 0;
            counter--;
        }
        Iterator<String> iterator=inputOperands.iterator();
        String finalResult="";
        while(iterator.hasNext())
        {
            finalResult=iterator.next();
        }
        return Float.parseFloat(finalResult);
    }

}

