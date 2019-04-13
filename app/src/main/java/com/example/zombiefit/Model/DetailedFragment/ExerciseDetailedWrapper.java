package com.example.zombiefit.Model.DetailedFragment;

import java.util.List;

final public class ExerciseDetailedWrapper {

    private List<ExerciseDetailedInnerObject> exercisedetailed;

    public ExerciseDetailedWrapper(List<ExerciseDetailedInnerObject> exercisedetailed) {
        this.exercisedetailed = exercisedetailed;
    }

    public List<ExerciseDetailedInnerObject> getExercisedetails() {
        return exercisedetailed;
    }

}
