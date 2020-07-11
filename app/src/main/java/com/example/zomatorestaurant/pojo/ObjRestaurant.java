package com.example.zomatorestaurant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObjRestaurant {
    @SerializedName("restaurant")
    @Expose
    private Restaurant restaurant;

    public Restaurant getRestaurant(){return this.restaurant;}
    public void setRestaurant(Restaurant restaurant){this.restaurant = restaurant;}
}
