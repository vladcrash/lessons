package ru.sberbank.android.school.lessons.domain.interactor;

public interface Callback<R> {

    void onSuccess(R r);
    void onError(Throwable throwable);
}
