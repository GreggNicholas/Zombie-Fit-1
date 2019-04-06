package com.example.zombiefit.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.zombiefit.R;
import com.squareup.picasso.Picasso;


public class ZDetailedFragment extends Fragment {
    private static final String IMAGE_KEY = "imageParams";
    private ImageButton youtubeButton;
    private String exerciseImage;

    public static ZDetailedFragment getInstance(String imageParams) {
        ZDetailedFragment detailFrag = new ZDetailedFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_KEY, imageParams);
        detailFrag.setArguments(args);
        return detailFrag;
    }

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
}
