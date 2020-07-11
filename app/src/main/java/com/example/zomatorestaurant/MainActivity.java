package com.example.zomatorestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.zomatorestaurant.adapter.RestaurantAdapter;
import com.example.zomatorestaurant.pojo.Restaurant;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // List of variables
    private RecyclerView recyclerView = null;
    private List<Restaurant> restaurantList;
    private RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locate recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        // Arrange recyclerview as needed
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // View Model
        RestaurantViewModel viewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        viewModel.getRestaurant().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> list) {
                adapter = new RestaurantAdapter(MainActivity.this, list);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}