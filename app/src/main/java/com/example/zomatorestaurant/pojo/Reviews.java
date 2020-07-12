package com.example.zomatorestaurant.pojo;

import com.google.firebase.storage.StorageException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Reviews {

    @SerializedName("reviews_count")
    @Expose
    private int reviews_count;

    @SerializedName("reviews_start")
    @Expose
    private int reviews_start;

    @SerializedName("reviews_shown")
    @Expose
    private int reviews_shown;

    @SerializedName("user_reviews")
    @Expose
    private List<ObjReview> list;

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getReviews_start() {
        return reviews_start;
    }

    public void setReviews_start(int reviews_start) {
        this.reviews_start = reviews_start;
    }

    public int getReviews_shown() {
        return reviews_shown;
    }

    public void setReviews_shown(int reviews_shown) {
        this.reviews_shown = reviews_shown;
    }

    public List<ObjReview> getList() {
        return list;
    }

    public void setList(List<ObjReview> list) {
        this.list = list;
    }
}
