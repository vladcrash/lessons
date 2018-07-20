package com.example.admin.lesson5.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("temp_avg")
    @ColumnInfo(name = "temp_avg")
    private Integer temp;

    @SerializedName("feels_like")
    @ColumnInfo(name = "feels_like")
    private Integer feelsLike;

    public Day() {
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Integer feelsLike) {
        this.feelsLike = feelsLike;
    }
}
