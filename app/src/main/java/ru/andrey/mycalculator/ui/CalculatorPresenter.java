package ru.andrey.mycalculator.ui;

import java.text.DecimalFormat;

import ru.andrey.mycalculator.model.Calculator;
import ru.andrey.mycalculator.model.Operator;

public class CalculatorPresenter {

    private final DecimalFormat format = new DecimalFormat("#.##");
    private final CalculatorView view;
    private final Calculator calculator;

    private double argOne;
    private Double argTwo;
    private Operator selectedOperator;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }


    public void onDigitPress(int digit) {
        if(argTwo == null){
            argOne = argOne * 10 + digit;
            showFormatter(argOne);
        } else {
            argTwo = argTwo * 10 + digit;
            showFormatter(argTwo);
        }
    }

    public void onOperatorPress(Operator operator) {
        if (selectedOperator != null){
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showFormatter(argOne);
        }
        argTwo = 0.0;
        selectedOperator = operator;
    }

    public void showFormatter (double value) {
        view.showResult(format.format(value));
    }

    public void onEqPress() {
        if(argTwo != null) {
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showFormatter(argOne);
        }
    }
}
