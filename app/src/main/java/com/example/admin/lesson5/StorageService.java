package com.example.admin.lesson5;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class StorageService extends Service {
    public static final String TEXT_STORAGE = "TEXT_STORAGE";
    public static final String TEXT = "TEXT";

    public static final Intent newIntent(Context context) {
        return new Intent(context, StorageService.class);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    public class LocalBinder extends Binder {
        public StorageService getService() {
            return StorageService.this;
        }
    }

    public void write(String text) {
        SharedPreferences.Editor editor = getSharedPreferences(TEXT_STORAGE, MODE_PRIVATE).edit();
        editor.putString(TEXT, text);
        editor.commit();
    }

    public String read() {
        SharedPreferences preferences = getSharedPreferences(TEXT_STORAGE, MODE_PRIVATE);
        return preferences.getString(TEXT, "Oops");
    }
}
