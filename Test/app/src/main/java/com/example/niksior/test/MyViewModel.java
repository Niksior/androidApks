package com.example.niksior.test;


import android.arch.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    public Details mDetails;

    public void createDetails() {
        this.mDetails = new Details();
    }

    public Details getDetails() {
        return mDetails;
    }

    public void setDetails(Details details) {
        mDetails = details;
    }

}
