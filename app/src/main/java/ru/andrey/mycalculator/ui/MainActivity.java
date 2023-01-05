// Я выбрал данный макет потому, что он позволяет не плодить большое количество строк кода
package ru.andrey.mycalculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import ru.andrey.mycalculator.R;
import ru.andrey.mycalculator.model.CalculatorImpl;
import ru.andrey.mycalculator.model.Operator;

public class MainActivity extends AppCompatActivity implements CalculatorView{

    private TextView resultView;
    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = findViewById(R.id.result);
        presenter = new CalculatorPresenter(this, new CalculatorImpl());

        Map<Integer, Integer> digit = new HashMap<>();
        digit.put(R.id.key_0, 0);
        digit.put(R.id.key_1, 1);
        digit.put(R.id.key_2, 2);
        digit.put(R.id.key_3, 3);
        digit.put(R.id.key_4, 4);
        digit.put(R.id.key_5, 5);
        digit.put(R.id.key_6, 6);
        digit.put(R.id.key_7, 7);
        digit.put(R.id.key_8, 8);
        digit.put(R.id.key_9, 9);

        View.OnClickListener diffClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPress(digit.get(view.getId()));
            }
        };

        findViewById(R.id.key_0).setOnClickListener(diffClickListener);
        findViewById(R.id.key_1).setOnClickListener(diffClickListener);
        findViewById(R.id.key_2).setOnClickListener(diffClickListener);
        findViewById(R.id.key_3).setOnClickListener(diffClickListener);
        findViewById(R.id.key_4).setOnClickListener(diffClickListener);
        findViewById(R.id.key_5).setOnClickListener(diffClickListener);
        findViewById(R.id.key_6).setOnClickListener(diffClickListener);
        findViewById(R.id.key_7).setOnClickListener(diffClickListener);
        findViewById(R.id.key_8).setOnClickListener(diffClickListener);
        findViewById(R.id.key_9).setOnClickListener(diffClickListener);

        Map<Integer, Operator> operation = new HashMap<>();
        operation.put(R.id.key_add, Operator.ADD);
        operation.put(R.id.key_sub, Operator.SUB);
        operation.put(R.id.key_mul, Operator.MUL);
        operation.put(R.id.key_div, Operator.DIV);

        View.OnClickListener operationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPress(operation.get(view.getId()));
            }
        };

        findViewById(R.id.key_add).setOnClickListener(operationClickListener);
        findViewById(R.id.key_sub).setOnClickListener(operationClickListener);
        findViewById(R.id.key_mul).setOnClickListener(operationClickListener);
        findViewById(R.id.key_div).setOnClickListener(operationClickListener);

        findViewById(R.id.key_eq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onEqPress();
            }
        });
    }

    @Override
    public void showResult(String result) {
        resultView.setText(result);
    }
}