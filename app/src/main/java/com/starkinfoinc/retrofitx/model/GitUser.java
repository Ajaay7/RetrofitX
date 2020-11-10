package com.starkinfoinc.retrofitx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//POJO CLASS
public class GitUser {
    //Declare Variables
    private  String  name ;
    private  String  company ;
    private  String  blog ;
    private  String  bio ;
    @SerializedName("avatar_url")
    @Expose private
    String  avatarurl ;

    //Define Getters

    public String getName()
    {
        return name;
    }
    public String getCompany()
    {
        return company;
    }

    public String getBlog()
    {
        return blog;
    }

    public String getBio()
    {
        return bio;
    }

    public String getAvatarurl()
    {
        return avatarurl;
    }

}
