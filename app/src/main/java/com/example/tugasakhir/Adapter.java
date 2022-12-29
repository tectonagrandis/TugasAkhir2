package com.example.tugasakhir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Modal> articleRVModalArrayList;
    private Context context;
    private ArticleClickInterface articleClickInterface;
    int lastPos = -1;

    public Adapter(ArrayList<Modal> articleRVModalArrayList, Context context, ArticleClickInterface articleClickInterface) {
        this.articleRVModalArrayList = articleRVModalArrayList;
        this.context = context;
        this.articleClickInterface = articleClickInterface;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder,int position) {
        Modal modal = articleRVModalArrayList.get(position);
        holder.articleTV.setText(modal.getArticleName());
        holder.articlePriceTV.setText("Rp. " + modal.getArticlePrice());
        Picasso.get().load(modal.getArticleImg()).into(holder.articleIV);
        setAnimation(holder.itemView, position);
        holder.articleIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articleClickInterface.onCourseClick(position);
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return articleRVModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView articleIV;
        private TextView articleTV, articlePriceTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleIV = itemView.findViewById(R.id.idIVArticle);
            articleTV = itemView.findViewById(R.id.idTVCOurseName);
            articlePriceTV = itemView.findViewById(R.id.idTVCousePrice);
        }
    }

    public interface ArticleClickInterface {
        void onCourseClick(int position);
    }
}