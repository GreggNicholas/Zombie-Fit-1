package com.example.zombiefit.Service;

import com.example.zombiefit.Model.DetailedFragment.ExerciseDetailedWrapper;
import com.example.zombiefit.Model.ListFragment.WorkoutListWrapper;
import com.example.zombiefit.Model.SplashFragment.SplashPageWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    String END_POINT = "GreggNicholas/0d37492b5bed5cc00a5eb8fe604a2610/raw/af55004c08ad548d58edfc457cc99c9999522300/ZombieFitList.json";

    @GET(END_POINT)
    Call<WorkoutListWrapper> getListOfWorkouts();

    @GET(END_POINT)
    Call<SplashPageWrapper> getSplashPageDetails();

    @GET(END_POINT)
    Call<ExerciseDetailedWrapper> getExerciseDetails();
}
