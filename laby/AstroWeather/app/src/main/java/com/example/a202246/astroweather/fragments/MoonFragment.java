package com.example.a202246.astroweather.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a202246.astroweather.R;

public class MoonFragment extends Fragment {

    public MoonFragment() {
    }

    public static MoonFragment newInstance() {
        MoonFragment fragment = new MoonFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.moon_fragment_main, container, false);
        return rootView;
    }
}
