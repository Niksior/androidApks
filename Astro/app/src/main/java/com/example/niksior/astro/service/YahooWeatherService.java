package com.example.niksior.astro.service;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.niksior.astro.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private Exception error;
    private String location;
    private String latitude;
    private String longitude;
    private String YQL;
    String option;

    public YahooWeatherService(WeatherServiceCallback callback, String location, String option, String latitude, String longitude)
    {
        this.callback = callback;
        this.location=location;
        this.option = option;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @SuppressLint("StaticFieldLeak")
    public void refreshWeather() {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                System.out.println(option);
                if(option.equals("1")){
                    YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", location);
                }
                else if(option.equals("0")){
                    String latAndLong = latitude + "," + longitude;
                    System.out.println(latAndLong);
                    YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"(%s)\")", latAndLong);
                }

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();
                } catch (Exception e) {
                    error = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s == null && error != null) {
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.getJSONObject("query");

                    int count = queryResults.optInt("count");

                    if (count == 0) {
                        callback.serviceFailure(new Exception());
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));

                    callback.serviceSuccess(channel);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(location);
    }
}
