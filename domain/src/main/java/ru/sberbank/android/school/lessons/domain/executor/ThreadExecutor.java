package ru.sberbank.android.school.lessons.domain.executor;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ru.sberbank.android.school.lessons.domain.interactor.UseCase;

public class ThreadExecutor<P> implements WeatherExecutor<P> {

    private Executor executor;

    private ThreadExecutor() {
        executor = Executors.newSingleThreadExecutor();
    }

    public ThreadExecutor(Executor executor) {
        this.executor = executor;
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
