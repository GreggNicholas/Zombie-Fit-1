package com.example.zombiefit.Model.SplashFragment;

public class SplashPageInnerObject {

    private String zombiefit;
    private String description;
    private String gif;

    public SplashPageInnerObject(String zombiefit, String description, String gif) {
        this.zombiefit = zombiefit;
        this.description = description;
        this.gif = gif;
    }

    public String getZombiefitSplashTitle() {
        return zombiefit;
    }

    public String getSplashDescription() {
        return description;
    }

    public String getSplashGif() {
        return gif;
    }
}
