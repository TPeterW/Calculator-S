package com.peter.calculator;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by � on 2015/6/13 0013.
 */
public class Calculator {
    // TODO: create a small TextView to show instand results
    private TextView textView;
    private String type;
    private String currentNum;
    public boolean doCalc;
    private boolean overwrite;

    private enum sign{none, plus, minus, multi, div}
    private sign lastEntered;

    /**
     * numA * numB = result
     */
    private double lastNum;

    public Calculator(){
        type = "simple";
        currentNum = 0 + "";
        doCalc = false;                               // indicates when next time the user presses any of the four sign buttons, whether the app needs to do calculations first
        lastEntered = sign.none;
        overwrite = false;
    }

    /*
    The actual useful constructor
     */
    public Calculator(String type){
        if(type.equals("simple"))
            type = "simple";
        else
            type = "sci";

        currentNum = 0 + "";
        doCalc = false;
        lastEntered = sign.none;
        overwrite = false;
    }

    /*
    Distributes to lower functions to operate in details
     */
    public String click(String button, View v){
        String display;
        try{
            if (button.equals("+"))
                display = plus();
            else if(button.equals("-"))
                display = minus();
            else if(button.equals("multi"))
                display = multi();
            else if(button.equals("div"))
                display = div();
            else if(button.equals("C") || button.equals("c"))
                display = allClear();
            else if(button.equals("DEL") || button.equals("del"))
                display = del();
            else if(button.equals("="))
                display = equals();
            else if(button.equals("."))
                display = addDot(v);
            else
                display = append(button, v);
        }
        catch (Exception e){
            Toast.makeText(v.getContext(), "Invalid Input", Toast.LENGTH_SHORT).show();
            display = allClear();
        }


        // TODO: after calculation, rewrite whatever is in the TextView

        return display;
    }

    private String plus(){
        // Ignore the last dot if exists
        if(currentNum.charAt(currentNum.length() - 1) == '.')
            currentNum = currentNum.substring(0, currentNum.length() - 1);


        if(!isNumeric(currentNum.charAt(0))){
            lastEntered = sign.plus;
            currentNum = "+";
        }
        else if(doCalc) {
            doCalc();
            doCalc = true;
            lastNum = Double.parseDouble(currentNum);
            lastEntered = sign.plus;
            currentNum = "+";
        }
        else{
            lastNum = Double.parseDouble(currentNum);
            lastEntered = sign.plus;
            currentNum = "+";
            doCalc = true;
        }

        return currentNum;
    }

    private String minus(){
        // Ignore the last dot if exists
        if(currentNum.charAt(currentNum.length() - 1) == '.')
            currentNum = currentNum.substring(0, currentNum.length() - 1);

        if(!isNumeric(currentNum.charAt(0))){
            lastEntered = sign.minus;
            currentNum = "-";
        }
        else if(doCalc) {
            doCalc();
            doCalc = true;
            lastNum = Double.parseDouble(currentNum);
            lastEntered = sign.minus;
            currentNum = "-";
        }
        else{
            lastNum = Double.parseDouble(currentNum);
            lastEntered = sign.minus;
            currentNum = "-";
            doCalc = true;
        }

        return currentNum;
    }

    private String multi(){
        // Ignore the last dot if exists
        if(currentNum.charAt(currentNum.length() - 1) == '.')
            currentNum = currentNum.substring(0, currentNum.length() - 1);

        if(!isNumeric(currentNum.charAt(0))){
            lastEntered = sign.multi;
            currentNum = "multi";
        }
        else if(doCalc) {
            doCalc();
            doCalc = true;
            lastNum = Double.parseDouble(currentNum);
            lastEntered = sign.multi;
            currentNum = "multi";
        }
        else{
            lastNum = Double.parseDouble(currentNum);
            lastEntered = sign.multi;
            currentNum = "multi";
            doCalc = true;
        }

        return currentNum;
    }

    private String div(){
        // Ignore the last dot if exists
        if(currentNum.charAt(currentNum.length() - 1) == '.')
            currentNum = currentNum.substring(0, currentNum.length() - 1);

        if(!isNumeric(currentNum.charAt(0))){
            lastEntered = sign.div;
            currentNum = "div";
        }
        else if(doCalc) {
            doCalc();
            doCalc = true;
            lastNum = Double.parseDouble(currentNum);
            lastEntered = sign.div;
            currentNum = "div";
        }
        else{
            lastNum = Double.parseDouble(currentNum);
            lastEntered = sign.div;
            currentNum = "div";
            doCalc = true;
        }

        return currentNum;
    }

    private String equals(){
        if(!doCalc && currentNum.equals(0 + "")){
            return currentNum;
        }

        if(!doCalc && currentNum.equals("0.")){
            return currentNum;
        }

        // Ignore the last dot if exists
        if(currentNum.charAt(currentNum.length() - 1) == '.')
            currentNum = currentNum.substring(0, currentNum.length() - 1);

        if(!isNumeric(currentNum.charAt(0))){
            return currentNum;
        }
        else if(doCalc){
            doCalc();
            doCalc = false;
            lastNum = Double.parseDouble(currentNum);
            overwrite = true;
            return currentNum;
        }
        else{
            doCalc = false;
            return currentNum;
        }
    }

    private String del(){
        if (isNumeric(currentNum.charAt(0))){
            if(currentNum.charAt(currentNum.length() - 1) == '0' && currentNum.charAt(currentNum.length() - 2) == '.')
                currentNum = currentNum.substring(0, currentNum.indexOf('.'));

            // the new length
            int length = currentNum.length();

            if(length == 1 || currentNum.equals(0 + ""))
                currentNum = 0 + "";
            else if(length > 1){
                currentNum = currentNum.substring(0, length - 1);


                /**
                 * Whether to go ahead and just delete the proceeding dot is debatable
                 */
//                if(currentNum.charAt(length - 2) == '.')
//                    currentNum = currentNum.substring(0, length - 2);
            }

            return currentNum;
        }

        else{
            return currentNum;
        }
    }

    private String addDot(View v){
        if(currentNum.contains(".") || currentNum.equals("+") || currentNum.equals("-"))
            return currentNum;
        else if(overwrite){
            currentNum = "0.";
            overwrite = false;
            return currentNum;
        }
        else if(currentNum.length() >= 34){
            Toast.makeText(v.getContext(), "Too Long", Toast.LENGTH_SHORT).show();
            return currentNum;
        }
        if(currentNum.charAt(currentNum.length() - 1) == '.' || currentNum.charAt(currentNum.length() - 1) == '×' || currentNum.charAt(currentNum.length() - 1) == '÷'){
            return currentNum;
        }
        else{
            currentNum += '.';
            return currentNum;
        }
    }

    private String allClear(){
        currentNum = 0 + "";
        doCalc = false;
        lastEntered = sign.none;

        return currentNum;
    }

    private String append(String button, View v){
        if(overwrite){
            currentNum = button;
            overwrite = false;
        }
        else if(currentNum.length() >= 34){
            Toast.makeText(v.getContext(), "Too Long", Toast.LENGTH_SHORT).show();
            return currentNum;
        }
        else if(currentNum.equals(0 + ""))                                                  // When display is zero
            currentNum = button;
        else if(!isNumeric(currentNum.charAt(0)))                                           // Just entered a sign
            currentNum = button;
        else
            currentNum += button;

        return currentNum;
    }

    private boolean isNumeric(char ch){
        boolean isNumeric;
            switch (ch){
                case '0':
                    isNumeric = true;
                    break;
                case '1':
                    isNumeric = true;
                    break;
                case '2':
                    isNumeric = true;
                    break;
                case '3':
                    isNumeric = true;
                    break;
                case '4':
                    isNumeric = true;
                    break;
                case '5':
                    isNumeric = true;
                    break;
                case '6':
                    isNumeric = true;
                    break;
                case '7':
                    isNumeric = true;
                    break;
                case '8':
                    isNumeric = true;
                    break;
                case '9':
                    isNumeric = true;
                    break;
                default:
                    isNumeric = false;
            }

        return isNumeric;
    }

    private void doCalc(){
        switch (lastEntered){
            case plus:
                currentNum = Double.toString(lastNum + Double.parseDouble(currentNum));
                break;
            case minus:
                currentNum = Double.toString(lastNum - Double.parseDouble(currentNum));
                break;
            case multi:
                currentNum = Double.toString(lastNum * Double.parseDouble(currentNum));
                break;
            case div:
                currentNum = Double.toString(lastNum / Double.parseDouble(currentNum));
        }
    }
}
