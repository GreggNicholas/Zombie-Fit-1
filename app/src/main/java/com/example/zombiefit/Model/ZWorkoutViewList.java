package com.example.zombiefit.Model;

import java.util.List;

public class ZWorkoutViewList {

    private List<ZWorkoutInnerObject> data;

    public ZWorkoutViewList(List<ZWorkoutInnerObject> data) {
        this.data = data;
    }

    public List<ZWorkoutInnerObject> getData() {
        return data;
    }
}
