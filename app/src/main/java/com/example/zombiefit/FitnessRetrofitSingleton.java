package com.example.zombiefit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FitnessRetrofitSingleton {
    private static final String BASE_URL = "https://pixabay.com";
    private static Retrofit instance;

    private FitnessRetrofitSingleton() {
    }

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
