package com.example.a202246.astroweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a202246.astroweather.fragments.*;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return SunFragment.newInstance();
        } else if (position == 1){
            return MoonFragment.newInstance();
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}