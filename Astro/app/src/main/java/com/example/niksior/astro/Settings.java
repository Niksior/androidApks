package com.example.niksior.astro;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.DoubleBuffer;

public class Settings extends AppCompatActivity {

    TextView szerInput, wysInput, odswInput;
    Button zapisz;
    SharedPreferences sharedPreferences;
    final String filename = "info";


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("savedLatitude", String.valueOf(szerInput.getText()));
        outState.putString("savedLongitude", String.valueOf(wysInput.getText()));
        outState.putString("savedRefresh", String.valueOf(odswInput.getText()));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = getSharedPreferences(filename, 0);

        szerInput = findViewById(R.id.szerInput);
        wysInput = findViewById(R.id.wysInput);
        odswInput = findViewById(R.id.odswInput);
        zapisz = findViewById(R.id.zapisz);

        if (savedInstanceState != null) {
            szerInput.setText(savedInstanceState.getString("savedLatitude"));
            wysInput.setText(savedInstanceState.getString("savedLongitude"));
            odswInput.setText(savedInstanceState.getString("savedRefresh"));
        } else {
            szerInput.setText(sharedPreferences.getString("sze", null));
            wysInput.setText(sharedPreferences.getString("wys", null));
            odswInput.setText(sharedPreferences.getString("ods", null));
        }

        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Double tmp = Double.parseDouble(String.valueOf(szerInput.getText()));
                    Double tmp2 = Double.parseDouble(String.valueOf(wysInput.getText()));
                    int tmp3 = Integer.parseInt(String.valueOf(odswInput.getText()));
                    if((tmp > 90 || tmp < -90) || (tmp2 > 180 || tmp2 < -180) || tmp3 < 1) {
                        Toast.makeText(Settings.this, "Wrong numbers!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("wys", String.valueOf(wysInput.getText()));
                        editor.putString("sze", String.valueOf(szerInput.getText()));
                        editor.putString("ods", String.valueOf(odswInput.getText()));
                        editor.apply();
                        Toast.makeText(Settings.this, "Saved values!", Toast.LENGTH_SHORT).show();
                    }
                }   catch (Exception e) {
                    Toast.makeText(Settings.this, "Wrong numbers!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
