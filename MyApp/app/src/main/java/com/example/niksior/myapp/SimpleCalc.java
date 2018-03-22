package com.example.niksior.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SimpleCalc extends Activity {

    private double result = 0;
    private double leftNumber = 0;
    private double rightNumber = 0;
    private String savedState;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView resultBox = findViewById(R.id.resultBox);
        savedState = (String) resultBox.getText();
        savedInstanceState.putDouble("result", result);
        savedInstanceState.putString("savedState", savedState);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView resultBox = findViewById(R.id.resultBox);
        result = savedInstanceState.getDouble("result");
        savedState = savedInstanceState.getString("savedState");
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
        if(actualState.length() > 0)
            resultBox.setText(resultBox.getText() + "0");
    }

    public void click1(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "1");
    }

    public void click2(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "2");
    }
    public void click3(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "3");
    }
    public void click4(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "4");
    }
    public void click5(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "5");
    }
    public void click6(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "6");
    }
    public void click7(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "7");
    }
    public void click8(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "8");
    }
    public void click9(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "9");
    }
    public void clickDot(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        String actualState = (String) resultBox.getText();
        if(actualState.indexOf('.') == -1) {
            if(actualState.length() == 0)
                resultBox.setText("0");
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
        resultBox.setText("");
        leftNumber = 0;
        rightNumber = 0;
        result = 0;
    }
    /*public void click1(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "1");
    }
    public void click1(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "1");
    }
    public void click1(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "1");
    }
    public void click1(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "1");
    }
    public void click1(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "1");
    }
    public void click1(View view){
        TextView resultBox = findViewById(R.id.resultBox);
        resultBox.setText(resultBox.getText() + "1");
    }*/



}
