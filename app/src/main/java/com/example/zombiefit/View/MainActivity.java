package com.example.zombiefit.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zombiefit.Fragment.ZListWorkoutsFragment;
import com.example.zombiefit.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, ZListWorkoutsFragment.newInstance())
                .commit();

    }


}
