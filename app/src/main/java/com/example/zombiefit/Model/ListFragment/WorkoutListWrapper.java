package com.example.zombiefit.Model.ListFragment;

import java.util.List;

public class WorkoutListWrapper {

    private List<WorkoutInnerObject> data;

    public WorkoutListWrapper(List<WorkoutInnerObject> data) {
        this.data = data;
    }

    public List<WorkoutInnerObject> getData() {
        return data;
    }
}
