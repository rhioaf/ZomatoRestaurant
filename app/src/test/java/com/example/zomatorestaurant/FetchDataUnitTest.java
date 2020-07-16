package com.example.zomatorestaurant;

import com.example.zomatorestaurant.api.API;
import com.example.zomatorestaurant.pojo.ObjRestaurant;
import com.example.zomatorestaurant.pojo.ObjReview;
import com.example.zomatorestaurant.pojo.Restaurant;
import com.example.zomatorestaurant.pojo.Restaurants;
import com.example.zomatorestaurant.pojo.Reviews;
import com.example.zomatorestaurant.RestaurantViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FetchDataUnitTest {

    protected List<ObjRestaurant> list;
    protected List<ObjReview> listReviews;
    protected ObjRestaurant objRestaurant;
    protected Restaurant dataRestaurant;
    protected int restaurantId;
    protected RestaurantViewModel restaurantViewModel;

    @Before
    public void setUp(){
        this.list = null;
        restaurantId = -1;
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
                    objRestaurant = list.get(0);
                    restaurantId = objRestaurant.getRestaurant().getId();
                    assertNotNull(list);
                }
            }
            @Override
            public void onFailure(Call<Restaurants> call, Throwable t) {

            }
        });
    }

    @Test
    public void fetchRestaurantDetail(){
        if(restaurantId != -1){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API api = retrofit.create(API.class);
            Call<Restaurant> call = api.fetchDataDetail(restaurantId);

            call.enqueue(new Callback<Restaurant>() {
                @Override
                public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                    if(response.body() != null){
                        dataRestaurant = response.body();
                        assertNotNull(dataRestaurant);
                    }
                }

                @Override
                public void onFailure(Call<Restaurant> call, Throwable t) {

                }
            });
        }
    }

    @Test
    public void fetchReviews(){
        if(restaurantId != -1){
            Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Config.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
            API api = retrofit.create(API.class);
            Call<Reviews> call = api.fetchReviews(restaurantId);

            call.enqueue(new Callback<Reviews>() {
                @Override
                public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                    if(response.body() != null){
                        listReviews = response.body().getList();
                        assertNotNull(listReviews);
                    }
                }

                @Override
                public void onFailure(Call<Reviews> call, Throwable t) {

                }
            });
        }
    }
}
