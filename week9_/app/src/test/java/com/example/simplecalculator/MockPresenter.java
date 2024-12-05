package com.example.simplecalculator;

public class MockPresenter implements CalculatorListener {
    private int result;

    @Override
    public int getResult() {
        return 0;
    }

    @Override
    public void onResultCalculated(int result) {
        this.result = result;

    }
}
