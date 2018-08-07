package ru.sberbank.android.school.lessons;

import android.app.Application;
import android.content.Context;

import ru.sberbank.android.school.lessons.di.component.AppComponent;
import ru.sberbank.android.school.lessons.di.component.DaggerAppComponent;
import ru.sberbank.android.school.lessons.di.component.WeatherComponent;
import ru.sberbank.android.school.lessons.di.component.WeatherDetailComponent;
import ru.sberbank.android.school.lessons.di.module.AppModule;
import ru.sberbank.android.school.lessons.di.module.WeatherDetailModule;
import ru.sberbank.android.school.lessons.di.module.WeatherModule;

public class App extends Application {

    private AppComponent appComponent;
    private WeatherComponent weatherComponent;
    private WeatherDetailComponent weatherDetailComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildAppComponent();
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public WeatherComponent plusWeatherComponent() {
        if (weatherComponent == null) {
            weatherComponent = appComponent
                    .weatherComponentBuilder()
                    .weatherModule(new WeatherModule())
                    .build();
        }
        return weatherComponent;
    }

    public void releaseWeatherComponent() {
        weatherComponent = null;
    }

    public WeatherDetailComponent plusWeatherDetailComponent() {
        if (weatherDetailComponent == null) {
            weatherDetailComponent = appComponent
                    .weatherDetailComponentBuilder()
                    .weatherDetailModule(new WeatherDetailModule())
                    .build();
        }
        return weatherDetailComponent;
    }

    public void releaseWeatherDetailComponent() {
        weatherDetailComponent = null;
    }

    private AppComponent buildAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
