package com.example.zombiefit.Fragment;

import android.os.Bundle;
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
    private static final String IMAGE_WORKOUT_KEY = "Getafterthatimage";
    private static final String TITLE_WORKOUT_KEY = "Getafterthattitle";
    private static final String DESCRIPTION_WORKOUT_KEY = "Getafterthatdescription";

    private static String workoutTitle;
    private static String workoutDescription;
    private static String workoutImage;

    private String workoutImageView;
    private String workoutTitleView;
    private String workoutDescriptView;

    private RecyclerView recyclerView;
    private ZListFitnessAdapter adapter;
    private List<ZWorkoutInnerObject> workoutInnerObjects;


    public static ZListWorkoutsFragment newInstance() {
        ZListWorkoutsFragment fragment = new ZListWorkoutsFragment();
        Bundle args = new Bundle();
        args.putString(TITLE_WORKOUT_KEY, workoutTitle);
        args.putString(DESCRIPTION_WORKOUT_KEY, workoutDescription);
        args.putString(IMAGE_WORKOUT_KEY, workoutImage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            workoutTitleView = getArguments().getString(TITLE_WORKOUT_KEY);
            workoutDescriptView = getArguments().getString(DESCRIPTION_WORKOUT_KEY);
            workoutImageView = getArguments().getString(IMAGE_WORKOUT_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zlistworkouts, container, false);
        recyclerView = view.findViewById(R.id.fragment_recyclerview);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Retrofit retrofit = ZFitnessRetrofitSingleton.getInstance();
        final ZFitnessService service = retrofit.create(ZFitnessService.class);
        service.getListOfWorkouts().enqueue(new Callback<ZWorkoutViewList>() {
            @Override
            public void onResponse(Call<ZWorkoutViewList> call, Response<ZWorkoutViewList> response) {
                Log.d(TAG, "onResponse: " + response.body().getData().get(1).getImage());
                final List<ZWorkoutInnerObject> workoutLists = response.body().getData();

                adapter = new ZListFitnessAdapter(workoutLists);
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new ZListFitnessAdapter.onItemClickListener() {
                    @Override
                    public void onItemViewClick(int position) {
                        getFragmentManager().beginTransaction()
                                .replace(R.id.mainactivity_container, ZDetailedFragment.getInstance(workoutImageView))
                                .addToBackStack("itemview")
                                .commit();
                    }
                });
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ZWorkoutViewList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        return view;
    }


}
