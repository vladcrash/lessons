package ru.sberbank.android.school.lessons.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.sberbank.android.school.lessons.domain.model.Forecast;

@Dao
public interface ForecastDao {

    @Query("SELECT * FROM FORECAST")
    List<Forecast> getAll();

    @Insert
    void insertAll(List<Forecast> forecasts);

    @Query("DELETE FROM FORECAST")
    void deleteAll();
}
