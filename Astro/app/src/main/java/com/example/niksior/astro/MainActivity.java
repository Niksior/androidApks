package com.example.niksior.astro;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    TextView textWys, textSzer;
    Thread thread;

    ViewPager viewPager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager1);
        pagerAdapter = new BasicPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

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
        SharedPreferences sharedPreferences = getSharedPreferences("info.xml", 0);
        textWys.setText(sharedPreferences.getString("Wys", String.valueOf(51.5873166)));
        textSzer.setText(sharedPreferences.getString("szer", String.valueOf(19.7543569)));
    }

    @Override
    public void onStop() {
        thread.interrupt();
        super.onStop();
    }

    @Override
    public void onRestart() {
        pokazDane();
        super.onRestart();
    }



}
