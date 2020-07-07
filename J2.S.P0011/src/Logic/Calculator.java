/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import GUI.SimpleCalculator;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author nguyentrinhan.dev
 */
public class Calculator {

    SimpleCalculator c;
    BigDecimal result = new BigDecimal("0");
    BigDecimal Dnum = new BigDecimal("0");
    String operator = ""; 
    Boolean newNumber = true;
    
    public Calculator(SimpleCalculator c) {
        this.c = c;
    }

    public String ButtonClear() {
        Dnum = new BigDecimal("0");
        result = new BigDecimal("0");
        operator = "";
        newNumber = true;
        return ("0");
    }
    
    public String addDigit(String text, String s) {
        // imported digit replace current text
        if (newNumber) {
            //after operator "=", result is return to zero
            //so next step do not use that number
            if (operator.equals("=")) {
                result = new BigDecimal("0");
            }
            newNumber = false;
            return (FixNumber(new BigDecimal(s)));
        } else 
        //incase of current text is zero, first number will replace it
         if ((text).equals("0")) {
                return (FixNumber(new BigDecimal("s")));
            } else 
        //add new digit to last text
            {
                return (text + s);
            }
    }
    
    public String Calculate(String text, String currentOp) {
        String output = text;
        if (!newNumber) {
            BigDecimal num = new BigDecimal(text);
            switch (operator) {
                case "+":
                    result = result.add(num);
                    break;
                case "-":
                    result = result.subtract(num);
                    break;
                case "*":
                    result = result.multiply(num);
                    break;
                case "/":
                    if (num.equals(new BigDecimal("0"))) {
                        output = "Can not divided by zero";
                    } else {
                        result = result.divide(num, 12, RoundingMode.HALF_EVEN);
                    }
                    break;
                default:
                    result = new BigDecimal(text);
                    break;
            }
            if (!output.equals("Can not divided by zero")) {
                output = FixNumber(result);
            }
        }
        //save new operator to next calculaton
        operator = currentOp;
        newNumber = true;
        return output;
    }
    public String FixNumber(BigDecimal currentNum) {
        String s = currentNum.toString();
        //find dot through String s
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                String newS = "";
                int newL = s.length();
                //delete all zeros digit after last dot digit through s
                for (int j = s.length() - 1; j > 1; j--) 
                // in case of last digit is also zero
                {
                    if (s.charAt(j) == '0')
                        //update length of 
                    {
                        newL = j;
                    } 
                // incase of next digit is appeared
                    else {
                        break;
                    }
                }
                // incase of all digit after dot equal zeros, so there all waste
                if (s.charAt(newL - 1) == '.') {
                    newL--;
                }
                //saves new string throung s
                for (int j = 0; j < newL; j++) {
                    newS = newS + s.charAt(j);
                }
                s = newS;
                break;
            }
        }
        return s;
    }
}
