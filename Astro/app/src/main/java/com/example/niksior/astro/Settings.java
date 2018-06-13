package com.example.niksior.astro;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                String saveTo;
                String checkValue;

                checkValue = String.valueOf(szerInput.getText());
                saveTo = "Latitude";
                checkValues(checkValue, sharedPreferences, 90, -90, saveTo);

                checkValue = String.valueOf(wysInput.getText());
                saveTo = "Longitude";
                checkValues(checkValue, sharedPreferences, 180, -180, saveTo);

                checkRefresh();

                Toast.makeText(Settings.this, "Saved values!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void checkValues(String checkValue, SharedPreferences sharedPref, int checkUpper, int checkLower, String saveTo){
        if(checkValue.equals("") ){
            Toast.makeText(Settings.this, "One of the values is empty!", Toast.LENGTH_SHORT).show();
        }
        else if(checkValue.endsWith(".")){
            checkValue = checkValue.substring(0, checkValue.length() - 1);
            szerInput.setText(checkValue);
        }
        else if(checkValue.matches("\\d+(?:\\.\\d+)?")){                                                                    //regex for latitude/longitude
            if((Double.valueOf(checkValue) <= checkUpper) && (Double.valueOf(checkValue) >= checkLower)){
                SharedPreferences.Editor edit = sharedPref.edit();
                if(saveTo.equals("Latitude")){
                    edit.putString("Latitude", checkValue);
                    edit.commit();
                }
                else if(saveTo.equals("Longitude")){
                    edit.putString("Longitude", checkValue);
                    edit.commit();
                }
            }
            else{
                Toast.makeText(Settings.this, "One of given values is bad!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(Settings.this, "One of given values is bad!", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkRefresh(){
        String refresh = String.valueOf(odswInput.getText());
        if (refresh.equals("")) {
            Toast.makeText(Settings.this, "One of give values is bad!", Toast.LENGTH_SHORT).show();
        } else if (refresh.endsWith(".")) {
            refresh = refresh.substring(0, refresh.length() - 1);
            odswInput.setText(refresh);
        } else if (refresh.matches("^[1-9]\\d*$")) {                                                                        //regex for timer
            if ((Integer.valueOf(refresh) <= 60) && (Integer.valueOf(refresh) >= 1)) {
                SharedPreferences sharedPref = getSharedPreferences("infoValues.xml", 0);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Refresh", refresh);
                editor.commit();
            } else {
                Toast.makeText(Settings.this, "Bad value in Minutes (1 : 60)!", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(Settings.this, "Bad value format in Minutes (1 : 60)!", Toast.LENGTH_SHORT).show();
        }
    }
}
