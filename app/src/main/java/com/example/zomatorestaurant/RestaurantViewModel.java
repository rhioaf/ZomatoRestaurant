// Author: 181511049 Ivan Eka Putra dan 181511064 Rhio Adjie Fabian

package com.example.zomatorestaurant;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zomatorestaurant.api.API;
import com.example.zomatorestaurant.pojo.ObjRestaurant;
import com.example.zomatorestaurant.pojo.ObjReview;
import com.example.zomatorestaurant.pojo.Restaurant;
import com.example.zomatorestaurant.pojo.Restaurants;
import com.example.zomatorestaurant.pojo.Reviews;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RestaurantViewModel extends ViewModel {

    // Instance of class ViewModel
    private MutableLiveData<List<ObjRestaurant>> restaurantList;
    private MutableLiveData<Restaurant> restaurant;
    private MutableLiveData<List<ObjReview>> reviewsList;

    // Fetch data
    public LiveData<List<ObjRestaurant>> getRestaurant(){
        if(this.restaurantList == null){
            this.restaurantList = new MutableLiveData<>();
            fetchDataRestaurant("city");
        }
        return this.restaurantList;
    }

    public LiveData<Restaurant> getRestaurantDetail(int restaurantId) {
        if(this.restaurant == null){
            this.restaurant = new MutableLiveData<>();
            fetchDataRestaurantDetail(restaurantId);
        }
        return this.restaurant;
    }

    LiveData<List<ObjReview>> getReviews(int restaurantId){
        if(this.reviewsList == null){
            this.reviewsList = new MutableLiveData<>();
            fetchReviewsRestaurant(restaurantId);
        }
        return this.reviewsList;
    }

    private void fetchDataRestaurant(String entityTpe){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<Restaurants> call = api.fetchData(11052, entityTpe);

        call.enqueue(new Callback<Restaurants>() {
            @Override
            public void onResponse(@NonNull Call<Restaurants> call, Response<Restaurants> response) {
                if (response.body() != null) {
                    restaurantList.setValue(response.body().getRestaurant());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Restaurants> call, Throwable t) {

            }
        });
    }

    private void fetchDataRestaurantDetail(int restaurantId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<Restaurant> call = api.fetchDataDetail(restaurantId);

        call.enqueue(new Callback<Restaurant>() {

            @Override
            public void onResponse(@NonNull Call<Restaurant> call, Response<Restaurant> response) {
                restaurant.setValue(response.body());
                if (response.body() != null) {
                    Log.e("", response.body().getName());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Restaurant> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void fetchReviewsRestaurant(int restaurantId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<Reviews> call = api.fetchReviews(restaurantId);

        call.enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(@NonNull Call<Reviews> call, Response<Reviews> response) {
                if (response.body() != null) {
                    reviewsList.setValue(response.body().getList());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Reviews> call, Throwable t) {

            }
        });
    }
}
