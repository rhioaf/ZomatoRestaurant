package com.example.zomatorestaurant;

import com.example.zomatorestaurant.api.API;
import com.example.zomatorestaurant.pojo.ObjRestaurant;
import com.example.zomatorestaurant.pojo.ObjReview;
import com.example.zomatorestaurant.pojo.Restaurant;
import com.example.zomatorestaurant.pojo.Restaurants;
import com.example.zomatorestaurant.pojo.Reviews;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

public class RestaurantDetailTest {

    protected List<ObjRestaurant> list;
    protected List<ObjReview> listReviews;
    protected ObjRestaurant objRestaurant;
    protected Restaurant dataRestaurant;
    protected int restaurantId;

    @Before
    public void setUp(){
        this.list = null;
        restaurantId = 16774318;
    }

    @Test
    public void fetchRestaurantDetail(){
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

    @Test
    public void fetchReviews(){
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
