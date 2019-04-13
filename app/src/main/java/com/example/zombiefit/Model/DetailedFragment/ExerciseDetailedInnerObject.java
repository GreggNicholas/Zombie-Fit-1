package com.example.zombiefit.Model.DetailedFragment;

final public class ExerciseDetailedInnerObject {

    private String title;
    private String image;
    private String description;
    private String youtubebutton;
    private String congrats;

    public ExerciseDetailedInnerObject(String title, String image, String description,
                                       String youtubebutton, String congrats) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.youtubebutton = youtubebutton;
        this.congrats = congrats;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getYoutubebutton() {
        return youtubebutton;
    }

    public String getCongrats() {
        return congrats;
    }
}
