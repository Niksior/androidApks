package com.example.niksior.test;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private MyViewModel myViewModel;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewModel = ViewModelProviders.of(MainActivity.class).get(MyViewModel.class);
        Details details = myViewModel.getDetails();

        listView= findViewById(R.id.lista);
        listView.setAdapter(new ArrayAdapter<>(this,
                R.layout.activity_main, details.getList()));
    }

    public void listClick(View view) {
        Details details = new Details();
        myViewModel.setDetails(details);
    }
}
