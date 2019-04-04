package com.example.zombiefit.View;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.zombiefit.Controller.ZListFitnessAdapter;
import com.example.zombiefit.Fragment.ZListWorkoutsFragment;
import com.example.zombiefit.Model.ZWorkoutInnerObject;
import com.example.zombiefit.Model.ZWorkoutViewList;
import com.example.zombiefit.R;
import com.example.zombiefit.Service.ZFitnessRetrofitSingleton;
import com.example.zombiefit.Service.ZFitnessService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainactivity_container, ZListWorkoutsFragment.newInstance())
                .commit();

//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(ZDetailedFragment.class, detailedfrag)
//                .commit();

    }


}
