package com.example.niksior.myapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdvancedCalc extends SimpleCalc implements Controlls {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calc);
    }

    public void changeButtonsState(boolean state) {
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
                    Context context = getApplicationContext();
                    CharSequence text = "You can't divide by 0!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
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
        }

        leftNumber = result;
        operationType = "none";
        resultBox.setText(String.valueOf(result));
        changeButtonsState(true);
        isDone = true;
    }


}
