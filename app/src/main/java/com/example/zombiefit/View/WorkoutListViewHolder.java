package com.example.zombiefit.View;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zombiefit.Model.DetailedFragment.ExerciseDetailedWrapper;
import com.example.zombiefit.Model.ListFragment.WorkoutInnerObject;
import com.example.zombiefit.OnFragmentInteractionListener;
import com.example.zombiefit.R;
import com.example.zombiefit.Service.RetrofitSingleton;
import com.example.zombiefit.Service.Service;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WorkoutListViewHolder extends RecyclerView.ViewHolder {

    private ImageView workoutImage;
    private TextView workoutTitleView, workoutDescription, workoutUpdate;


    public WorkoutListViewHolder(@NonNull View itemView) {
        super(itemView);
        workoutTitleView = itemView.findViewById(R.id.cardview_workoutname_textview);
        workoutDescription = itemView.findViewById(R.id.cardview_workoutdescription_textview);
        workoutImage = itemView.findViewById(R.id.cardview_workout_imageview);
        workoutUpdate = itemView.findViewById(R.id.cardview_workoutdemo_textview);
    }

    @SuppressLint("ResourceAsColor")
    public void onBind(final WorkoutInnerObject workoutInnerObject, final OnFragmentInteractionListener listener) {

        workoutTitleView.setText(workoutInnerObject.getTitle());
        workoutTitleView.setTypeface(Typeface.DEFAULT_BOLD);

        Picasso.get().load(workoutInnerObject.getImage()).resize(1100, 450).into(workoutImage);
        onLongClick(workoutInnerObject);
        onClick(listener);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                    switch (position) {
                        case 0:
                            listener.onDetailedFragmentInteraction();
                            break;
                        case 1:
                            leaveThemWantingMore1();
                            break;
                        case 2:
                            leaveThemWantingMore1();
                            break;
                        case 3:
                            leaveThemWantingMore1();
                            break;
                        case 4:
                            leaveThemWantingMore2();
                            break;
                        case 5:
                            leaveThemWantingMore2();
                            break;
                    }
            }

            private void leaveThemWantingMore1() {
                workoutUpdate.setText(workoutInnerObject.getUpdate());
                workoutUpdate.setPadding(0, 50, 0, 0);
                workoutUpdate.setTextSize(42);
                workoutDescription.setVisibility(View.GONE);
                eraseTextView();
            }

            private void leaveThemWantingMore2() {
                workoutUpdate.setText(workoutInnerObject.getUpdate());
                workoutUpdate.setPadding(0, 50, 0, 0);
                workoutUpdate.setTextSize(42);
                workoutDescription.setVisibility(View.GONE);
                eraseTextView();
            }
        });


    }

    private void onClick(OnFragmentInteractionListener listener) {
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
                workoutDescription.setLineSpacing(1, 1);
                workoutDescription.getEllipsize();
                eraseTextView();
                return true;
            }
        });
    }
}
