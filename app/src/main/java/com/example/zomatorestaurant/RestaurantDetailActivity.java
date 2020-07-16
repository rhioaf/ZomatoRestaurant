// Author: 181511049 Ivan Eka Putra dan 181511064 Rhio Adjie Fabian

package com.example.zomatorestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zomatorestaurant.adapter.ReviewsAdapter;
import com.example.zomatorestaurant.api.API;

import com.example.zomatorestaurant.pojo.ObjReview;
import com.example.zomatorestaurant.pojo.Restaurant;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RestaurantDetailActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private ImageView ivRestaurant;
    private TextView tvRestaurantName, tvRestaurantAggregateRating, tvRestaurantCurrency, tvRestaurantCostForOne, tvRestaurantHasOnlineDelivery;
    private Restaurant restaurant;
    private MapView mvRestaurant;
    private ReviewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_restaurant_detail);
        Context context = getApplicationContext();
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));

        //Find views
        RecyclerView recyclerViewReview = findViewById(R.id.recycler_view_review);
        adapter = new ReviewsAdapter(this);
        recyclerViewReview.setAdapter(adapter);
        recyclerViewReview.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewReview.setNestedScrollingEnabled(false);
        ivRestaurant = findViewById(R.id.iv_restaurant_detail);
        tvRestaurantName = findViewById(R.id.tv_restaurant_name_detail);
        tvRestaurantAggregateRating = findViewById(R.id.tv_restaurant_detail_aggregate_rating);
        tvRestaurantCurrency = findViewById(R.id.tv_restaurant_currency);
        tvRestaurantCostForOne = findViewById(R.id.tv_restaurant_cost_for_one);
        tvRestaurantHasOnlineDelivery = findViewById(R.id.tv_restaurant_has_online_delivery);
        mvRestaurant = findViewById(R.id.map);
        mvRestaurant.setTileSource(TileSourceFactory.MAPNIK);

        requestPermissionsIfNecessary(new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });

        mvRestaurant.setBuiltInZoomControls(true);
        mvRestaurant.setMultiTouchControls(true);

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
                restaurant = response.body();
                String costForOne = null;
                if (restaurant != null) {
                    costForOne = (restaurant.getAverage() / 2) + " per person";
                }

                Glide.with(getApplicationContext())
                        .load(restaurant.getThumb())
                        .into(ivRestaurant);
                tvRestaurantName.setText(restaurant.getName());
                tvRestaurantAggregateRating.setText(restaurant.getUserRating().getRating().toString());
                tvRestaurantCurrency.setText(restaurant.getCurrency());
                tvRestaurantCostForOne.setText(costForOne);
                if (restaurant.getHasOnline() == 1) {
                    tvRestaurantHasOnlineDelivery.setText(R.string.onlineOrderingAvailable);
                } else {
                    tvRestaurantHasOnlineDelivery.setText(R.string.noOnlineOrderingAvailable);
                }
                IMapController mapController = mvRestaurant.getController();
                mapController.setZoom(18);
                GeoPoint startPoint = new GeoPoint(Double.parseDouble(restaurant.getLocation().getLatitude()), Double.parseDouble(restaurant.getLocation().getLongitude()));
                mapController.setCenter(startPoint);

                Marker marker = new Marker(mvRestaurant);
                marker.setPosition(startPoint);
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                marker.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_stat_name, null));
                marker.setTitle("Start Point");
                mvRestaurant.getOverlays().add(marker);
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        RestaurantViewModel viewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);
        viewModel.getReviews(restaurantId).observe(this, new Observer<List<ObjReview>>() {
            @Override
            public void onChanged(List<ObjReview> list) {
                adapter.setData(list);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        mvRestaurant.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        mvRestaurant.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        ArrayList<String> permissionsToRequest = new ArrayList<>(Arrays.asList(permissions).subList(0, grantResults.length));
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] strings) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : strings) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }
}
