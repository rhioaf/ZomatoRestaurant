package com.example.zomatorestaurant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    // Initiate the var to store data from Zomato Restaurant
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("average_cost_for_two")
    @Expose
    private int average;

    @SerializedName("price_range")
    @Expose
    private int priceRange;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("featured_image")
    @Expose
    private String urlImage;

    @SerializedName("has_online_delivery")
    @Expose
    private int hasOnline;

    @SerializedName("is_delivering_now")
    @Expose
    private int isDeliver;

    @SerializedName("phone_numbers")
    @Expose
    private String phoneNumber;

    @SerializedName("user_rating")
    @Expose
    private UserRating userRating;

    // Constructor
    public Restaurant(int id, String name, String url, Location location, int average, int priceRange, String currency,
                      String urlImage, int hasOnline, int isDeliver, String phoneNumber, UserRating userRating){
        this.id = id;
        this.name = name;
        this.url = url;
        this.location = location;
        this.average = average;
        this.priceRange = priceRange;
        this.currency = currency;
        this.urlImage = urlImage;
        this.hasOnline = hasOnline;
        this.isDeliver = isDeliver;
        this.phoneNumber = phoneNumber;
        this.userRating = userRating;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getHasOnline() {
        return hasOnline;
    }

    public void setHasOnline(int hasOnline) {
        this.hasOnline = hasOnline;
    }

    public int getIsDeliver() {
        return isDeliver;
    }

    public void setIsDeliver(int isDeliver) {
        this.isDeliver = isDeliver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

}
