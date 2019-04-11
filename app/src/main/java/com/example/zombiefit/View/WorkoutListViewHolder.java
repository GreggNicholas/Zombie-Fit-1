package com.example.zombiefit.View;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zombiefit.Controller.WorkoutListAdapter;
import com.example.zombiefit.Fragment.ExerciseDetailedFragment;
import com.example.zombiefit.Fragment.WorkoutListFragment;
import com.example.zombiefit.Model.ListFragment.WorkoutInnerObject;
import com.example.zombiefit.R;
import com.squareup.picasso.Picasso;

final public class WorkoutListViewHolder extends RecyclerView.ViewHolder {
    private static final String WORKOUTIMAGE_KEY = "imageparams";
    private ImageView workoutImage;
    private TextView workoutTitleView;
    private TextView workoutDescription;
    private TextView workoutUpdate;


    public WorkoutListViewHolder(@NonNull View itemView) {
        super(itemView);
        workoutTitleView = itemView.findViewById(R.id.cardview_workoutname_textview);
        workoutDescription = itemView.findViewById(R.id.cardview_workoutdescription_textview);
        workoutImage = itemView.findViewById(R.id.cardview_workout_imageview);
        workoutUpdate = itemView.findViewById(R.id.cardview_workoutupdate_textview);
    }

    @SuppressLint("ResourceAsColor")
    public void onBind(final WorkoutInnerObject workoutInnerObject, WorkoutListFragment.onFragmentInteractionListener workoutListener) {
        workoutUpdate.setText(workoutInnerObject.getUpdate());
        workoutTitleView.setText(workoutInnerObject.getTitle());
        Picasso.get().load(workoutInnerObject.getImage()).resize(1100, 450).into(workoutImage);
        onClick(workoutInnerObject, workoutListener);
        onLongClick(workoutInnerObject);
    }

    private void onLongClick(final WorkoutInnerObject workoutInnerObject) {
        workoutImage.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onLongClick(View v) {
                workoutDescription.setText(workoutInnerObject.getDescription());
                workoutTitleView.setVisibility(View.GONE);
                workoutDescription.getEllipsize();
                workoutDescription.setFocusable(true);
                workoutDescription.setBackgroundColor(R.color.cardview_shadow_end_color);
                return true;
            }
        });
    }

    private void onClick(final WorkoutInnerObject workoutInnerObject,  final WorkoutListFragment.onFragmentInteractionListener workoutListener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (workoutListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                       workoutListener.onItemViewClick(position);

                        switch (position) {
                            case 0:
                                workoutListener.onWorkoutListFragmentInteraction(workoutInnerObject.getTitle()
                                        ,workoutInnerObject.getDescription(), workoutInnerObject.getImage());
                                break;

                        }

                    }

//                      switch ()

                    Toast.makeText(itemView.getContext(), "mmmm BRAINS!!!", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}

