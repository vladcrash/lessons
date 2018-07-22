package ru.sberbank.android.school.lessons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String CHANGE_STATE = "CHANGE_STATE";
    public static final String EXTRA_STATE = "EXTRA_STATE";

    private TextView stateTextView;
    private Button changeStateButton;
    private StateReceiver stateReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(stateReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(stateReceiver);
    }

    private void init() {
        stateTextView = findViewById(R.id.textView);
        changeStateButton = findViewById(R.id.change_state_button);
        stateReceiver = new StateReceiver();
        intentFilter = new IntentFilter(CHANGE_STATE);
    }

    private void setListeners() {
        changeStateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(StateService.newIntent(MainActivity.this));
            }
        });
    }

    public class StateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            stateTextView.setText(intent.getStringExtra(EXTRA_STATE));
        }
    }
}
