package com.example.zombiefit.View;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zombiefit.Controller.WorkoutListAdapter;
import com.example.zombiefit.Fragment.WorkoutListFragment;
import com.example.zombiefit.Model.ListFragment.WorkoutInnerObject;
import com.example.zombiefit.R;
import com.squareup.picasso.Picasso;

public class WorkoutListViewHolder extends RecyclerView.ViewHolder {
    private static final String WORKOUTIMAGE_KEY = "imageparams";
    private ImageView workoutImage;
    private TextView workoutTitleView;
    private TextView workoutDescription;
    private TextView workoutUpdate;

    private String exerciseDescription;
    private String exerciseYoutube;
    private String exerciseCongrats;
    private String exerciseImage;
    private String exerciseTitle;
    private WorkoutListAdapter.onItemClickListener onItemClickListener;
    private final String demo = "Full Version Only";

    public WorkoutListViewHolder(@NonNull View itemView, WorkoutListAdapter.onItemClickListener listener) {
        super(itemView);
        workoutTitleView = itemView.findViewById(R.id.cardview_workoutname_textview);
        workoutDescription = itemView.findViewById(R.id.cardview_workoutdescription_textview);
        workoutImage = itemView.findViewById(R.id.cardview_workout_imageview);
        workoutUpdate = itemView.findViewById(R.id.cardview_workoutdemo_textview);
        this.onItemClickListener = listener;
    }

    @SuppressLint("ResourceAsColor")
    public void onBind(final WorkoutInnerObject workoutInnerObject, WorkoutListFragment.onFragmentInteractionListener listener) {

        workoutTitleView.setText(workoutInnerObject.getTitle());
        Picasso.get().load(workoutInnerObject.getImage()).resize(1100, 450).into(workoutImage);
        onClick(workoutInnerObject);
        onLongClick(workoutInnerObject);
    }


    @SuppressLint("ResourceAsColor")
    private void eraseTextView() {
        workoutTitleView.setVisibility(View.GONE);
        workoutDescription.setBackgroundColor(R.color.cardview_shadow_end_color);
    }

    private void onLongClick(final WorkoutInnerObject workoutInnerObject) {
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onLongClick(View v) {
                workoutDescription.setText(workoutInnerObject.getDescription());
                workoutDescription.getEllipsize();
                eraseTextView();
                return true;
            }
        });
    }

    private void onClick(final WorkoutInnerObject workoutInnerObject) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View itemview) {
                {
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemViewClick(position);

                            switch (position) {
                                case 0:
                                    Toast.makeText(itemview.getContext(), "meow", Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    leaveThemWantingMore1();
                                    workoutDescription.setVisibility(View.GONE);
                                    Toast.makeText(itemview.getContext(), demo, Toast.LENGTH_LONG).show();
                                    break;
                                case 2:
                                    workoutDescription.setVisibility(View.GONE);
                                    leaveThemWantingMore1();
                                    Toast.makeText(itemview.getContext(), demo, Toast.LENGTH_LONG).show();
                                    break;
                                case 3:
                                    workoutDescription.setVisibility(View.GONE);
                                    leaveThemWantingMore1();
                                    Toast.makeText(itemview.getContext(), demo, Toast.LENGTH_LONG).show();
                                    break;
                                case 4:
                                    workoutDescription.setVisibility(View.GONE);
                                    leaveThemWantingMore2();
                                    Toast.makeText(itemview.getContext(), demo, Toast.LENGTH_LONG).show();
                                    break;
                                case 5:
                                    workoutDescription.setVisibility(View.GONE);
                                    leaveThemWantingMore2();
                                    Toast.makeText(itemview.getContext(), demo, Toast.LENGTH_SHORT).show();
                                    break;
                            }
//                            if (fragment != null) {
//                                FragmentManager fragmentManager = fragment.getFragmentManager();
//                                fragmentManager.beginTransaction()
//                                        .replace(R.id.mainactivity_container, ExerciseDetailedFragment.newInstance(exerciseTitle
//                                                , exerciseImage, exerciseDescription,
//                                                exerciseYoutube, exerciseCongrats)).commit();
//                            }
                        }

//                      switch ()

//                        Toast.makeText(itemView.getContext(), "mmmm BRAINS!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            private void leaveThemWantingMore1() {
                workoutUpdate.setText(workoutInnerObject.getUpdate());
                workoutUpdate.setTextSize(34);
                eraseTextView();
            }

            private void leaveThemWantingMore2() {
                workoutUpdate.setText(workoutInnerObject.getUpdate());
                eraseTextView();
            }

        });

    }


}

