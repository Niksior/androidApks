package com.example.niksior.astro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class UlubioneActivity extends AppCompatActivity {

    private EditText ulubInput;
    private Button ulubButton;
    private Baza database;
    private Cursor data;
    private List<String> cityList;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulubione);

        initElements();
        displayCityList();

        ulubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = ulubInput.getText().toString();
                if (ulubInput.length() != 0) {
                    if (!cityList.contains(cityName)) {
                        addData(cityName);
                        ulubInput.setText("");
                        displayCityList();
                    } else {
                        Toast.makeText(UlubioneActivity.this, "Miasto już dodane", LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UlubioneActivity.this, "Podaj nazwe", LENGTH_SHORT).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityName = listView.getItemAtPosition(position).toString();
                SharedPreferences.Editor edit = sharedPref.edit();
                edit.putString("miasto", cityName);
                edit.apply();
            }
        });

    }

    private void initElements() {
        ulubInput = findViewById(R.id.ulubInput);
        listView = findViewById(R.id.ulubList);
        cityList = new ArrayList<>();
        database = new Baza(this);
        ulubButton = findViewById(R.id.ulubButton);
        sharedPref = getSharedPreferences("info", 0);
    }

    private void displayCityList() {
        data = database.getListContents();
        cityList.clear();
        while (data.moveToNext()) {
            cityList.add(data.getString(1));
        }
        arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view_row, cityList);
        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);
    }

    public void addData(String record) {
        boolean result = database.insertData(record);

        if (result) {
            Toast.makeText(UlubioneActivity.this, "Zapisano", LENGTH_SHORT).show();
        } else {
            Toast.makeText(UlubioneActivity.this, "Błąd", LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsButton:
                startActivity(new Intent(this, Settings.class));
                return true;

            case R.id.exitButton:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                return true;

            case R.id.unitsButton:
                startActivity(new Intent(this, UnitSettings.class));
                return true;

            case R.id.ulubioneButton:
                startActivity(new Intent(this, UlubioneActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
