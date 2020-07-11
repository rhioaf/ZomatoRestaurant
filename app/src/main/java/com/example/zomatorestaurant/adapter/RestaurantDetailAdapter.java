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

public class RestaurantDetailAdapter extends RecyclerView.Adapter<RestaurantDetailAdapter.ViewHolder> {

    Context context;
    Restaurant restaurant;

    public RestaurantDetailAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.activity_restaurant_detail, parent, false);
        return new ViewHolder(view);
    }

    public void setData(Restaurant restaurant){
        this.restaurant = restaurant;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantDetailAdapter.ViewHolder holder, int position) {
        Glide.with(this.context)
                .load(this.restaurant.getUrlImage())
                .into(holder.ivRestaurantImage);
        holder.tvRestaurantName.setText(this.restaurant.getName());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivRestaurantImage;
        TextView tvRestaurantName;

        public ViewHolder(View view){
            super(view);
            ivRestaurantImage = view.findViewById(R.id.iv_restaurant_detail);
            tvRestaurantName = (TextView) view.findViewById(R.id.tv_restaurant_name_detail);

        }
    }
}
