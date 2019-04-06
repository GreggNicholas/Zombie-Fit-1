package com.example.zombiefit.Service;

import com.example.zombiefit.Model.ZWorkoutViewList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ZFitnessService {

    String END_POINT = "/GreggNicholas/0d37492b5bed5cc00a5eb8fe604a2610/raw/ea7a6c6e059b39f15c72254603e7e23e13ea0940/ZombieFitList.json";

    @GET(END_POINT)
    Call<ZWorkoutViewList> getListOfWorkouts();
}
