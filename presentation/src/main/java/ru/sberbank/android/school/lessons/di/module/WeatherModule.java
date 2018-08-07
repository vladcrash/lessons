package ru.sberbank.android.school.lessons.di.module;

import dagger.Module;
import dagger.Provides;
import ru.sberbank.android.school.lessons.di.ActivityScope;
import ru.sberbank.android.school.lessons.domain.executor.MainThread;
import ru.sberbank.android.school.lessons.domain.executor.WeatherExecutor;
import ru.sberbank.android.school.lessons.domain.interactor.GetDailyForecasts;
import ru.sberbank.android.school.lessons.domain.repository.ForecastRepository;
import ru.sberbank.android.school.lessons.weather.WeatherAdapter;
import ru.sberbank.android.school.lessons.weather.WeatherViewModel;

@Module
public class WeatherModule {

    @Provides
    @ActivityScope
    public WeatherViewModel provideWeatherViewModel(WeatherAdapter adapter, GetDailyForecasts getDailyForecastsUseCase) {
        return new WeatherViewModel(adapter, getDailyForecastsUseCase);
    }

    @Provides
    @ActivityScope
    public WeatherAdapter provideWeatherAdapter() {
        return new WeatherAdapter();
    }

    @Provides
    @ActivityScope
    public GetDailyForecasts provideGetDailyForecasts(MainThread mainThread,
                                                      WeatherExecutor executor,
                                                      ForecastRepository repository) {

        return new GetDailyForecasts(mainThread, executor, repository);
    }
}
