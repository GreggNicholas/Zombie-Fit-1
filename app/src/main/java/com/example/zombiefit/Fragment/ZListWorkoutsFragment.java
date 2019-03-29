package com.example.zombiefit.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zombiefit.R;
import com.squareup.picasso.Picasso;


public class ZListWorkoutsFragment extends Fragment {
    private static final String ImageWorkoutKey = "GetAfterIT";
    //to be inserted into newInstance params upon retrofit call
    private static String workoutImage;
    private static String workoutImageView;

    public static ZListWorkoutsFragment newInstance() {
        ZListWorkoutsFragment fragment = new ZListWorkoutsFragment();
        Bundle args = new Bundle();
        args.putString(ImageWorkoutKey, workoutImage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            workoutImageView = getArguments().getString(ImageWorkoutKey);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_cardview, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView zFitWorkoutView = view.findViewById(R.id.cardview_workout_imageview);
        Picasso.get().load(workoutImageView).into(zFitWorkoutView);
    }
}
