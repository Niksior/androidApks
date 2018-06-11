package com.example.a202246.astroweather.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a202246.astroweather.R;

public class SunFragment extends Fragment {

    public SunFragment() {
    }

    public static SunFragment newInstance() {
        SunFragment fragment = new SunFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sun_fragment_main, container, false);
        return rootView;
    }
}