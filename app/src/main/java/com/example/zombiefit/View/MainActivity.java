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

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private ZListFitnessAdapter adapter;
    private List<ZWorkoutInnerObject> workoutInnerObjects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_recyclerview);
        Retrofit retrofit = ZFitnessRetrofitSingleton.getInstance();
        final ZFitnessService service = retrofit.create(ZFitnessService.class);
        service.getListOfWorkouts().enqueue(new Callback<ZWorkoutViewList>() {
            @Override
            public void onResponse(Call<ZWorkoutViewList> call, Response<ZWorkoutViewList> response) {
                Log.d(TAG, "onResponse: " + response.body().getData().get(1).getImage());
                 List<ZWorkoutInnerObject> workoutLists = response.body().getData();

                adapter = new ZListFitnessAdapter(workoutLists);
                recyclerView.setAdapter(adapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<ZWorkoutViewList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });


//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.mainactivity_container, ZListWorkoutsFragment.newInstance())
//                .commit();

//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(ZDetailedFragment.class, detailedfrag)
//                .commit();

    }


}
