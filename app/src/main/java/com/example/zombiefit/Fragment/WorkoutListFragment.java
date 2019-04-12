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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zombiefit.Controller.WorkoutListAdapter;
import com.example.zombiefit.Model.ListFragment.WorkoutInnerObject;
import com.example.zombiefit.Model.ListFragment.WorkoutListWrapper;
import com.example.zombiefit.R;
import com.example.zombiefit.Service.RetrofitSingleton;
import com.example.zombiefit.Service.Service;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


final public class WorkoutListFragment extends Fragment  {
    private static final String TAG = "List";
    private static final String IMAGE_WORKOUT_KEY = "Getafterthatimage";
    private static final String TITLE_WORKOUT_KEY = "Getafterthattitle";
    private static final String DESCRIPTION_WORKOUT_KEY = "Getafterthatdescription";

    private static String workoutTitle;
    private static String workoutDescription;
    private static String workoutImage;

    private ImageView workoutImageView;
    private TextView workoutTitleView;
    private TextView workoutDescriptView;

    private RecyclerView recyclerView;
    private WorkoutListAdapter adapter;
    private List<WorkoutInnerObject> workoutInnerObjects;
    private WorkoutListFragment.onFragmentInteractionListener workFragmentListener;
    private WorkoutListAdapter.onItemClickListener adapterListener;


    public static WorkoutListFragment newInstance() {
        return new WorkoutListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listworkouts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.fragment_recyclerview);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        Retrofit retrofit = RetrofitSingleton.getInstance();
        final Service service = retrofit.create(Service.class);
        service.getListOfWorkouts().enqueue(new Callback<WorkoutListWrapper>() {
            @Override
            public void onResponse(Call<WorkoutListWrapper> call, Response<WorkoutListWrapper> response) {

                final List<WorkoutInnerObject> workoutLists = new LinkedList<>();
                if (response.body() != null) {
                    workoutLists.addAll(response.body().getWorkoutlist());
                }

                adapter = new WorkoutListAdapter(workoutLists, adapterListener);
                adapter.notifyDataSetChanged();

                adapter.setOnItemClickListener(new WorkoutListAdapter.onItemClickListener() {
                    @Override
                    public void onItemViewClick(int position) {


                        switch (position) {


                        }

                    }

                });
                adapter.notifyDataSetChanged();
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<WorkoutListWrapper> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), getResources().getString(R.string.retrofit_onfailure), Toast.LENGTH_SHORT).show();
            }
        });
    }



    public interface onFragmentInteractionListener {
        void onWorkoutListFragmentInteraction(String title, String description, String image);

        void onItemViewClick(int position);
    }
}
