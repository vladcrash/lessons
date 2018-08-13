package ru.sberbank.android.school.lessons.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.sberbank.android.school.lessons.domain.model.Hour;

@Dao
public interface HourDao {

    @Query("SELECT * FROM Hour WHERE forecast_id=:id")
    Single<List<Hour>> getHoursByForecastId(Long id);

    @Insert
    void insertAll(List<Hour> hours);
}
