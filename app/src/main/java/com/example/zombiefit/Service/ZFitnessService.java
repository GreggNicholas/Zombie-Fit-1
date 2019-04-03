package com.example.zombiefit.Service;

import com.example.zombiefit.Model.ZWorkoutViewList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ZFitnessService {

    String END_POINT = "/GreggNicholas/0d37492b5bed5cc00a5eb8fe604a2610/raw/135e86247da3f6847388b0964bd91008731787aa/ZombieFitList.json";

    @GET(END_POINT)
    Call<ZWorkoutViewList> getListOfWorkouts();
}
