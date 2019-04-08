package com.example.zombiefit.Controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zombiefit.Model.ZWorkoutInnerObject;
import com.example.zombiefit.R;
import com.example.zombiefit.View.ZFitnessViewHolder;

import java.util.List;

public class ZListFitnessAdapter extends RecyclerView.Adapter<ZFitnessViewHolder> {
    private List<ZWorkoutInnerObject> workoutList;
    private onItemClickListener listener;

    public ZListFitnessAdapter(List<ZWorkoutInnerObject> workoutList) {
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public ZFitnessViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_item_cardview, viewGroup, false);
        return new ZFitnessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZFitnessViewHolder zFitnessViewHolder, int position) {
        zFitnessViewHolder.onBind(workoutList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }


    public interface onItemClickListener {
        void onItemViewClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener setOnlistener) {
        listener = setOnlistener;
    }
}
