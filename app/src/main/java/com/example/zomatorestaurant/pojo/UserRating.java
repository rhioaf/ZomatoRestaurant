package com.example.zomatorestaurant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRating {

    @SerializedName("aggregate_rating")
    @Expose
    private Float rating;

    @SerializedName("rating_text")
    @Expose
    private String ratingText;

    @SerializedName("rating_color")
    @Expose
    private String ratingColor;

    @SerializedName("votes")
    @Expose
    private int ratingVotes;

    public UserRating(Float rating, String ratingText, String ratingColor, int ratingVotes){
        this.rating = rating;
        this.ratingText = ratingText;
        this.ratingColor = ratingColor;
        this.ratingVotes = ratingVotes;
    }

    // Getter and Setter
    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public int getRatingVotes() {
        return ratingVotes;
    }

    public void setRatingVotes(int ratingVotes) {
        this.ratingVotes = ratingVotes;
    }
}
