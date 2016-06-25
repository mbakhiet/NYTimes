package com.codepath.nytimessearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mbakhiet on 6/20/16.
 */
public class ArticleRecycleAdapter
        extends RecyclerView.Adapter<ArticleRecycleAdapter.ViewHolder>{

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row

        ImageView imageView;
        TextView headline;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.ivImage);
            headline = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

    private List<Article> articles;
    Context context;

    public ArticleRecycleAdapter(Context myContext, List<Article> articleList) {
        context = myContext;
        articles = articleList;
    }

    private Context getContext() {
        return context;
    }

    @Override
    public ArticleRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View articlesView = inflater.inflate(R.layout.item_article_result, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(articlesView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ArticleRecycleAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Article article = articles.get(position);

        // Set item views based on the data model
        TextView textView = viewHolder.headline;
        textView.setText(article.getHeadline());

        ImageView imageView = viewHolder.imageView;

        imageView.setImageResource(0);

        //imageView.setHeightRatio(((double)imageView.getHeight())/imageView.getWidth());

        String imageUri = article.getThumbNail();
        if (!TextUtils.isEmpty(imageUri)){
            Picasso.with(getContext()).load(imageUri).into(viewHolder.imageView);
        }
        else{
            Picasso.with(getContext()).load(R.drawable.newspaper).into(imageView);
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return articles.size();
    }


    public void clearData() {
        int size = this.getItemCount();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.articles.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }

}