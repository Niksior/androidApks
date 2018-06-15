package com.example.niksior.astro.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.example.niksior.astro.AstroInit;
import com.example.niksior.astro.R;

import java.util.Objects;

public class SunFragment extends Fragment {

    TextView wschod, zachod, azWschod, azZachod, swit, zmierzch;
    int refreshTime = 1;
    Thread thread;

    AstroCalculator astroCalculator;
    SharedPreferences sharedPreferences;
    private final String filename = "info";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_sun_fragment, container, false);
        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(filename, 0);

        wschod = rootView.findViewById(R.id.wschod);
        zachod = rootView.findViewById(R.id.zachod);
        azWschod = rootView.findViewById(R.id.az_wschod);
        azZachod = rootView.findViewById(R.id.az_zach);
        swit = rootView.findViewById(R.id.swit);
        zmierzch = rootView.findViewById(R.id.zmierch);

        astroCalculator = new AstroInit(sharedPreferences).getAstroCalculator();
        ustawWartosci();

        thread = new Thread() {

            @Override
            public void run() {
                try {

                    while (!isInterrupted()) {
                        refresh();
                        Thread.sleep(refreshTime*1000);
                        if(getActivity() != null){
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ustawWartosci();
                                }
                            });
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        return rootView;
    }

    private void ustawWartosci() {
        wschod.setText(astroCalculator.getSunInfo().getSunrise().toString());
        zachod.setText(astroCalculator.getSunInfo().getSunset().toString());
        azWschod.setText(String.valueOf(astroCalculator.getSunInfo().getAzimuthRise()));
        azZachod.setText(String.valueOf(astroCalculator.getSunInfo().getAzimuthSet()));
        zmierzch.setText(astroCalculator.getSunInfo().getTwilightEvening().toString());
        swit.setText( astroCalculator.getSunInfo().getTwilightMorning().toString());
    }

    public void refresh(){
        if(getActivity() != null){
            astroCalculator = new AstroInit(sharedPreferences).getAstroCalculator();
            refreshTime = Integer.valueOf(sharedPreferences.getString("ods", null));
        }
    }

    @Override
    public void onResume() {
        refresh();
        super.onResume();
    }


}
