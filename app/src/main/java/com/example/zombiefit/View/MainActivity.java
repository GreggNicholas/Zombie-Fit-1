package com.example.zombiefit.View;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.zombiefit.Fragment.ZListWorkoutsFragment;
import com.example.zombiefit.R;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainactivity_container, ZListWorkoutsFragment.newInstance())
                .commit();

    }


}
