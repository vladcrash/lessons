package ru.sberbank.android.school.lessons;

import android.os.Handler;
import android.os.Looper;

import ru.sberbank.android.school.lessons.domain.executor.MainThread;

public class MainThreadImpl implements MainThread {

    private static MainThread mainThread;
    private Handler handler;

    private MainThreadImpl() {
        handler = new Handler(Looper.getMainLooper());
    }

    public static MainThread getInstance() {
        if (mainThread != null) {
            return mainThread;
        }

        return mainThread = new MainThreadImpl();
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }
}
