package com.example.niksior.astro.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;
import com.example.niksior.astro.R;

public class SunFragment extends Fragment {

    TextView wschod, zachod, azWschod, azZachod, swit, zmierzch;
    int refreshTime = 1;

    AstroCalculator astroCalculator;
    AstroCalculator.Location location;
    AstroDateTime astroDateTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_sun_fragment, container, false);

        return rootView;
    }

    private void ustawWartosci() {
        wschod.setText((CharSequence) astroCalculator.getSunInfo().getSunrise());
        zachod.setText((CharSequence) astroCalculator.getSunInfo().getSunset());
        azWschod.setText(String.valueOf(astroCalculator.getSunInfo().getAzimuthRise()));
        azZachod.setText(String.valueOf(astroCalculator.getSunInfo().getAzimuthSet()));
        swit.setText((CharSequence) astroCalculator.getSunInfo().getTwilightEvening());
        zmierzch.setText((CharSequence) astroCalculator.getSunInfo().getTwilightMorning());
    }
}
