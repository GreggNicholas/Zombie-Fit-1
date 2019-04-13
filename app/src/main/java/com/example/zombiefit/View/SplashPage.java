package com.example.zombiefit.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zombiefit.R;
import com.squareup.picasso.Picasso;

import static java.lang.Thread.sleep;

public class SplashPage extends AppCompatActivity {
    private Runnable runnable;
    private ImageView gif;
    private TextView title;
    private ActionBar actionBar;
    private View splashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        splashView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        splashView.setSystemUiVisibility(uiOptions);
        actionBar = getSupportActionBar();
        actionBar.hide();


        title = findViewById(R.id.splash_textview);
        gif = findViewById(R.id.splash_imageview);
        Picasso.get().load(R.drawable.splash).into(gif);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                SplashPage.this.startActivity(intent);
                SplashPage.this.finish();
            }
        }, 4000);
    }
        }
//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    sleep(20000);
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//    }
//}
