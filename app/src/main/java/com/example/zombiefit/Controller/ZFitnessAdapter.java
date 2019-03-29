package com.example.zombiefit.Controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zombiefit.R;
import com.example.zombiefit.View.ZFitnessViewHolder;

public class ZFitnessAdapter extends RecyclerView.Adapter<ZFitnessViewHolder> {
    private CardView cardView;


    @NonNull
    @Override
    public ZFitnessViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_item_cardview, viewGroup, false);
        return new ZFitnessViewHolder(view, (CardView) view.findViewById(R.id.cardview_listworkoutsfragment));
    }

    @Override
    public void onBindViewHolder(@NonNull ZFitnessViewHolder zFitnessViewHolder, int position) {
        zFitnessViewHolder.onBind(cardView);
    }

    @Override

    public int getItemCount() {
        return 0;
    }


    public interface onCardViewClickListener {
        void onCardViewClick(int position);

    }
}
