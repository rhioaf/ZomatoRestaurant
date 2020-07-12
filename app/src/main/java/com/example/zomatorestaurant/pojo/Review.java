package com.example.zomatorestaurant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("rating")
    @Expose
    private int rating;

    @SerializedName("review_text")
    @Expose
    private String reviewText;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("rating_color")
    @Expose
    private String ratingColor;

    @SerializedName("review_time_friendly")
    @Expose
    private String reviewTime;

    @SerializedName("rating_text")
    @Expose
    private String ratingText;

    @SerializedName("user")
    @Expose
    private User user;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
