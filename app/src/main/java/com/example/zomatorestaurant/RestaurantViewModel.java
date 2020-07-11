package com.example.zomatorestaurant;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zomatorestaurant.api.API;
import com.example.zomatorestaurant.pojo.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantViewModel extends ViewModel {

    // Instance of class ViewModel
    private MutableLiveData<List<Restaurant>> restaurantList;

    // Fetch data
    public LiveData<List<Restaurant>> getRestaurant(){
        if(this.restaurantList == null){
            this.restaurantList = new MutableLiveData<List<Restaurant>>();


        }
        return this.restaurantList;
    }

    private void fetchDataRestaurant(){
        Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(Config.BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();
        API api = retrofit.create(API.class);
        Call<List<Restaurant>> call = api.fetchData();

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                restaurantList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {

            }
        });
    }
}
