package ru.sberbank.android.school.lessons.domain.executor;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ru.sberbank.android.school.lessons.domain.interactor.UseCase;

public class ThreadExecutor<P> implements WeatherExecutor<P> {

    private static ThreadExecutor threadExecutor;
    private Executor executor;

    private ThreadExecutor() {
        executor = Executors.newSingleThreadExecutor();
    }

    public static WeatherExecutor getInstance() {
        if (threadExecutor != null) {
            return threadExecutor;
        }

        return threadExecutor = new ThreadExecutor();
    }

    @Override
    public void execute(final UseCase<P> useCase, final P params) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                useCase.run(params);
            }
        });
    }
}
