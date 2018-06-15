package com.example.niksior.astro.fragments;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.niksior.astro.R;

import java.util.Objects;

public class ExtendedWeatherFragment extends Fragment {

    private TextView wiatrTekst;
    private TextView kierunekTekst;
    private TextView wilgotnoscTekst;
    private TextView widocznoscTekst;

    private SharedPreferences sharedPreferences;

    private String speedSettings;
    private String speedUnit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_extended_weather_fragment, container, false);

        wiatrTekst = rootView.findViewById(R.id.wiatrTekst);
        kierunekTekst = rootView.findViewById(R.id.kierunekTekst);
        wilgotnoscTekst = rootView.findViewById(R.id.wilgotnoscTekst);
        widocznoscTekst = rootView.findViewById(R.id.widocznoscTekst);
        sharedPreferences = Objects.requireNonNull(getContext()).getSharedPreferences("weather", 0);

        ustawPogode();

        return rootView;
    }

    private void ustawPogode(){
        wiatrTekst.setText(String.format("Prędkość wiatru: %s %s", setUnit(sharedPreferences.getString("predkosc_wiatru", "NULL")), speedUnit));
        kierunekTekst.setText(String.format("Kierunek wiatru: %s", sharedPreferences.getString("kierunek_wiatru", "NULL")));
        wilgotnoscTekst.setText(String.format("Wilgotność: %s %%", sharedPreferences.getString("wilgotnosc", "NULL")));
        widocznoscTekst.setText(String.format("Widoczność: %s %%", sharedPreferences.getString("widocznosc", "1")));
    }

    private String setUnit(String windSpeed){
        speedSettings = sharedPreferences.getString("j_pred", "M");
        if(speedSettings.equals("M") || speedSettings.equals("NULL")){
            speedUnit = "mp/h";
        }
        else{
            speedUnit = "km/h";
            windSpeed = String.valueOf((int)(Double.parseDouble(windSpeed) * 1.61));
        }
        return windSpeed;
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

}
