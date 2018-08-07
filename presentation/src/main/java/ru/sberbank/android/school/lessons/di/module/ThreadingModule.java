package ru.sberbank.android.school.lessons.di.module;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.sberbank.android.school.lessons.MainThreadImpl;
import ru.sberbank.android.school.lessons.domain.executor.MainThread;
import ru.sberbank.android.school.lessons.domain.executor.ThreadExecutor;
import ru.sberbank.android.school.lessons.domain.executor.WeatherExecutor;

@Module
public class ThreadingModule {

    @Provides
    @Singleton
    public MainThread provideMainThread(Handler handler) {
        return new MainThreadImpl(handler);
    }

    @Provides
    @Singleton
    public Handler provideHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    @Singleton
    public WeatherExecutor provideWeatherExecutor() {
        return new ThreadExecutor(Executors.newSingleThreadExecutor());
    }
}
