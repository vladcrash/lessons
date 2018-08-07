package ru.sberbank.android.school.lessons.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.sberbank.android.school.lessons.di.module.AppModule;
import ru.sberbank.android.school.lessons.di.module.DatabaseModule;
import ru.sberbank.android.school.lessons.di.module.NetworkModule;
import ru.sberbank.android.school.lessons.di.module.RepositoryModule;
import ru.sberbank.android.school.lessons.di.module.ThreadingModule;

@Singleton
@Component(modules = {AppModule.class, DatabaseModule.class, NetworkModule.class,
        RepositoryModule.class, ThreadingModule.class})
public interface AppComponent {

    WeatherComponent.Builder weatherComponentBuilder();
    WeatherDetailComponent.Builder weatherDetailComponentBuilder();
}
