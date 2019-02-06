package com.example.mapsappfromscratch;

import retrofit2.Call;
import retrofit2.http.GET;
//https://raw.githubusercontent.com/JDVila/storybook/master/museums.json

public interface MuseumsService {
    @GET("JDVila/storybook/master/museums.json")
    Call<MuseumsList>getMuseums();
}
