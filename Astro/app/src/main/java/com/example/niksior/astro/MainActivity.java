package com.example.niksior.astro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niksior.astro.data.Atmosphere;
import com.example.niksior.astro.data.Channel;
import com.example.niksior.astro.data.Item;
import com.example.niksior.astro.data.Location;
import com.example.niksior.astro.data.Wind;
import com.example.niksior.astro.service.WeatherServiceCallback;
import com.example.niksior.astro.service.YahooWeatherService;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    TextView textWys, textSzer;
    Thread thread;

    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    SharedPreferences sharedPreferences, sharedWeatherPreferences;
    private final String filename = "info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager1);
        Configuration configuration = getResources().getConfiguration();

        if (!czyTablet(configuration)) {
            pagerAdapter = new BasicPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pagerAdapter);
        }
        sharedPreferences = getSharedPreferences(filename, 0);

        ustawUstawienia();

        thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = findViewById(R.id.textZegar);
                                long date = System.currentTimeMillis();
                                tdate.setText(SimpleDateFormat.getInstance().format(date));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    System.out.println("Clock failed");
                }
            }
        };

        thread.start();

        textSzer = findViewById(R.id.textSzer);
        textWys = findViewById(R.id.textWys);

        pokazDane();
        ustawPogode();
    }

    private void ustawPogode() {
        String cityToSearch = sharedPreferences.getString("miasto", null);
        String optionSetting = sharedPreferences.getString("szukanie_miastem", null);
        String latitudeSend = sharedPreferences.getString("wys", null);
        String longitudeSend = sharedPreferences.getString("sze", null);

        YahooWeatherService yahooWeatherService = new YahooWeatherService(this, cityToSearch, optionSetting, latitudeSend, longitudeSend);
        yahooWeatherService.refreshWeather();
        sharedWeatherPreferences = getSharedPreferences("weather", 0);
    }

    private void pokazDane() {

        textWys.setText(sharedPreferences.getString("wys", null));
        textSzer.setText(sharedPreferences.getString("sze", null));
    }

    private void ustawUstawienia() {
        if (!sharedPreferences.getBoolean("firstLaunch", false)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstLaunch", true);
            editor.putString("wys", "51.5873166");
            editor.putString("sze", "19.7543569");
            editor.putString("ods", "1");
            editor.putString("miasto", "Lodz");
            editor.putString("szukanie_miastem", "1");
            editor.apply();
        }
    }

    public boolean czyTablet(Configuration config) {
        boolean xlarge = ((config.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((config.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    @Override
    public void serviceSuccess(Channel channel) {
        Item item = channel.getItem();
        Location location = channel.getLocation();
        Atmosphere atmosphere = channel.getAtmosphere();
        Wind wind = channel.getWind();
        SharedPreferences.Editor editor = sharedWeatherPreferences.edit();

        editor.putString("miasto", location.getCity());
        editor.putString("kraj", location.getCountry());
        editor.putString("kierunek_wiatru", wind.getDirection());
        editor.putString("predkosc_wiatru", wind.getSpeed());
        editor.putString("wilgotnosc", atmosphere.getHumidity());
        editor.putString("cisnienie", atmosphere.getPressure());
        editor.putString("szer", item.getLongitude());
        editor.putString("wys", item.getLatitude());
        editor.putString("widocznosc", atmosphere.getVisibility());
        editor.putString("aktualny_obrazek", item.getCondition().getCode());
        editor.putString("aktualna_temperatura", item.getCondition().getTemperature());
        editor.putString("aktualny_opis", item.getCondition().getDescription());

        for (int i = 0; i < 3; i++) {
            editor.putString("kod_obrazku_" + i, item.getForecast(i).getCodeImage());
            editor.putString("dzien_" + i, item.getForecast(i).getDay());
            editor.putString("temp_maks_" + i, item.getForecast(i).getHighTemperature());
            editor.putString("temp_min_" + i, item.getForecast(i).getLowTemperature());
            editor.putString("opis_" + i, item.getForecast(i).getDescription());
        }
        editor.apply();

        pokazDane();
    }

    @Override
    public void serviceFailure(Exception exception) {
        Toast.makeText(MainActivity.this, "No internet connection!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsButton:
                startActivity(new Intent(this, Settings.class));
                return true;

            case R.id.exitButton:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                return true;

            case R.id.unitsButton:
                startActivity(new Intent(this, UnitSettings.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        thread.interrupt();
        super.onStop();
    }

    @Override
    public void onRestart() {
        pokazDane();
        ustawPogode();
        super.onRestart();
    }

    @Override
    public void onResume() {
        pokazDane();
        ustawPogode();
        super.onResume();
    }

}
