package com.example.niksior.astro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    TextView textWys, textSzer;
    Thread thread;

    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    SharedPreferences sharedPreferences;
    private final String filename = "info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager1);
        pagerAdapter = new BasicPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
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
    }

    private void pokazDane() {

        textWys.setText(sharedPreferences.getString("wys", null));
        textSzer.setText(sharedPreferences.getString("sze", null));
    }

    private void ustawUstawienia() {
        if(!sharedPreferences.getBoolean("firstLaunch", false)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstLaunch", true);
            editor.putString("wys", "51.5873166");
            editor.putString("sze", "19.7543569");
            editor.putString("ods", "1");
            editor.apply();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.settingsButton:
                startActivity(new Intent(this, Settings.class));
                return true;

            case R.id.exitButton:
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onStop() {
//        thread.interrupt();
//        super.onStop();
//    }

    @Override
    public void onRestart() {
        pokazDane();
        super.onRestart();
    }



}
