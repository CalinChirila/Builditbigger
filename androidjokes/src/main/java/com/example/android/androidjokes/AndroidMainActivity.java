package com.example.android.androidjokes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AndroidMainActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA = "jokeExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_main);

        TextView androidJoke = findViewById(R.id.tv_android_joke);

        Intent intent = getIntent();
        if(intent != null){
            String joke = intent.getStringExtra(JOKE_EXTRA) + ", from android library";
            androidJoke.setText(joke);
        }

    }
}
