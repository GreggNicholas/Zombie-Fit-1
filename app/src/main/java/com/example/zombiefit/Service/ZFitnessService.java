package com.example.zombiefit.Service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ZFitnessService {

    String PATH = "3/gallery/";
    String SELECTOR = "{{section}}";
    String clientID = "211412c2722c28f";

    @GET(PATH + SELECTOR)
    Call getListOfWorkouts(@Header(clientID) @Path("hofmqKE") String section);


}
