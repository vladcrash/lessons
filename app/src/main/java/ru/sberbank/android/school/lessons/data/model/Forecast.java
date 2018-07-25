package ru.sberbank.android.school.lessons.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Weather.class,
        parentColumns = "id",
        childColumns = "weather_id",
        onDelete = CASCADE))
public class Forecast {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "weather_id")
    private Integer weatherId;

    @SerializedName("date")
    @ColumnInfo(name = "date")
    private String date;

    @Embedded
    @SerializedName("parts")
    private Parts parts;

    @SerializedName("hours")
    @Ignore
    private List<Hour> hours = null;

    public Forecast() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Parts getParts() {
        return parts;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }
}
