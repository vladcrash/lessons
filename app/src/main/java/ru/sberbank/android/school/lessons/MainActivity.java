package ru.sberbank.android.school.lessons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements FragmentOne.Callback{
    public static final String SEND_DATA = "SEND_DATA";
    public static final String EXTRA_DATA = "EXTRA_DATA";
    private DataReceiver receiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        receiver = new DataReceiver();
        intentFilter = new IntentFilter(SEND_DATA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);
        startService(ServiceDataGenerator.newIntent(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void setText(String text) {
        Fragment fragmentTwo = getSupportFragmentManager().findFragmentById(R.id.fragment_two);
        ((FragmentTwo)fragmentTwo).setText(text);
    }

    public class DataReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Integer extra = intent.getIntExtra(EXTRA_DATA, 0);
            Log.wtf(MainActivity.class.getName(), extra + " rum pam pam");
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_one);
            ((FragmentOne)fragment).setText(extra.toString());
        }
    }



}
