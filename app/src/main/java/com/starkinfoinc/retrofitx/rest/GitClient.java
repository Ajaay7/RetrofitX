package com.starkinfoinc.retrofitx.rest;

import com.starkinfoinc.retrofitx.model.GitUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitClient{

    //GET
    @GET("users/{user}")
    Call<GitUser> getdata(@Path("user") String user);
}

