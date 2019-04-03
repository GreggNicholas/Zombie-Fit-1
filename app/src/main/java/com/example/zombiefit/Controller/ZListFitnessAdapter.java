package com.example.zombiefit.Controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zombiefit.R;
import com.example.zombiefit.View.ZFitnessViewHolder;

public class ZListFitnessAdapter extends RecyclerView.Adapter<ZFitnessViewHolder> {



    @NonNull
    @Override
    public ZFitnessViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_item_cardview, viewGroup, false);
        return new ZFitnessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZFitnessViewHolder zFitnessViewHolder, int position) {
        zFitnessViewHolder.onBind();
    }

    @Override

    public int getItemCount() {
        return 0;
    }


    public interface onCardViewClickListener {
        void onCardViewClick(int position);

    }
}
