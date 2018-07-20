package com.example.admin.lesson5.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.admin.lesson5.model.Forecast;

import java.util.List;

@Dao
public interface ForecastDao {
    @Query("SELECT * FROM FORECAST")
    List<Forecast> getAll();

    @Insert
    void insertAll(List<Forecast> forecasts);

    @Query("DELETE FROM FORECAST")
    void deleteAll();
}
