package ru.sberbank.android.school.lessons.domain.interactor;

import ru.sberbank.android.school.lessons.domain.executor.WeatherExecutor;
import ru.sberbank.android.school.lessons.domain.executor.MainThread;

public abstract class UseCase<P> {

    protected MainThread mainThread;
    protected WeatherExecutor<P> threadExecutor;

    public UseCase(MainThread mainThread, WeatherExecutor<P> threadExecutor) {
        this.mainThread = mainThread;
        this.threadExecutor = threadExecutor;
    }

    public abstract void run(P params);

    public void execute(P params) {
        threadExecutor.execute(this, params);
    }
}
