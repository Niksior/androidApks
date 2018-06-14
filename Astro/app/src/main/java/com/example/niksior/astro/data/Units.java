package com.example.niksior.astro.data;

import org.json.JSONObject;

public class Units implements JSONPopulator {

    private String tempertature;

    @Override
    public void populate(JSONObject data) {
        this.tempertature = data.optString("temperature");
    }

    public String getTempertature() {
        return tempertature;
    }
}
