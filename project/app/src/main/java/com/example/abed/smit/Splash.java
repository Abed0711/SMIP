package com.example.abed.smit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.Wave;

public class Splash extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        progressBar=findViewById(R.id.spinKit);

        Wave wave = new Wave();

        progressBar.setIndeterminateDrawable(wave);


        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2500);
                    Intent intent = new Intent(getApplicationContext(),LoginMain.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
