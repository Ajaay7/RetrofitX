package com.starkinfoinc.retrofitx.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenarator {

    public static final String BASE_URL = "https://api.github.com/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        //If retrofit is null creating new retrofit
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;


    }
}