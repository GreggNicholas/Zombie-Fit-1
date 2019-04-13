package com.example.zombiefit.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zombiefit.R;

import static java.lang.Thread.sleep;

public class SplashPage extends AppCompatActivity {
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(20000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
    }
}
