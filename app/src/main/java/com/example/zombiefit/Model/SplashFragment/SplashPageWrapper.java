package com.example.zombiefit.Model.SplashFragment;

import java.util.List;

public class SplashPageWrapper {

    private List<SplashPageInnerObject> splashpage;

    public SplashPageWrapper(List<SplashPageInnerObject> splashpage) {
        this.splashpage = splashpage;
    }

    public List<SplashPageInnerObject> getSplashpage() {
        return splashpage;
    }
}
