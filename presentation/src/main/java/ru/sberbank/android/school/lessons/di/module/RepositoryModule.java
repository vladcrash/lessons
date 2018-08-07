package ru.sberbank.android.school.lessons.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.sberbank.android.school.lessons.data.db.WeatherDatabase;
import ru.sberbank.android.school.lessons.data.network.WeatherWebService;
import ru.sberbank.android.school.lessons.data.repository.ForecastRepositoryImpl;
import ru.sberbank.android.school.lessons.domain.repository.ForecastRepository;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ForecastRepository provideForecastRepository(WeatherWebService weatherWebService, WeatherDatabase weatherDatabase) {
        return new ForecastRepositoryImpl(weatherWebService, weatherDatabase);
    }
}
