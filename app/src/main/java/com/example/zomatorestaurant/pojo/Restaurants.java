package com.example.zomatorestaurant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Restaurants {

    // Initiate arrays of restaurant
    @SerializedName("results_found")
    @Expose
    private int resultsFound;

    @SerializedName("results_start")
    @Expose
    private int resultsStart;

    @SerializedName("results_shown")
    @Expose
    private int resultsShown;

    @SerializedName("restaurants")
    @Expose
    private List<ObjRestaurant> restaurants;

    public List<ObjRestaurant> getRestaurant(){
        return this.restaurants;
    }

    public void setRestaurant(List<ObjRestaurant> restaurant) {
        this.restaurants = restaurant;
    }
    //    public Restaurants(Restaurant list){
//        this.restaurant = list;
//    }
//
//    public Restaurant getRestaurant() {
//        return restaurant;
//    }
//
//    public void setRestaurant(Restaurant restaurant) {
//        this.restaurant = restaurant;
//    }

//    public List<Restaurant> getRestaurantList() {
//        return restaurantList;
//    }
//
//    public void setRestaurantList(List<Restaurant> restaurantList) {
//        this.restaurantList = restaurantList;
//    }

//    public Restaurant get(int position){
//        return this.restaurantList.get(position);
//    }
//
//    public int size(){
//        return this.restaurantList.size();
//    }
}
