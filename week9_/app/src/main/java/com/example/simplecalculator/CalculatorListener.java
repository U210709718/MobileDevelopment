package com.example.simplecalculator;

public interface CalculatorListener {
    int getResult();

    void onResultCalculated (int result);
}
