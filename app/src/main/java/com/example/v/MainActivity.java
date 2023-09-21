package com.example.v;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText editTextResult;
    private String currentInput = "";
    private double currentValue = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextResult = findViewById(R.id.editTextResult);

        setupNumberButtons();
        setupOperatorButtons();
        setupClearButton();
        setupEqualsButton();
    }

    private void setupNumberButtons() {
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9
        };

        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedButton = (Button) v;
                    currentInput += clickedButton.getText().toString();
                    updateDisplay();
                }
            });
        }
    }

    private void setupOperatorButtons() {
        int[] operatorButtonIds = {
                R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonEquals
        };

        for (int id : operatorButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedButton = (Button) v;
                    operator = clickedButton.getText().toString();
                    currentValue = Double.parseDouble(currentInput);
                    currentInput = "";
                }
            });
        }
    }

    private void setupClearButton() {
        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "";
                currentValue = 0;
                operator = "";
                updateDisplay();
            }
        });
    }

    private void setupEqualsButton() {
        Button buttonEquals = findViewById(R.id.buttonEquals);
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty() && !operator.isEmpty()) {
                    double secondValue = Double.parseDouble(currentInput);
                    double result = performOperation(currentValue, secondValue, operator);
                    currentInput = String.valueOf(result);
                    operator = "";
                    updateDisplay();
                }
            }
        });
    }

    private double performOperation(double value1, double value2, String operator) {
        switch (operator) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                if (value2 != 0) {
                    return value1 / value2;
                } else {
                    // Handle division by zero
                    return 0;
                }
            default:
                return 0;
        }
    }

    private void updateDisplay() {
        editTextResult.setText(currentInput);
    }
}