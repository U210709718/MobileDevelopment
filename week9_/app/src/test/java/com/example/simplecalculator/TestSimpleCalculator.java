package com.example.simplecalculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestSimpleCalculator {
    @Test
    public void performAddition() {  //test different operations
        CalculatorListener listener = new MockPresenter();

        SimpleCalculator calculator = new SimpleCalculator(listener); ///no - import ! its accesseble !
        calculator.setOperand(72);
        calculator.setOperator("+");
        calculator.setOperand(6);
        calculator.setOperator("=");
        assertEquals(78, listener.getResult());
    }
    @Test
    public void performSubtraction() {  //test different operations
        CalculatorListener listener = new MockPresenter();

        SimpleCalculator calculator = new SimpleCalculator(listener); ///no - import ! its accesseble !
        calculator.setOperand(72);
        calculator.setOperator("-");
        calculator.setOperand(6);
        calculator.setOperator("=");
        assertEquals(66, listener.getResult());
    }
    @Test
    public void performMixedOperations() {  //test different operations
        CalculatorListener listener = new MockPresenter();

        SimpleCalculator calculator = new SimpleCalculator(listener); ///no - import ! its accesseble !
        calculator.setOperand(72);
        calculator.setOperator("+");
        calculator.setOperand(6);
        calculator.setOperator("-");
        calculator.setOperand(3);
        calculator.setOperator("=");
        assertEquals(75, listener.getResult());
    }
}