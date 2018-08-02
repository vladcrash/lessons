package ru.sberbank.android.school.lessons.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Forecast.class,
        parentColumns = "id",
        childColumns = "forecast_id",
        onDelete = CASCADE))
public class Hour {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "forecast_id")
    private Integer forecastId;

    @SerializedName("hour")
    @ColumnInfo(name = "hour")
    private String hour;

    @SerializedName("temp")
    @ColumnInfo(name = "temp")
    private Integer temp;

    @SerializedName("feels_like")
    @ColumnInfo(name = "feels_like")
    private Integer feelsLike;

    public Hour() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForecastId() {
        return forecastId;
    }

    public void setForecastId(Integer forecastId) {
        this.forecastId = forecastId;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
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
