package com.example.niksior.astro.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astrocalculator.AstroCalculator;
import com.example.niksior.astro.AstroInit;
import com.example.niksior.astro.R;

import java.util.Objects;

public class MoonFragment extends Fragment {

    TextView wschodK, zachodK, now, pelnia, faza, dzien;

    int refreshTime = 1;
    Thread thread;

    AstroCalculator astroCalculator;
    SharedPreferences sharedPreferences;
    private final String filename = "info";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_moon_fragment, container, false);

        wschodK = rootView.findViewById(R.id.wschodK);
        zachodK = rootView.findViewById(R.id.zachodK);
        now = rootView.findViewById(R.id.now);
        pelnia = rootView.findViewById(R.id.pelnia);
        faza = rootView.findViewById(R.id.faza);
        dzien = rootView.findViewById(R.id.dzien);

        sharedPreferences = Objects.requireNonNull(getActivity()).getSharedPreferences(filename, 0);
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

        thread.start();


        return rootView;
    }

    private void ustawWartosci() {
        wschodK.setText(astroCalculator.getMoonInfo().getMoonrise().toString());
        zachodK.setText(astroCalculator.getMoonInfo().getMoonset().toString());
        now.setText(astroCalculator.getMoonInfo().getNextNewMoon().toString());
        pelnia.setText(astroCalculator.getMoonInfo().getNextFullMoon().toString());
        faza.setText((String.valueOf(astroCalculator.getMoonInfo().getIllumination()) + "%"));
        dzien.setText( String.valueOf(astroCalculator.getMoonInfo().getAge()));
    }

    public void refresh(){
        if(getActivity() != null){
            astroCalculator = new AstroInit(sharedPreferences).getAstroCalculator();
            refreshTime = Integer.valueOf(sharedPreferences.getString("ods", null));
        }
    }

    @Override
    public void onStop(){
        refresh();
        super.onStop();
    }

    @Override
    public void onResume() {
        refresh();
        ustawWartosci();
        super.onResume();
    }

    @Override
    public void onPause() {
        refresh();
        super.onPause();
    }

    @Override
    public void onStart() {
        refresh();
        super.onStart();
    }
}
