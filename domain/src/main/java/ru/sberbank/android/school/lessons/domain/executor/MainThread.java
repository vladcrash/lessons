package ru.sberbank.android.school.lessons.domain.executor;

public interface MainThread {

    void post(Runnable runnable);
}
