package com.example.zomatorestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zomatorestaurant.R;
import com.example.zomatorestaurant.adapter.RestaurantAdapter;
import com.example.zomatorestaurant.api.API;
import com.example.zomatorestaurant.pojo.ObjRestaurant;
import com.example.zomatorestaurant.pojo.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RestaurantDetailActivity extends AppCompatActivity {

    private Context context;
    private ImageView ivRestaurant;
    private TextView tvRestaurantName;
    private RestaurantAdapter adapter;
    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        this.context = getApplicationContext();

        //Find views
        ivRestaurant = (ImageView) findViewById(R.id.iv_restaurant_detail);
        tvRestaurantName = (TextView) findViewById(R.id.tv_restaurant_name_detail);

        Intent intent = getIntent();
        final int restaurantId = intent.getIntExtra("restaurantId", 0);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<Restaurant> call = api.fetchDataDetail(restaurantId);

        call.enqueue(new Callback<Restaurant>() {

            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
//                Log.e("", response.body().getName());
                restaurant = response.body();
                Glide.with(getApplicationContext())
                        .load(restaurant.getUrlImage())
                        .into(ivRestaurant);
                tvRestaurantName.setText(restaurant.getName());
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });


//        RestaurantViewModel viewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
//        viewModel.getRestaurantDetail(restaurantId).observe(this, new Observer<Restaurant>() {
//            @Override
//            public void onChanged(Restaurant restaurant) {
////                Glide.with(context)
////                        .load(restaurant.getUrlImage())
////                        .load(ivRestaurant);
//                tvRestaurantName.setText(restaurant.getName());
//            }
//        });
//        viewModel.getRestaurantDetail(restaurantId).observe(this, new Observer<Restaurant>() {
//            @Override
//            public void onChanged(ObjRestaurant objRestaurant) {
//                Glide.with(context)
//                        .load(objRestaurant.getRestaurant().getUrlImage())
//                        .into(ivRestaurant);
//                tvRestaurantName.setText(objRestaurant.getRestaurant().getName());
//            }
//        });
    }
}
