package com.example.niksior.test2;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText =  findViewById(R.id.text);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        TextView textView = findViewById(R.id.textView2);
        textView.setText(myViewModel.getString());
    }

    public void listClick(View view) {
        myViewModel.setString(editText.getText().toString());
    }


}