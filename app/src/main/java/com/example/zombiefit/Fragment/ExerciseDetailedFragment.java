package com.example.zombiefit.Fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zombiefit.R;
import com.squareup.picasso.Picasso;


final public class ExerciseDetailedFragment extends Fragment {
    private static final String TITLE_KEY = "params1";
    private static final String IMAGE_KEY = "params2";
    private static final String CONGRATS_KEY = "params3";
    private static final String YOUTUBE_KEY = "params4";
    private static final String DESCRIPTION_KEY = "params5";

    private TextView timer;
    private TextView exerciseTitle;
    private TextView exerciseCongrats;
    private ImageButton exerciseYoutube;
    private TextView exerciseDescription;
    private ImageView exerciseImage;


    private long timeLeftInMilliSec = 30000;
    private String title;
    private String image;
    private String description;
    private String youtube;
    private String congrats;

    private OnFragmentInteractionListener listener;

    public static ExerciseDetailedFragment newInstance(String title, String image, String description,
                                                       String youTube, String congrats) {
        ExerciseDetailedFragment detailFrag = new ExerciseDetailedFragment();
        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        args.putString(IMAGE_KEY, image);
        args.putString(DESCRIPTION_KEY, description);
        args.putString(YOUTUBE_KEY, youTube);
        args.putString(CONGRATS_KEY, congrats);
        detailFrag.setArguments(args);
        return detailFrag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(listener != null){
            listener.onDetailedFragmentInteraction(title,image,description,youtube,congrats);
        }
        if (getArguments() != null) {

            title = getArguments().getString(TITLE_KEY);
            image = getArguments().getString(IMAGE_KEY);
            description = getArguments().getString(DESCRIPTION_KEY);
            youtube = getArguments().getString(YOUTUBE_KEY);
            congrats = getArguments().getString(CONGRATS_KEY);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detailed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timer = view.findViewById(R.id.detailedfragment_timer);
        exerciseTitle = view.findViewById(R.id.detailedfragment_title);
        exerciseImage = view.findViewById(R.id.detailedfragment_imageview_exercise);
        exerciseYoutube = view.findViewById(R.id.detailedfragment_youtube);
        exerciseDescription = view.findViewById(R.id.detailedfragment_desciption);
//        exerciseCongrats = view.findViewById(R.id.deta);


        Picasso.get().load(youtube).into(exerciseYoutube);
        Picasso.get().load(image).into(exerciseImage);
        onLongClick(view);
        exerciseTitle.setText(title);
        listener.onDetailedFragmentInteraction(title,image,description,youtube,congrats);
        setTimer();
        exerciseYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=U4s4mEQ5VqU");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    private void onLongClick(final View view) {
        exerciseDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exerciseDescription.setText(description);
                exerciseDescription.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_end_color));
            }
        });
    }

    private void setTimer() {
        new CountDownTimer(timeLeftInMilliSec, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliSec = 1;
                timer.setText(R.string.timer_default);
                long timeLeft = millisUntilFinished / 1000;

                timeLeft++;
                timer.setText(String.valueOf(timeLeft));
            }

            @Override
            public void onFinish() {
                timer.setTextSize(25);
                timer.setText(getString(R.string.timer_finishtext));

            }
        }.start();
    }

    private void updateTimer() {
        int min = (int) timeLeftInMilliSec / 600000;
        int sec = (int) timeLeftInMilliSec % 600000 / 1000;

        String remainingTime;


    }

    public interface OnFragmentInteractionListener {
        void onDetailedFragmentInteraction(String title, String image, String description,
                                           String youTube, String congrats);
    }
}
