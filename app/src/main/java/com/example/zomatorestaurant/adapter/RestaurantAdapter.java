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
//        Glide.with(this.context)
//                .load(restaurant.getUrlImage())
//                .into(holder.imageView);
        holder.textView.setText(restaurant.getRestaurant().getName());
    }

    @Override
    public int getItemCount(){
        if(this.list == null){
            return 0;
        }
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
        TextView textView;

        public ViewHolder(View view){
            super(view);
//            imageView = view.findViewById(R.id.imageRestaurant);
            textView = (TextView) view.findViewById(R.id.nameRestaurant);
        }
    }
}
