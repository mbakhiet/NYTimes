package com.codepath.nytimessearch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mbakhiet on 6/20/16.
 */
public class Article implements Serializable {

    String webUrl;
    String headline;
    String thumbNail;

    public String getThumbNail() {
        return thumbNail;
    }

    public String getHeadline() {
        return headline;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public Article(JSONObject jsonObject){
        try{
            this.webUrl = jsonObject.getString("web_url");
            this.headline = jsonObject.getJSONObject("headline").getString("main");

            JSONArray multimedia = jsonObject.getJSONArray("multimedia");

            if (multimedia.length() > 0){
                JSONObject multimediaJSON = multimedia.getJSONObject(0);
                this.thumbNail = "http://www.nytimes.com/" + multimediaJSON.getString("url");
            }
            else{
                this.thumbNail = "";
            }

        } catch (Exception e){

        }
    }

    public static ArrayList<Article> fromJSONArray(JSONArray array){
        ArrayList<Article> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try{
                results.add(new Article(array.getJSONObject(x)));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return results;
    }
}
