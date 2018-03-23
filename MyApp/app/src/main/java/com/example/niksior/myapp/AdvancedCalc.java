package com.example.niksior.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdvancedCalc extends SimpleCalc implements Controlls {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calc);
        Button button0 = findViewById(R.id.equalsButton);
        button0.setEnabled(false);
    }

    public void changeButtonsState(boolean state) {
        Button button0 = findViewById(R.id.equalsButton);
        Button button1 = findViewById(R.id.additionButton);
        Button button2 = findViewById(R.id.multiplyButton);
        Button button3 = findViewById(R.id.divideButton);
        Button button4 = findViewById(R.id.subtractionButton);
        Button button5 = findViewById(R.id.pow2Button);
        Button button6 = findViewById(R.id.powyButton);
        Button button7 = findViewById(R.id.lnButton);
        Button button8 = findViewById(R.id.logButton);
        Button button9 = findViewById(R.id.tanButton);
        Button button10 = findViewById(R.id.sinButton);
        Button button11 = findViewById(R.id.cosButton);
        Button button12 = findViewById(R.id.sqrtButton);

        button0.setEnabled(!state);
        button1.setEnabled(state);
        button2.setEnabled(state);
        button3.setEnabled(state);
        button4.setEnabled(state);
        button5.setEnabled(state);
        button6.setEnabled(state);
        button7.setEnabled(state);
        button8.setEnabled(state);
        button9.setEnabled(state);
        button10.setEnabled(state);
        button11.setEnabled(state);
        button12.setEnabled(state);
    }

    public void clickEq(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        double result = 0;

        switch (operationType) {
            case "div":
                if (Double.parseDouble(actualState) == 0) {
                    sendToast("You can't divide by 0");
                } else {
                    result = leftNumber / Double.parseDouble(actualState);
                }
                break;
            case "add":
                result = leftNumber + Double.parseDouble(actualState);
                break;
            case "mul":
                result = leftNumber * Double.parseDouble(actualState);
                break;
            case "sub":
                result = leftNumber - Double.parseDouble(actualState);
                break;
            case "powy":
                result = Math.pow(leftNumber, Double.parseDouble(actualState));
                break;

        }

        leftNumber = result;
        operationType = "none";
        resultBox.setText(String.valueOf(result));
        changeButtonsState(true);
        isDone = true;
    }

    public void clickSqrt(View view) {
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        leftNumber = Double.parseDouble(actualState);
        if(leftNumber < 0)
            sendToast("It can't be negative number");
        else
            leftNumber = Math.sqrt(leftNumber);
        resultBox.setText(String.valueOf(leftNumber));
        changeButtonsState(true);
        isDone = true;
    }

    public void clickSin(View view) {
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText(String.valueOf(Math.sin(leftNumber)));
        changeButtonsState(true);
        isDone = true;
    }
    public void clickCos(View view) {
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText(String.valueOf(Math.cos(leftNumber)));
        changeButtonsState(true);
        isDone = true;
    }
    public void clickTan(View view) {
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText(String.valueOf(Math.tan(leftNumber)));
        changeButtonsState(true);
        isDone = true;
    }
    public void clickPow2(View view) {
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText(String.valueOf(Math.pow(leftNumber, 2)));
        changeButtonsState(true);
        isDone = true;
    }
    public void clickPowy(View view) {
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        operationType = "powy";
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText("0");
        changeButtonsState(false);
        isDone = false;
    }
    public void clickLog(View view) {
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        leftNumber = Double.parseDouble(actualState);
        if(leftNumber <= 0)
            sendToast("Number must be positive");
        else
            resultBox.setText(String.valueOf(Math.log10(leftNumber)));
        changeButtonsState(true);
        isDone = true;
    }
    public void clickLn(View view) {
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        leftNumber = Double.parseDouble(actualState);
        if(leftNumber <= 0)
            sendToast("Number must be positive");
        else
            resultBox.setText(String.valueOf(Math.log(leftNumber)));
        changeButtonsState(true);
        changeButtonsState(true);
        isDone = true;
    }
}
