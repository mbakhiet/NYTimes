package com.codepath.nytimessearch.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.nytimessearch.Article;
import com.codepath.nytimessearch.ArticleRecycleAdapter;
import com.codepath.nytimessearch.R;
import com.codepath.nytimessearch.helpers.EndlessRecyclerViewScrollListener;
import com.codepath.nytimessearch.helpers.ItemClickSupport;
import com.codepath.nytimessearch.helpers.SearchFilter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    EditText etQuery;
    RecyclerView rvContents;
    Button btnSearch;

    ArrayList<Article> articles;
    ArticleRecycleAdapter adapter;
    SearchFilter searchFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupViews();


    }

    public void setupViews(){
        searchFilter = new SearchFilter();
        btnSearch = (Button) findViewById(R.id.btnSearch);
        rvContents = (RecyclerView) findViewById(R.id.rvContents);
        rvContents.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        articles = new ArrayList<>();
        adapter = new ArticleRecycleAdapter(getApplicationContext(), articles);
        adapter.notifyItemRangeInserted(adapter.getItemCount(), articles.size());
        rvContents.setAdapter(adapter);
        etQuery = (EditText) findViewById(R.id.etQuery);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rvContents.setLayoutManager(layoutManager);
        // Add the scroll listener
        rvContents.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadFromAPI(page);
            }
        });

        ItemClickSupport.addTo(rvContents).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        //create an intent to display the article
                        Intent i = new Intent(getApplicationContext(), ArticleActivity.class);

                        //get the article
                        Article article = articles.get(position);

                        //pass that article to the intent
                        i.putExtra("article", article);

                        //launch the Article activity
                        startActivity(i);
                    }
                }
        );
    }

    public void loadFromAPI(int page){
        // Send an API request to retrieve appropriate data using the offset value as a parameter.
        //  --> Deserialize API response and then construct new objects to append to the adapter
        //  --> Notify the adapter of the changes
        String query = etQuery.getText().toString();
        //Toast.makeText(this, "Searching for " + query + "...", Toast.LENGTH_LONG).show();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";

        RequestParams params = new RequestParams();
        params.put("api-key", "6013e42e9aac470f928b14119913c31b");
        params.put("page", page);
        params.put("q", query);

        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray articleJsonResults = null;

                try{
                    articleJsonResults = response.getJSONObject("response").getJSONArray("docs");
                    articles.addAll(Article.fromJSONArray(articleJsonResults));
                    adapter.notifyDataSetChanged();

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.filterIcon){
            onFilter();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onFilter(){
        Intent i = new Intent(getApplicationContext(),FilterActivity.class);
        i.putExtra("filters",searchFilter);
        startActivity(i);
    }

    public void onArticleSearch(final View view) {
        adapter.clearData();
        loadFromAPI(0);
    }
}
