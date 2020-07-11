package com.example.zomatorestaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zomatorestaurant.R;
import com.example.zomatorestaurant.pojo.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    Context context;
    List<Restaurant> restaurantList;

    public RestaurantAdapter(Context context, List<Restaurant> list){
        this.context = context;
        this.restaurantList = list;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(this.context).inflate(R.layout.recyclerview_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        Glide.with(this.context)
                .load(restaurant.getUrlImage())
                .into(holder.imageView);
        holder.textView.setText(restaurant.getName());
    }

    @Override
    public int getItemCount(){
        return restaurantList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.imageRestaurant);
            textView = view.findViewById(R.id.nameRestaurant);
        }
    }
}
