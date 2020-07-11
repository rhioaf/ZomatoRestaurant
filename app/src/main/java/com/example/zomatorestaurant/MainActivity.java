package com.example.zomatorestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.zomatorestaurant.adapter.RestaurantAdapter;
import com.example.zomatorestaurant.api.API;
import com.example.zomatorestaurant.pojo.ObjRestaurant;
import com.example.zomatorestaurant.pojo.Restaurant;
import com.example.zomatorestaurant.pojo.Restaurants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // List of variables
    private RecyclerView recyclerView = null;
    private List<ObjRestaurant> restaurantsList;
    private RestaurantAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        // Locate recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        // Arrange recyclerview as needed
        recyclerView.setHasFixedSize(true);
        adapter = new RestaurantAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Config.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        API api = retrofit.create(API.class);
//        Call<Restaurants> call = api.fetchData();
//
//        call.enqueue(new Callback<Restaurants>() {
//            @Override
//            public void onResponse(Call<Restaurants> call, Response<Restaurants> response) {
//                restaurantsList = response.body().getRestaurant();
//                recyclerView.setAdapter(new RestaurantAdapter(getApplicationContext(), restaurantsList));
//            }
//
//            @Override
//            public void onFailure(Call<Restaurants> call, Throwable t) {
//
//            }
//        });

        // View Model
        RestaurantViewModel viewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        viewModel.getRestaurant().observe(this, new Observer<List<ObjRestaurant>>() {
            @Override
            public void onChanged(List<ObjRestaurant> objRestaurants) {
                adapter.setData(objRestaurants);
            }
        });
//        viewModel.getRestaurant().observe(this, new Observer<List<Restaurants>>() {
//            @Override
//            public void onChanged(List<ObjRestaurant> restaurants) {
//                adapter.setData(restaurants);
//            }
//        });
    }
}