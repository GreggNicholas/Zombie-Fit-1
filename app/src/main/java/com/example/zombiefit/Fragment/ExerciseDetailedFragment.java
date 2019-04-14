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

import com.example.zombiefit.OnFragmentInteractionListener;
import com.example.zombiefit.R;
import com.squareup.picasso.Picasso;


final public class ExerciseDetailedFragment extends Fragment {
    private static final String TITLE_KEY = "params1";
    private static final String IMAGE_KEY = "params2";
    private static final String CONGRATS_KEY = "params3";
    private static final String YOUTUBE_KEY = "params4";
    private static final String DESCRIPTION_KEY = "params5";
    private static final String ONFINISH_KEY = "params5";


    private TextView timer;
    private TextView exerciseTitle;
    private TextView exerciseCongrats;
    private ImageButton exerciseYoutube;
    private TextView exerciseDescription;
    private ImageView exerciseImage;
    private ImageView onFinishView;


    private long timeLeftInMilliSec = 45000;
    private String title;
    private String image;
    private String description;
    private String youtube;
    private String congrats;
    private String onfinish;

    private OnFragmentInteractionListener listener;

    public static ExerciseDetailedFragment newInstance(String title, String image, String description,
                                                       String youTube, String congrats, String onfinish) {
        ExerciseDetailedFragment detailFrag = new ExerciseDetailedFragment();
        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        args.putString(IMAGE_KEY, image);
        args.putString(DESCRIPTION_KEY, description);
        args.putString(YOUTUBE_KEY, youTube);
        args.putString(CONGRATS_KEY, congrats);
        args.putString(ONFINISH_KEY, onfinish);
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

        if (getArguments() != null) {

            title = getArguments().getString(TITLE_KEY);
            image = getArguments().getString(IMAGE_KEY);
            description = getArguments().getString(DESCRIPTION_KEY);
            youtube = getArguments().getString(YOUTUBE_KEY);
            congrats = getArguments().getString(CONGRATS_KEY);
            onfinish = getArguments().getString(ONFINISH_KEY);

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
        exerciseDescription = view.findViewById(R.id.detailedfragment_description);
        onFinishView = view.findViewById(R.id.detailedfragment_onfinish);
        timer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        Picasso.get().load(youtube).fit().into(exerciseYoutube);
        Picasso.get().load(image).fit().into(exerciseImage);

        onLongClick();
        exerciseTitle.setText(title);



        setTimer();
        exerciseYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getResources().getString(R.string.youtube_spidermanpushup));
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });

    }

    private void onLongClick() {
        exerciseDescription.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                exerciseDescription.setText(description);
                exerciseDescription.setTextSize(14);
                exerciseImage.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_end_color));
                return false;
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
                timer.setText(congrats);
                timer.setGravity(0);
                Picasso.get().load(onfinish).fit().into(onFinishView);
                updateTimer();
            }
        }.start();
    }

    private void updateTimer() {
        int min = (int) timeLeftInMilliSec / 600000;
        int sec = (int) timeLeftInMilliSec % 600000 / 1000;

        String remainingTime;


    }


    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

}
