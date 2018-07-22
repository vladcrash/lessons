package ru.sberbank.android.school.lessons.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import ru.sberbank.android.school.lessons.util.DataProviderUtil;
import ru.sberbank.android.school.lessons.activity.MainActivity;

import java.util.concurrent.TimeUnit;

public class ServiceOne extends Service {
    public static final int SERVICE_ONE = 1;

    public static final Intent newIntent(Context context) {
        return new Intent(context, ServiceOne.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        Intent intent = new Intent(MainActivity.SEND_COLORS);
                        int[] colors = DataProviderUtil.getRandomColors(ServiceOne.this.getApplicationContext(), 3);
                        intent.putExtra(MainActivity.COLORS, colors);
                        intent.putExtra(MainActivity.SERVICE_ID, SERVICE_ONE);
                        LocalBroadcastManager.getInstance(ServiceOne.this).sendBroadcast(intent);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
