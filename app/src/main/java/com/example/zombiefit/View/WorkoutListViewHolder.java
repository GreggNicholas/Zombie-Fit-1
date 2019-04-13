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
    private static final String WORKOUTIMAGE_KEY = "imageparams";
    private static final String TAG = "zombie";
    private final String demo = "Full Version Only";
    private ImageView workoutImage;
    private TextView workoutTitleView;
    private TextView workoutDescription;
    private TextView workoutUpdate;

    private String exerciseCongrats;
    private String exerciseImage;
    private String exerciseYoutube;
    private String exerciseDescription;
    private String exerciseTitle;
    private String brains = "MMMM Brains";


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
                {
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) switch (position) {
                            case 0:
                                Retrofit retrofit = RetrofitSingleton.getInstance();
                                Service service = retrofit.create(Service.class);
                                service.getExerciseDetails().enqueue(new Callback<ExerciseDetailedWrapper>() {
                                    @Override
                                    public void onResponse(Call<ExerciseDetailedWrapper> call, Response<ExerciseDetailedWrapper> response) {
                                        Log.d(TAG, "onResponse: " + response.isSuccessful());
                                        Log.d(TAG, "onResponse: " + response.body().getExercisedetails().get(0).getTitle());
                                        Log.d(TAG, "onResponse: " + response.body().getExercisedetails().get(0).getDescription());
                                        Log.d(TAG, "onResponse: " + response.body().getExercisedetails().get(0).getYoutubebutton());
                                        Log.d(TAG, "onResponse: " + response.body().getExercisedetails().get(0).getImage());
                                        Log.d(TAG, "onResponse: " + response.body().getExercisedetails().get(0).getCongrats());
                                        String exerciseCongrats = response.body().getExercisedetails().get(0).getCongrats();
                                        String exerciseImage = response.body().getExercisedetails().get(0).getImage();
                                        String exerciseYoutube = response.body().getExercisedetails().get(0).getYoutubebutton();
                                        String exerciseDescription = response.body().getExercisedetails().get(0).getDescription();
                                        String exerciseTitle = response.body().getExercisedetails().get(0).getTitle();

                                        if (response.isSuccessful() && response.body() != null) {
                                            listener.onDetailedFragmentInteraction(exerciseTitle, exerciseImage, exerciseDescription, exerciseYoutube,
                                                    exerciseCongrats);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ExerciseDetailedWrapper> call, Throwable t) {
                                        Log.e(TAG, "onFailure: " + t.getMessage());
                                    }
                                });

                                Toast.makeText(v.getContext(), brains, Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                leaveThemWantingMore1();
                                workoutDescription.setVisibility(View.GONE);
                                Toast.makeText(v.getContext(), demo, Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                workoutDescription.setVisibility(View.GONE);
                                leaveThemWantingMore1();
                                Toast.makeText(v.getContext(), demo, Toast.LENGTH_LONG).show();
                                break;
                            case 3:
                                workoutDescription.setVisibility(View.GONE);
                                leaveThemWantingMore1();
                                Toast.makeText(v.getContext(), demo, Toast.LENGTH_LONG).show();
                                break;
                            case 4:
                                workoutDescription.setVisibility(View.GONE);
                                leaveThemWantingMore2();
                                Toast.makeText(v.getContext(), demo, Toast.LENGTH_LONG).show();
                                break;
                            case 5:
                                workoutDescription.setVisibility(View.GONE);
                                leaveThemWantingMore2();
                                Toast.makeText(v.getContext(), demo, Toast.LENGTH_SHORT).show();
                                break;
                        }
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
                workoutDescription.setLineSpacing(1,1);
                workoutDescription.getEllipsize();
                eraseTextView();
                return true;
            }
        });
    }
}
