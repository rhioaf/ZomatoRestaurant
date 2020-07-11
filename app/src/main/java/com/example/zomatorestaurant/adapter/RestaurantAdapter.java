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
import com.example.zomatorestaurant.pojo.ObjRestaurant;
import com.example.zomatorestaurant.pojo.Restaurant;
import com.example.zomatorestaurant.pojo.Restaurants;

import org.w3c.dom.Text;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    Context context;
    List<ObjRestaurant> list;

    public RestaurantAdapter(Context context){
//        this.list = list = null;
        this.context = context;
    }

//    public RestaurantAdapter(Context context, List<ObjRestaurant>){
//        this.list = list;
//        this.context = context;
//    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(this.context).inflate(R.layout.recyclerview_layout, parent, false);
        return new ViewHolder(view);
    }

    public void setData(List<ObjRestaurant> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ObjRestaurant restaurant = list.get(position);
        Glide.with(this.context)
                .load(restaurant.getRestaurant().getUrlImage())
                .into(holder.ivRestaurantImage);
        holder.tvRestaurantName.setText(restaurant.getRestaurant().getName());
        holder.tvCurrency.setText(restaurant.getRestaurant().getCurrency());
        holder.tvCostPerOne.setText((restaurant.getRestaurant().getAverage() / 2) + " per person");
        if (restaurant.getRestaurant().getHasOnline() == 1) {
            holder.tvHasOnlineDelivery.setText("HAS ONLINE ORDERING");
        } else {
            holder.tvHasOnlineDelivery.setText("OFFLINE ORDERING");
        }
        holder.tvAggregateRating.setText(restaurant.getRestaurant().getUserRating().getRating().toString());
    }

    @Override
    public int getItemCount(){
        if(this.list == null){
            return 0;
        }
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRestaurantImage;
        TextView tvRestaurantName, tvCurrency, tvCostPerOne, tvHasOnlineDelivery, tvAggregateRating;

        public ViewHolder(View view){
            super(view);
            ivRestaurantImage = view.findViewById(R.id.iv_restaurant);
            tvRestaurantName = (TextView) view.findViewById(R.id.tv_restaurant_name);
            tvCurrency = (TextView) view.findViewById(R.id.tv_currency);
            tvCostPerOne = (TextView) view.findViewById(R.id.tv_cost_for_one);
            tvHasOnlineDelivery = (TextView) view.findViewById(R.id.tv_has_online_delivery);
            tvAggregateRating = (TextView) view.findViewById(R.id.tv_aggregate_rating);
        }
    }
}
