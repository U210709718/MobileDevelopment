package com.example.simplecalculator;

public class SimpleCalculator {
    private final CalculatorListener listener;
    //to preform calculations
    Integer operand1;
    String operator;
    Integer operand2;

    public SimpleCalculator(CalculatorListener listener) {
        this.listener = listener;
    }

    public void setOperand(int value ) {
        if (operand1 == null)
            operand1 = value; //it will be 1st number
        else {
            operand2 = value;
            if (operator.equals("+"))
                operand1 += operand2;
            else if (operator.equals("-"))
                operand1 -= operand2;
            listener.onResultCalculated(operand1);
            //send to presenter!

        }
    }

    public void setOperator(String op) {
        operator = op;

    }
}
