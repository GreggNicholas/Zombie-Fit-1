package com.example.zombiefit.Model.DetailedFragment;

import java.util.List;

public class ExerciseDetailedWrapper {
    public ExerciseDetailedWrapper(List<ExerciseDetailedInnerObject> exercisedetailed) {
        this.exercisedetailed = exercisedetailed;
    }

    List<ExerciseDetailedInnerObject> exercisedetailed;

    public List<ExerciseDetailedInnerObject> getExercisedetails() {
        return exercisedetailed;
    }
}
