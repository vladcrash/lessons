package com.example.admin.lesson5;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class ServiceDataGenerator extends Service {

    public static final Intent newIntent(Context context) {
        return new Intent(context, ServiceDataGenerator.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 1000; i++) {
                        Intent broadcastIntent = new Intent(MainActivity.SEND_DATA);
                        broadcastIntent.putExtra(MainActivity.EXTRA_DATA, i);
                        sendBroadcast(broadcastIntent);
                        Log.wtf(ServiceDataGenerator.class.getName(), "" + i);
                        TimeUnit.SECONDS.sleep(3);
                    }
                    stopSelf();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
