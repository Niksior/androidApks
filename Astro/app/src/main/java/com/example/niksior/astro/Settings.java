package com.example.niksior.astro;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.DoubleBuffer;

public class Settings extends AppCompatActivity {

    TextView szerInput, wysInput, odswInput, miastoInput;
    Button zapisz;
    Switch czymSzukac;
    SharedPreferences sharedPreferences;
    final String filename = "info";


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("aktualnaWyskosc", String.valueOf(szerInput.getText()));
        outState.putString("aktualnaSzerokosc", String.valueOf(wysInput.getText()));
        outState.putString("aktualneOdswiezanie", String.valueOf(odswInput.getText()));
        outState.putString("aktualneMiasto", String.valueOf(miastoInput.getText()));
        outState.putBoolean("pozycja", czymSzukac.isChecked());
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
        miastoInput = findViewById(R.id.miastoInput);
        zapisz = findViewById(R.id.zapisz);
        czymSzukac = findViewById(R.id.czymSzukac);


        if (savedInstanceState != null) {
            szerInput.setText(savedInstanceState.getString("aktualnaWyskosc"));
            wysInput.setText(savedInstanceState.getString("aktualnaSzerokosc"));
            odswInput.setText(savedInstanceState.getString("aktualneOdswiezanie"));
            miastoInput.setText(savedInstanceState.getString("aktualneMiasto"));
            czymSzukac.setChecked(savedInstanceState.getBoolean("pozycja"));
        } else {
            szerInput.setText(sharedPreferences.getString("sze", null));
            wysInput.setText(sharedPreferences.getString("wys", null));
            odswInput.setText(sharedPreferences.getString("ods", null));
            miastoInput.setText(sharedPreferences.getString("miasto", null));
        }

        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Double tmp = Double.parseDouble(String.valueOf(szerInput.getText()));
                    Double tmp2 = Double.parseDouble(String.valueOf(wysInput.getText()));
                    int tmp3 = Integer.parseInt(String.valueOf(odswInput.getText()));
                    if((tmp > 90 || tmp < -90) || (tmp2 > 180 || tmp2 < -180) || tmp3 < 1) {
                        Toast.makeText(Settings.this, "Niepoprawne liczby", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("wys", String.valueOf(wysInput.getText()));
                        editor.putString("sze", String.valueOf(szerInput.getText()));
                        editor.putString("ods", String.valueOf(odswInput.getText()));
                        editor.putString("miasto", String.valueOf(miastoInput.getText()));
                        if(czymSzukac.isChecked())
                            editor.putString("szukanie_miastem", "1");
                        else
                            editor.putString("szukanie_miastem", "0");
                        editor.apply();
                        Toast.makeText(Settings.this, "Zapisano", Toast.LENGTH_SHORT).show();
                    }
                }   catch (Exception e) {
                    Toast.makeText(Settings.this, "Niepoprawne liczby", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsButton:
                return true;

            case R.id.exitButton:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
