package com.example.zombiefit.Model.ListFragment;

final public class WorkoutInnerObject {

    private String title;
    private String description;
    private String image;
    private String update;

    public WorkoutInnerObject(String title, String description, String image, String update) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.update = update;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getUpdate() {
        return update;
    }
}
