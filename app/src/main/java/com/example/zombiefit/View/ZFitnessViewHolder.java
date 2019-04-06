package com.example.zombiefit.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zombiefit.Controller.ZListFitnessAdapter;
import com.example.zombiefit.Model.ZWorkoutInnerObject;
import com.example.zombiefit.R;
import com.squareup.picasso.Picasso;

public class ZFitnessViewHolder extends RecyclerView.ViewHolder {
    private static final String WORKOUTIMAGE_KEY = "imageparams";
    private ImageView workoutImage;
    private TextView workoutTitleView;


    public ZFitnessViewHolder(@NonNull View itemView) {
        super(itemView);
        workoutImage = itemView.findViewById(R.id.cardview_workout_imageview);
        workoutTitleView = itemView.findViewById(R.id.cardview_workoutname_textview);
    }

    public void onBind(final ZWorkoutInnerObject zWorkoutInnerObject, final ZListFitnessAdapter.onItemClickListener listener) {
        workoutTitleView.setText(zWorkoutInnerObject.getTitle());
        Picasso.get().load(zWorkoutInnerObject.getImage()).resize(1100, 450).into(workoutImage);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemViewClick(position);
                        Toast.makeText(itemView.getContext(), "mmmm BRAINS!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}

