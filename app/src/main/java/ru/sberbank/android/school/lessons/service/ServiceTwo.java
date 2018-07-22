package ru.sberbank.android.school.lessons.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.sberbank.android.school.lessons.util.DataProviderUtil;

public class ServiceTwo extends Service {
    public static final Intent newIntent(@NonNull Context context) {
        return new Intent(context, ServiceTwo.class);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    public class LocalBinder extends Binder {
        public ServiceTwo getService() {
            return ServiceTwo.this;
        }
    }

    public int[] getRandomColors() {
        return DataProviderUtil.getRandomColors(this.getApplicationContext(), 9);
    }

    public String[] getRandomWords() {
        return DataProviderUtil.getRandomWords(this.getApplicationContext(), 9);
    }
}
