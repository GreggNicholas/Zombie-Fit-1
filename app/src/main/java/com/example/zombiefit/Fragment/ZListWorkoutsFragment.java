package com.example.zombiefit.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zombiefit.Controller.ZListFitnessAdapter;
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


public class ZListWorkoutsFragment extends Fragment {
    private static final String TAG = "List";
    private static final String ImageWorkoutKey = "GetAfterIT";
    //to be inserted into newInstance params upon retrofit call
    private static String workoutImage;
    private static String workoutImageView;
    private RecyclerView recyclerView;
    private ZListFitnessAdapter adapter;
    private List<ZWorkoutInnerObject> workoutInnerObjects;


    public static ZListWorkoutsFragment newInstance() {
        ZListWorkoutsFragment fragment = new ZListWorkoutsFragment();
        Bundle args = new Bundle();
//        args.putString(ImageWorkoutKey, workoutImage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            workoutImageView = getArguments().getString(ImageWorkoutKey);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zlistworkouts, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recyclerview);
        Retrofit retrofit = ZFitnessRetrofitSingleton.getInstance();
        final ZFitnessService service = retrofit.create(ZFitnessService.class);
        service.getListOfWorkouts().enqueue(new Callback<ZWorkoutViewList>() {
            @Override
            public void onResponse(Call<ZWorkoutViewList> call, Response<ZWorkoutViewList> response) {
                Log.d(TAG, "onResponse: " + response.body().getData().get(1).getImage());
                List<ZWorkoutInnerObject> workoutLists = response.body().getData();

                adapter = new ZListFitnessAdapter(workoutLists);
                recyclerView.setHasFixedSize(true);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ZWorkoutViewList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
