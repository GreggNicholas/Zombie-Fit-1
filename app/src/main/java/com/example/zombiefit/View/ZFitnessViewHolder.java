package com.example.zombiefit.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zombiefit.R;

public class ZFitnessViewHolder extends RecyclerView.ViewHolder {
    private CardView cardView;

    public ZFitnessViewHolder(@NonNull View itemView, CardView cardView) {
        super(itemView);
        this.cardView = cardView;
    }

//    public void onBind(View Cardview) {
//        cardView = itemView.findViewById(R.id.cardview_listworkoutsfragment);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(cardView.getContext(), );
//
//            }
//        });
//
//    }
}
