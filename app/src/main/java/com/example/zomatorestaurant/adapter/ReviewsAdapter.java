package com.example.zomatorestaurant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zomatorestaurant.R;
import com.example.zomatorestaurant.pojo.ObjReview;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    private Context context;
    private List<ObjReview> list;

    public ReviewsAdapter(Context context){
        this.context = context;
    }

    public void setData(List<ObjReview> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.recyclerview_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.ViewHolder holder, int position) {
        ObjReview objReview = list.get(position);
        Glide.with(this.context)
                .load(objReview.getReview().getUser().getProfileImage())
                .into(holder.ivUser);
        holder.tvUserName.setText(objReview.getReview().getUser().getName());
        holder.tvRating.setText(String.valueOf(objReview.getReview().getRating()));
        holder.tvReviewTime.setText(objReview.getReview().getReviewTime());
        holder.reviewText.setText(objReview.getReview().getReviewText().length() < 1 ? "No Reviews Text" : objReview.getReview().getReviewText());
    }

    @Override
    public int getItemCount() {
        return this.list == null ? 0 : this.list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView ivUser;
        TextView tvUserName, tvRating, tvReviewTime, reviewText;

        ViewHolder(View view){
            super(view);
            ivUser = view.findViewById(R.id.iv_user);
            tvUserName = view.findViewById(R.id.tv_user_name);
            tvRating = view.findViewById(R.id.tv_reviews_rating);
            tvReviewTime = view.findViewById(R.id.tv_review_time_friendly);
            reviewText = view.findViewById(R.id.tv_review);
        }
    }
}
