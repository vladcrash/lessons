package ru.sberbank.android.school.lessons.di.module;

import dagger.Module;
import dagger.Provides;
import ru.sberbank.android.school.lessons.di.ActivityScope;
import ru.sberbank.android.school.lessons.domain.interactor.GetHourlyForecasts;
import ru.sberbank.android.school.lessons.domain.repository.ForecastRepository;
import ru.sberbank.android.school.lessons.weatherdetail.WeatherDetailAdapter;
import ru.sberbank.android.school.lessons.weatherdetail.WeatherDetailContract;
import ru.sberbank.android.school.lessons.weatherdetail.WeatherDetailPresenter;

@Module
public class WeatherDetailModule {

    @Provides
    @ActivityScope
    public WeatherDetailContract.Presenter provideWeatherDetailPresenterImpl(WeatherDetailPresenter weatherDetailPresenter) {
        return weatherDetailPresenter;
    }

    @Provides
    @ActivityScope
    public WeatherDetailPresenter provideWeatherDetailPresenter(GetHourlyForecasts getHourlyForecastsUseCase) {
        return new WeatherDetailPresenter(getHourlyForecastsUseCase);
    }

    @Provides
    @ActivityScope
    public GetHourlyForecasts provideGetHourlyForecasts(ForecastRepository repository) {
        return new GetHourlyForecasts(repository);
    }

    @Provides
    @ActivityScope
    public WeatherDetailAdapter provideWeatherDetailAdapter() {
        return new WeatherDetailAdapter();
    }
}
