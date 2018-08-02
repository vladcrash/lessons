package ru.sberbank.android.school.lessons.domain.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Weather {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @SerializedName("forecasts")
    @Ignore
    private List<Forecast> forecasts;

    public Weather() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
}
