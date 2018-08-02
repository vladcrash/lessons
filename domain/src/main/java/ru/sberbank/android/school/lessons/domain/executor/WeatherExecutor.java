package ru.sberbank.android.school.lessons.domain.executor;

import ru.sberbank.android.school.lessons.domain.interactor.UseCase;

public interface WeatherExecutor<P> {

    void execute(UseCase<P> useCase, P params);
}
