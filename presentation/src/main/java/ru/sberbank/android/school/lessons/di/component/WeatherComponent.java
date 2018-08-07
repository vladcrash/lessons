package ru.sberbank.android.school.lessons.di.component;

import dagger.Subcomponent;
import ru.sberbank.android.school.lessons.di.ActivityScope;
import ru.sberbank.android.school.lessons.di.module.WeatherModule;
import ru.sberbank.android.school.lessons.weather.WeatherActivity;

@ActivityScope
@Subcomponent(modules = {WeatherModule.class})
public interface WeatherComponent {

    @Subcomponent.Builder
    interface Builder {
        WeatherComponent.Builder weatherModule(WeatherModule weatherModule);
        WeatherComponent build();
    }

    void inject(WeatherActivity weatherActivity);
}
