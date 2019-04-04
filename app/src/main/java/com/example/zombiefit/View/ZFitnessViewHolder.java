package com.example.zombiefit.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.zombiefit.Fragment.ZDetailedFragment;
import com.example.zombiefit.Model.ZWorkoutInnerObject;
import com.example.zombiefit.R;
import com.squareup.picasso.Picasso;

public class ZFitnessViewHolder extends RecyclerView.ViewHolder {
    private static final String WORKOUTIMAGE_KEY = "imageparams";
    private ImageView workoutImage;


    public ZFitnessViewHolder(@NonNull View itemView) {
        super(itemView);
        workoutImage = itemView.findViewById(R.id.cardview_workout_imageview);

    }

    public void onBind(final ZWorkoutInnerObject zWorkoutInnerObject) {
        Picasso.get().load(zWorkoutInnerObject.getImage()).into(workoutImage);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workoutImageParams = workoutImage.toString();
                Intent i = new Intent(v.getContext(), ZDetailedFragment.class);
                i.putExtra(WORKOUTIMAGE_KEY, workoutImageParams);
                v.getContext().startActivity(i);
            }
        });
    }

}
