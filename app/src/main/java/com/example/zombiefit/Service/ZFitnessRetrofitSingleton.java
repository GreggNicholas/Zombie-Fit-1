package com.example.zombiefit.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZFitnessRetrofitSingleton {
    private static final String BASE_URL = "https://api.imgur.com/";
    private static Retrofit instance;

    private ZFitnessRetrofitSingleton() {
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
