package com.example.niksior.astro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.niksior.astro.fragments.MoonFragment;
import com.example.niksior.astro.fragments.SunFragment;
import com.example.niksior.astro.fragments.WeatherFragment;

public class BasicPagerAdapter extends FragmentStatePagerAdapter {

    BasicPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                return new SunFragment();
            }
            case 1: {
                return new MoonFragment();
            }
            case 2: {
                return new WeatherFragment();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
