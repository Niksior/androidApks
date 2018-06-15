package com.example.niksior.astro;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class UnitSettings extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RadioGroup unitGroup, unit2Group;
    RadioButton celRadio, farRadio, kmRadio, milRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_settings);

        sharedPreferences = getSharedPreferences("weather", 0);

        unitGroup = findViewById(R.id.unitGroup);
        unit2Group = findViewById(R.id.unit2Group);
        celRadio = findViewById(R.id.celRadio);
        farRadio = findViewById(R.id.farRadio);
        kmRadio = findViewById(R.id.kmRadio);
        milRadio = findViewById(R.id.milRadio);
        addListeners();
    }

    private void addListeners() {
        unitGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                editor = sharedPreferences.edit();
                switch (checkedId) {
                    case R.id.celRadio:
                        editor.putString("j_temp", "C");
                        break;
                    case R.id.farRadio :
                        editor.putString("j_temp", "F");
                        break;
                }
                editor.apply();
            }
        });

        unit2Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                editor = sharedPreferences.edit();
                switch (checkedId) {
                    case R.id.kmRadio:
                        editor.putString("j_pred", "K");
                        break;
                    case R.id.milRadio:
                        editor.putString("j_pred", "M");
                        break;
                }
                editor.apply();
            }
        });
    }

}
