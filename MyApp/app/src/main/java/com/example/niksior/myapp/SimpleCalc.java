package com.example.niksior.myapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleCalc extends Activity implements Controlls {

    protected double leftNumber = 0;
    protected String savedState = "0";
    protected String operationType = "none";
    protected boolean isDone = false;
    protected double rightNumber = 0;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView resultBox = findViewById(R.id.resultBox);
        savedState = (String) resultBox.getText();
        savedInstanceState.putDouble("leftNum", leftNumber);
        savedInstanceState.putString("savedState", savedState);
        savedInstanceState.putString("operation", operationType);
        savedInstanceState.putDouble("rightNum", rightNumber);
        savedInstanceState.putBoolean("state", isDone);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView resultBox = findViewById(R.id.resultBox);
        leftNumber = savedInstanceState.getDouble("leftNum");
        savedState = savedInstanceState.getString("savedState");
        operationType = savedInstanceState.getString("operation");
        rightNumber = savedInstanceState.getDouble("rightNum");
        isDone = savedInstanceState.getBoolean("state");
        if(operationType.equals("none"))
            changeButtonsState(true);
        else
            changeButtonsState(false);
        resultBox.setText(savedState);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("ourString", ourString);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ourString = savedInstanceState.getString("ourString");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);
    }

    public void click0(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(isDone)
            resultBox.setText("0");
        else if(actualState.length() > 1 || (actualState.indexOf('0') != 0 && actualState.length() == 1)) {
            operationType = "none";
            resultBox.setText(resultBox.getText() + "0");
        }
        isDone = false;
    }

    public void click1(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && Double.parseDouble(actualState) == 0 || isDone)
            resultBox.setText("1");
        else {
            operationType = "none";
            resultBox.setText(resultBox.getText() + "1");
        }
        isDone = false;
    }

    public void click2(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && Double.parseDouble(actualState) == 0 || isDone)
            resultBox.setText("2");
        else {
            operationType = "none";
            resultBox.setText(resultBox.getText() + "2");
        }
        isDone = false;
    }
    public void click3(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && Double.parseDouble(actualState) == 0 || isDone)
            resultBox.setText("3");
        else {
            operationType = "none";
            resultBox.setText(resultBox.getText() + "3");
        }
        isDone = false;
    }
    public void click4(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && Double.parseDouble(actualState) == 0 || isDone)
            resultBox.setText("4");
        else {
            operationType = "none";
            resultBox.setText(resultBox.getText() + "4");
        }
        isDone = false;
    }
    public void click5(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && Double.parseDouble(actualState) == 0 || isDone)
            resultBox.setText("5");
        else {
            operationType = "none";
            resultBox.setText(resultBox.getText() + "5");
        }
        isDone = false;
    }
    public void click6(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && Double.parseDouble(actualState) == 0 || isDone)
            resultBox.setText("6");
        else {
            operationType = "none";
            resultBox.setText(resultBox.getText() + "6");
        }
        isDone = false;
    }
    public void click7(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && Double.parseDouble(actualState) == 0 || isDone)
            resultBox.setText("7");
        else {
            operationType = "none";
            resultBox.setText(resultBox.getText() + "7");
        }
        isDone = false;
    }
    public void click8(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && Double.parseDouble(actualState) == 0 || isDone)
            resultBox.setText("8");
        else {
            operationType = "none";
            resultBox.setText(resultBox.getText() + "8");
        }
        isDone = false;
    }
    public void click9(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && Double.parseDouble(actualState) == 0 || isDone)
            resultBox.setText("9");
        else {
            operationType = "none";
            resultBox.setText(resultBox.getText() + "9");
        }
        isDone = false;
    }
    public void clickDot(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.indexOf('.') == -1) {
            resultBox.setText(resultBox.getText() + ".");
        }
    }
    public void clickSign(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        double tmp = Double.parseDouble(actualState) * -1;
        if(actualState.length() == 1 && Double.parseDouble(actualState) == 0 || isDone)
            resultBox.setText("0");
        else
        resultBox.setText(String.valueOf(tmp));
    }
    public void clickClear(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText("0");
        leftNumber = 0;
        operationType = "none";
        rightNumber = 0;
        changeButtonsState(true);
    }
    public void clickC(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1) {
            actualState = "0";
        }
        if(actualState.length() > 1) {
            actualState = actualState.substring(0, actualState.length()-1);
        }
        resultBox.setText(actualState);
    }
    public void clickMul(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        operationType = "mul";
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText("0");
        changeButtonsState(false);
    }
    public void clickAdd(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        operationType = "add";
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText("0");
        changeButtonsState(false);
    }
    public void clickSub(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        operationType = "sub";
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText("0");
        changeButtonsState(false);
    }
    public void clickDiv(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        operationType = "div";
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText("0");
        changeButtonsState(false);
    }
    public void clickEq(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        double result = 0;

        if(!isDone)
            rightNumber = Double.parseDouble(actualState);

        switch (operationType) {
            case "div":
                if (Double.parseDouble(actualState) == 0) {
                    sendToast("You can't divide by 0");
                } else {
                    result = leftNumber / rightNumber;
                }
                break;
            case "add":
                result = leftNumber + rightNumber;
                break;
            case "mul":
                result = leftNumber * rightNumber;
                break;
            case "sub":
                result = leftNumber - rightNumber;
                break;
        }


        leftNumber = result;
        resultBox.setText(String.valueOf(result));
        changeButtonsState(true);
        isDone = true;
    }

    public void changeButtonsState(boolean state) {
        Button button1 = findViewById(R.id.additionButton);
        Button button2 = findViewById(R.id.multiplyButton);
        Button button3 = findViewById(R.id.divideButton);
        Button button4 = findViewById(R.id.subtractionButton);

        button1.setEnabled(state);
        button2.setEnabled(state);
        button3.setEnabled(state);
        button4.setEnabled(state);
    }

    public void sendToast(String message) {
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }
}
