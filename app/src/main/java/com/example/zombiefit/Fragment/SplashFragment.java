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
import android.widget.Toast;

import com.example.zombiefit.R;

import static java.lang.Thread.sleep;


public class SplashFragment extends Fragment {
    private static final String SPLASH_IMAGE_KEY = "param1";
    private static final String SPLASH_TITLE_KEY = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener listener;


    public static SplashFragment getInstance(String image, String title) {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();
        args.putString(SPLASH_IMAGE_KEY, image);
        args.putString(SPLASH_TITLE_KEY, title);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            new Handler().postDelayed(runnable, getContext(), 40000);
        }
        Toast.makeText(getContext(), "skp", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                sleep(1300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listener.onFragmentInteraction();
        }
    };


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
