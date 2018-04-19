package com.example.niksior.test2;


import android.arch.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private String string = "";

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
