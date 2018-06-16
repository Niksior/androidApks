package com.example.niksior.astro.fragments;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.niksior.astro.R;

import java.util.Objects;

public class WeatherFragment extends Fragment {

    ImageView ikonaPogody;
    TextView temperatura, cisnienie, kraj, miasto, opis;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_weather_fragment, container, false);

        ikonaPogody = rootView.findViewById(R.id.ikonaPogody);
        temperatura = rootView.findViewById(R.id.tempTekst);
        cisnienie = rootView.findViewById(R.id.cisnienieTekst);
        kraj = rootView.findViewById(R.id.krajTekst);
        miasto = rootView.findViewById(R.id.miastoTekst);
        opis = rootView.findViewById(R.id.opisTekst);

        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences("weather", 0);

        ustawPogode();

        return rootView;
    }

    private void ustawPogode() {
        int temp = Integer.parseInt(sharedPreferences.getString("aktualna_temperatura", "1"));
        if(Objects.requireNonNull(sharedPreferences.getString("j_temp", "F")).equalsIgnoreCase("C")) {
            Double d = (temp - 32) / 1.8;
            temp = d.intValue();
        }
        String tmp = String.valueOf(temp) + "°" + sharedPreferences.getString("j_temp", "F");
        temperatura.setText(tmp);
        cisnienie.setText((sharedPreferences.getString("cisnienie", "1") + "hPa"));
        kraj.setText(sharedPreferences.getString("kraj", "Polska"));
        miasto.setText(sharedPreferences.getString("miasto", "Łódź"));
        opis.setText(sharedPreferences.getString("opis", " "));

        Drawable drawable = getResources().getDrawable(getResources().getIdentifier("icon_" + sharedPreferences.getString("aktualny_obrazek", "1"), "drawable", Objects.requireNonNull(getContext()).getPackageName()), null);
        ikonaPogody.setImageDrawable(drawable);
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
