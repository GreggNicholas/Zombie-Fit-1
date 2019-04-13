package com.example.zombiefit.Controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zombiefit.Model.ListFragment.WorkoutInnerObject;
import com.example.zombiefit.OnFragmentInteractionListener;
import com.example.zombiefit.R;
import com.example.zombiefit.View.WorkoutListViewHolder;

import java.util.List;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListViewHolder> {
    private List<WorkoutInnerObject> workoutList;
    private OnFragmentInteractionListener listener;

    public WorkoutListAdapter(List<WorkoutInnerObject> workoutList, OnFragmentInteractionListener listener) {
        this.workoutList = workoutList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WorkoutListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_item_cardview, viewGroup, false);
        return new WorkoutListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutListViewHolder workoutListViewHolder, int position) {
        workoutListViewHolder.onBind(workoutList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }


}
