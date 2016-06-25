package com.codepath.nytimessearch.helpers;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.SearchView;

import java.util.Calendar;

/**
 * Created by mbakhiet on 6/23/16.
 */
public class SearchFilter implements Parcelable {

    int day;
    int month;
    int year;
    boolean sort;
    String newsDesk;

    public SearchFilter(){

        sort = false;
        newsDesk = "";

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isSort() {
        return sort;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.day);
        dest.writeInt(this.month);
        dest.writeInt(this.year);
        dest.writeByte(this.sort ? (byte) 1 : (byte) 0);
        dest.writeString(this.newsDesk);
    }

    protected SearchFilter(Parcel in) {
        this.day = in.readInt();
        this.month = in.readInt();
        this.year = in.readInt();
        this.sort = in.readByte() != 0;
        this.newsDesk = in.readString();
    }

    public static final Parcelable.Creator<SearchFilter> CREATOR = new Parcelable.Creator<SearchFilter>() {
        @Override
        public SearchFilter createFromParcel(Parcel source) {
            return new SearchFilter(source);
        }

        @Override
        public SearchFilter[] newArray(int size) {
            return new SearchFilter[size];
        }
    };
}
