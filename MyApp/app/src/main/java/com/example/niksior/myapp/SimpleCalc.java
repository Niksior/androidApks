package com.example.niksior.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SimpleCalc extends Activity {

    private double leftNumber = 0;
    private String savedState = "0";
    private String operationType = "null";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView resultBox = findViewById(R.id.resultBox);
        savedState = (String) resultBox.getText();
        savedInstanceState.putDouble("leftNum", leftNumber);
        savedInstanceState.putString("savedState", savedState);
        savedInstanceState.putString("operation", operationType);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView resultBox = findViewById(R.id.resultBox);
        leftNumber = savedInstanceState.getDouble("leftNum");
        savedState = savedInstanceState.getString("savedState");
        operationType = savedInstanceState.getString("operation");
        resultBox.setText(savedState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple_calc);
    }

    public void click0(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() > 1 || (actualState.indexOf('0') != 0 && actualState.length() == 1))
            resultBox.setText(resultBox.getText() + "0");
    }

    public void click1(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && actualState.equals("0"))
            resultBox.setText("1");
        else
            resultBox.setText(resultBox.getText() + "1");
    }

    public void click2(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && actualState.equals("0"))
            resultBox.setText("2");
        else
            resultBox.setText(resultBox.getText() + "2");
    }
    public void click3(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && actualState.equals("0"))
            resultBox.setText("3");
        else
            resultBox.setText(resultBox.getText() + "3");
    }
    public void click4(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && actualState.equals("0"))
            resultBox.setText("4");
        else
            resultBox.setText(resultBox.getText() + "4");
    }
    public void click5(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && actualState.equals("0"))
            resultBox.setText("5");
        else
            resultBox.setText(resultBox.getText() + "5");
    }
    public void click6(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && actualState.equals("0"))
            resultBox.setText("6");
        else
            resultBox.setText(resultBox.getText() + "6");
    }
    public void click7(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && actualState.equals("0"))
            resultBox.setText("7");
        else
            resultBox.setText(resultBox.getText() + "7");
    }
    public void click8(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && actualState.equals("0"))
            resultBox.setText("8");
        else
            resultBox.setText(resultBox.getText() + "8");
    }
    public void click9(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.length() == 1 && actualState.equals("0"))
            resultBox.setText("9");
        else
            resultBox.setText(resultBox.getText() + "9");
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
        resultBox.setText(String.valueOf(tmp));
    }
    public void clickClear(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText("0");
        leftNumber = 0;
        operationType = "none";
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
        resultBox.setText("");
    }
    public void clickAdd(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        operationType = "add";
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText("");
    }
    public void clickSub(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        operationType = "sub";
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText("");
    }
    public void clickDiv(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        operationType = "div";
        leftNumber = Double.parseDouble(actualState);
        resultBox.setText("");
    }
    /*public void click1(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "1");
    }*/



}
