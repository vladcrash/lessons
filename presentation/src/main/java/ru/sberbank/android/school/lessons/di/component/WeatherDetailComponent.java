package ru.sberbank.android.school.lessons.di.component;

import dagger.Subcomponent;
import ru.sberbank.android.school.lessons.di.ActivityScope;
import ru.sberbank.android.school.lessons.di.module.WeatherDetailModule;
import ru.sberbank.android.school.lessons.weatherdetail.WeatherDetailActivity;

@ActivityScope
@Subcomponent(modules = {WeatherDetailModule.class})
public interface WeatherDetailComponent {

    @Subcomponent.Builder
    interface Builder {
        WeatherDetailComponent.Builder weatherDetailModule(WeatherDetailModule weatherDetailModule);
        WeatherDetailComponent build();
    }

    void inject(WeatherDetailActivity weatherDetailActivity);
}
