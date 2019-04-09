package com.example.zombiefit.Fragment;


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


public class ZDetailedFragment extends Fragment {
    private static final String IMAGE_KEY = "imageParams";
    private TextView timer;
    private ImageButton youtubeButton;
    private long timeLeftInMilliSec = 30000;
    private String exerciseImage;

    public static ZDetailedFragment getInstance() {
        ZDetailedFragment detailFrag = new ZDetailedFragment();
        Bundle args = new Bundle();
//        args.putString(IMAGE_KEY, imageParams);
        detailFrag.setArguments(args);
        return detailFrag;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            exerciseImage = getArguments().getString(IMAGE_KEY);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zdetailed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timer = view.findViewById(R.id.timer_detailedfragment);
        setTimer();
        ImageView exerciseImageView = view.findViewById(R.id.viewpager_imageview_exercise);
        Picasso.get().load(exerciseImage).into(exerciseImageView);
        youtubeButton = view.findViewById(R.id.youtubeButton);

        youtubeButton.setOnClickListener(new View.OnClickListener() {
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
}
