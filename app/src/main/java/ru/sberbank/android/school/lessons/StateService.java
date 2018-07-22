package ru.sberbank.android.school.lessons;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import static ru.sberbank.android.school.lessons.MainActivity.EXTRA_STATE;

public class StateService extends IntentService {


    public static final Intent newIntent(Context context) {
        return new Intent(context, StateService.class);
    }

    public StateService() {
        super(StateService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Intent broadcastIntent = new Intent(MainActivity.CHANGE_STATE);
        StateManager stateManager = StateManager.getInstance();
        stateManager.changeState();
        String extraState = stateManager.getCurrentState();
        broadcastIntent.putExtra(EXTRA_STATE, extraState);
        sendBroadcast(broadcastIntent);
    }
}