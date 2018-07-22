package ru.sberbank.android.school.lessons.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.lesson5.R;
import ru.sberbank.android.school.lessons.fragment.FragmentOne;
import ru.sberbank.android.school.lessons.fragment.FragmentThree;
import ru.sberbank.android.school.lessons.fragment.FragmentTwo;
import ru.sberbank.android.school.lessons.service.ServiceOne;
import ru.sberbank.android.school.lessons.service.ServiceThree;
import ru.sberbank.android.school.lessons.service.ServiceTwo;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements FragmentThree.Callback {
    public static final String SEND_COLORS = "com.example.admin.SEND_COLORS";
    public static final String SEND_WORDS = "com.example.admin.SEND_WORDS";
    public static final String COLORS = "com.example.admin.COLORS";
    public static final String WORDS = "com.example.admin.WORDS";
    public static final String SERVICE_ID = "com.example.admin.SERVICE_ID";

    private BroadcastReceiver receiver;
    private IntentFilter intentFilter;

    private ServiceConnection connectionToServiceTwo = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, final IBinder service) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            final FragmentTwo fragment = (FragmentTwo) fragmentManager.findFragmentById(R.id.fragment2);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        try {
                            ServiceTwo serviceTwo = (((ServiceTwo.LocalBinder) service)).getService();
                            final int[] randomColors = serviceTwo.getRandomColors();
                            final String[] randomWords = serviceTwo.getRandomWords();
                            updateUI(fragment, randomColors, randomWords);
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }

        private void updateUI(final FragmentTwo fragment, final int[] randomColors, final String[] randomWords) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fragment.setTextColors(randomColors);
                    fragment.setText(randomWords);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(ServiceOne.newIntent(this));
        startService(ServiceThree.newIntent(this));
        init();
    }

    private void init() {
        receiver = new DataReceiver();
        intentFilter = new IntentFilter(SEND_COLORS);
        intentFilter.addAction(SEND_WORDS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(ServiceTwo.newIntent(this), connectionToServiceTwo, Context.BIND_AUTO_CREATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(connectionToServiceTwo);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    public class DataReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int id = intent.getIntExtra(SERVICE_ID, 0);
            FragmentManager fragmentManager = getSupportFragmentManager();
            switch (id) {
                case ServiceOne.SERVICE_ONE:
                    FragmentOne fragmentOne = (FragmentOne) fragmentManager.findFragmentById(R.id.fragment1);
                    fragmentOne.setBackgroundColors(intent.getIntArrayExtra(COLORS));
                    break;
                case ServiceThree.SERVICE_THREE:
                    FragmentThree fragmentThree = (FragmentThree) fragmentManager.findFragmentById(R.id.fragment3);
                    fragmentThree.setText(intent.getStringArrayExtra(WORDS));
                    break;
            }
        }
    }

    @Override
    public void sendData(final String text) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTwo fragment = (FragmentTwo) fragmentManager.findFragmentById(R.id.fragment2);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fragment.setTextToButton(text);
            }
        });
    }
}
