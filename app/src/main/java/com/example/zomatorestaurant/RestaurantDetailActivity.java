package com.example.zomatorestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zomatorestaurant.R;
import com.example.zomatorestaurant.adapter.RestaurantAdapter;
import com.example.zomatorestaurant.pojo.ObjRestaurant;
import com.example.zomatorestaurant.pojo.Restaurant;

import java.util.List;

public class RestaurantDetailActivity extends AppCompatActivity {

    private Context context;
    private ImageView ivRestaurant;
    private TextView tvRestaurantName;
    private RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        this.context = getApplicationContext();

        //Find views
        ivRestaurant = (ImageView) findViewById(R.id.iv_restaurant);
        tvRestaurantName = (TextView) findViewById(R.id.title);

        Intent intent = getIntent();
        int restaurantId = intent.getIntExtra("restaurantId", 0);

        RestaurantViewModel viewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        viewModel.getRestaurantDetail(restaurantId).observe(this, new Observer<ObjRestaurant>() {
            @Override
            public void onChanged(ObjRestaurant objRestaurant) {
                Glide.with(context)
                        .load(objRestaurant.getRestaurant().getUrlImage())
                        .into(ivRestaurant);
                tvRestaurantName.setText(objRestaurant.getRestaurant().getName());
            }
        });
    }
}
