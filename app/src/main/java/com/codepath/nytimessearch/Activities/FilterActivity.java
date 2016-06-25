package com.codepath.nytimessearch.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.codepath.nytimessearch.helpers.SearchFilter;

import static com.codepath.nytimessearch.R.layout.activity_filter;

public class FilterActivity extends AppCompatActivity {

    SearchFilter searchFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_filter);
        searchFilter = getIntent().getParcelableExtra("filters");
        int day = searchFilter.getDay();
        Toast.makeText(getApplicationContext(), Integer.toString(day), Toast.LENGTH_LONG);
    }
}
