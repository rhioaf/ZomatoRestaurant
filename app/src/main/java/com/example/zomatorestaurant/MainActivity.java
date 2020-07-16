// Author: 181511064 Rhio Adjie Fabian

package com.example.zomatorestaurant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.zomatorestaurant.adapter.RestaurantAdapter;
import com.example.zomatorestaurant.pojo.ObjRestaurant;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        setContentView(R.layout.activity_main);

        // Locate recycler view
        // List of variables
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        // Arrange recyclerview as needed
        recyclerView.setHasFixedSize(true);
        adapter = new RestaurantAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // View Model
        RestaurantViewModel viewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        viewModel.getRestaurant().observe(this, new Observer<List<ObjRestaurant>>() {
            @Override
            public void onChanged(List<ObjRestaurant> objRestaurants) {
                adapter.setData(objRestaurants);
            }
        });
    }
}