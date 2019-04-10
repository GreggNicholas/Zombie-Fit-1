package com.example.zombiefit.Service;

import com.example.zombiefit.Model.ListFragment.WorkoutListWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ZFitnessService {

    String END_POINT = "/GreggNicholas/0d37492b5bed5cc00a5eb8fe604a2610/raw/d8e6fb09bd8865272674a643ff6d53d1779b6ff5/ZombieFitList.json";

    @GET(END_POINT)
    Call<WorkoutListWrapper> getListOfWorkouts();
}
