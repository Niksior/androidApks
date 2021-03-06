package com.example.niksior.astro.fragments;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.niksior.astro.R;

import java.util.Objects;

public class MoreWeatherFragment extends Fragment {

    private TextView[] maxTemp = new TextView[3];
    private TextView[] minTemp = new TextView[3];
    private ImageView[] dayView = new ImageView[3];
    private TextView[] dayText = new TextView[3];


    private SharedPreferences sharedPreferences;
    private int resource[] = new int[3];

    private String temperatureSettings;
    private String temperatureUnit;
    private Drawable[] weatherIconDrawables = new Drawable[3];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_more_weather_fragment, container, false);

        for(int i=0; i<3; i++){
            maxTemp[i] = rootView.findViewById(getResources().getIdentifier("maxTemp" + (i+1), "id", getContext().getPackageName()));
            minTemp[i] = rootView.findViewById(getResources().getIdentifier("minTemp" + (i+1), "id", getContext().getPackageName()));
            dayView[i] = rootView.findViewById(getResources().getIdentifier("dayView" + (i+1), "id", getContext().getPackageName()));
            dayText[i] = rootView.findViewById(getResources().getIdentifier("dayText" + (i+1) , "id", getContext().getPackageName()));
        }

        ustawPogode();
        return rootView;
    }

    public void ustawPogode() {
        sharedPreferences = Objects.requireNonNull(getContext()).getSharedPreferences("weather", 0);
        temperatureSettings = sharedPreferences.getString("j_temp", "F");
        for(int i=0; i<3; i++){
            resource[i] = getResources().getIdentifier("icon_" + sharedPreferences.getString("icon_" + (i + 1), "1"), "drawable", Objects.requireNonNull(getContext()).getPackageName());
            weatherIconDrawables[i] = getResources().getDrawable(resource[i], null);
            maxTemp[i].setText(String.format("%s%s", zmienJednostke(sharedPreferences.getString("temp_maks_" + (i + 1), "1")), temperatureUnit));
            minTemp[i].setText(String.format("%s%s", zmienJednostke(sharedPreferences.getString("temp_min_" + (i + 1), "1")), temperatureUnit));
            dayView[i].setImageDrawable(weatherIconDrawables[i]);
            dayText[i].setText(sharedPreferences.getString("dzien_" + (i + 1), "NULL"));
        }
    }

    private String zmienJednostke(String temperatureValue){
        if(temperatureSettings.equals("F") || temperatureSettings.equals("NULL")){
            temperatureUnit = "°F";
        }
        else{
            temperatureUnit = "°C";
            temperatureValue = String.valueOf((int)((Integer.parseInt(temperatureValue)-32)/1.8));
        }
        return temperatureValue;
    }

    @Override
    public void onResume() {
        ustawPogode();
        super.onResume();
    }

    @Override
    public void onPause() {
        ustawPogode();
        super.onPause();
    }

    @Override
    public void onStart() {
        ustawPogode();
        super.onStart();
    }

    @Override
    public void onStop() {
        ustawPogode();
        super.onStop();
    }
}
