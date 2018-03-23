package com.example.niksior.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static java.lang.System.exit;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void lauchSimpleCalc (View view){
        Intent intent = new Intent(this, SimpleCalc.class);
        startActivity(intent);
    }

    public void launchCredits (View view) {
        Intent intent = new Intent(this, Credits.class);
        startActivity(intent);
    }

    public void launchAdvancedCalc (View view) {
        Intent intent = new Intent(this, AdvancedCalc.class);
        startActivity(intent);
    }

    public void exitApp (View view) {
        exit(1);
    }
}
