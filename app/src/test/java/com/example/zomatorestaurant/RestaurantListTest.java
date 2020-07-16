package com.example.zomatorestaurant;

import com.example.zomatorestaurant.api.API;
import com.example.zomatorestaurant.pojo.ObjRestaurant;
import com.example.zomatorestaurant.pojo.ObjReview;
import com.example.zomatorestaurant.pojo.Restaurant;
import com.example.zomatorestaurant.pojo.Restaurants;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertNotNull;

public class RestaurantListTest {

    protected List<ObjRestaurant> list;
    protected List<ObjReview> listReviews;
    protected ObjRestaurant objRestaurant;
    protected Restaurant dataRestaurant;
    protected int restaurantId;

    @Before
    public void setUp(){
        this.list = null;
    }

    @Test
    public void fetchData(){
        // Using Retrofit to fetch some data from Zomato
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<Restaurants> call = api.fetchData(11052, "city");

        // Register the callback
        call.enqueue(new Callback<Restaurants>() {
            @Override
            public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
                if(response.body() != null){
                    list = response.body().getRestaurant();
                    assertNotNull(list);
                }
            }

            @Override
            public void onFailure(Call<Restaurants> call, Throwable t) {

            }
        });
    }
}
