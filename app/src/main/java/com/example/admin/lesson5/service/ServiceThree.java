package com.example.admin.lesson5.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.example.admin.lesson5.util.DataProviderUtil;
import com.example.admin.lesson5.activity.MainActivity;

import java.util.concurrent.TimeUnit;

public class ServiceThree extends IntentService {
    public static final int SERVICE_THREE = 3;

    public static final Intent newIntent(@NonNull Context context) {
        return new Intent(context, ServiceThree.class);
    }

    public ServiceThree() {
        super(ServiceThree.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        for (int i = 0; i < 1000; i++) {
            try {
                Intent localBroadcasIntent = new Intent(MainActivity.SEND_WORDS);
                String[] randomWords = DataProviderUtil.getRandomWords(this.getApplicationContext(), 3);
                localBroadcasIntent.putExtra(MainActivity.WORDS, randomWords);
                localBroadcasIntent.putExtra(MainActivity.SERVICE_ID, SERVICE_THREE);
                LocalBroadcastManager.getInstance(this.getApplicationContext()).sendBroadcast(localBroadcasIntent);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
