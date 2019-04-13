package com.example.zombiefit.Fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zombiefit.R;
import com.squareup.picasso.Picasso;

import static java.lang.Thread.sleep;

final public class SplashFragment extends Fragment {
    private static final String SPLASH_IMAGE_KEY = "param1";
    private static final String SPLASH_TITLE_KEY = "param2";
    private static final String SPLASH_DESCRIPTION_KEY = "param3";

    private TextView splashTitleView;
    private ImageView splashImageView;
    private TextView splashDescriptionView;

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private OnFragmentInteractionListener listener;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listener.onSplashFragmentInteraction(mParam1, mParam2, mParam3);
        }
    };

    public static SplashFragment newInstance(String image, String title, String description) {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();
        args.putString(SPLASH_IMAGE_KEY, image);
        args.putString(SPLASH_TITLE_KEY, title);
        args.getString(SPLASH_DESCRIPTION_KEY, description);
        fragment.setArguments(args);
        return fragment;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(SPLASH_IMAGE_KEY);
            mParam2 = getArguments().getString(SPLASH_TITLE_KEY);
            mParam3 = getArguments().getString(SPLASH_DESCRIPTION_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        splashTitleView = view.findViewById(R.id.splash_textview);
        splashImageView = view.findViewById(R.id.splash_imageview);
        splashDescriptionView = view.findViewById(R.id.splash_description);
//        splashTitleView.setText(mParam1);
        Picasso.get().load(mParam2).into(splashImageView);
        splashDescriptionView.setText(mParam3);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            new Handler().postDelayed(runnable, getContext(), 400000);

        }
        Toast.makeText(getContext(), "skp", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public interface OnFragmentInteractionListener {
        void onSplashFragmentInteraction(String image, String title, String description);
    }
}
