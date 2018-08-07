package ru.sberbank.android.school.lessons;

import android.os.Handler;

import ru.sberbank.android.school.lessons.domain.executor.MainThread;

public class MainThreadImpl implements MainThread {

    private Handler handler;

    public MainThreadImpl(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
