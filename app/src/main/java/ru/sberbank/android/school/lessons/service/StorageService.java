package ru.sberbank.android.school.lessons.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.admin.lesson5.IMyAidlInterface;
import ru.sberbank.android.school.lessons.util.SharedPreferencesManager;

public class StorageService extends Service {
    public static final Intent newIntent(Context context) {
        Intent intent = new Intent(context, StorageService.class);
        intent.setAction(IMyAidlInterface.class.getName());
        return intent;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterface.Stub() {
            @Override
            public void write(String text) throws RemoteException {
                SharedPreferencesManager.write(StorageService.this, text);
            }

            @Override
            public String read() throws RemoteException {
                return SharedPreferencesManager.read(StorageService.this);
            }
        };
    }
}
