package ru.sberbank.android.school.lessons.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.data.db.WeatherDatabase;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public WeatherDatabase provideWeatherDatabase(Context context) {
        return Room.databaseBuilder(context, WeatherDatabase.class, context.getString(R.string.database_name))
                .build();
    }
}
