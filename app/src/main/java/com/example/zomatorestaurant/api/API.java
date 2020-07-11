package com.example.zomatorestaurant.api;

import com.example.zomatorestaurant.Config;
import com.example.zomatorestaurant.pojo.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface API {

    @Headers({
            "Accept: application/json",
            "user-key : " + Config.API_KEY
    })
    @GET("restaurant")
    Call<List<Restaurant>> fetchData();

}
