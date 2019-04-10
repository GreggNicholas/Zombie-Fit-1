package com.example.zombiefit.Model.ListFragment;

import java.util.List;

public class WorkoutListWrapper {

    private List<WorkoutInnerObject> workoutlist;

    public WorkoutListWrapper(List<WorkoutInnerObject> workoutlist) {
        this.workoutlist = workoutlist;
    }

    public List<WorkoutInnerObject> getWorkoutlist() {
        return workoutlist;
    }
}
