package com.example.admin.lesson5.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.admin.lesson5.model.Forecast;
import com.example.admin.lesson5.model.Hour;

import java.util.List;

@Dao
public interface HourDao {
    @Query("SELECT * FROM Hour WHERE forecast_id=:id")
    List<Hour> getHoursByForecastId(int id);

    @Insert
    void insertAll(List<Hour> hours);
}
