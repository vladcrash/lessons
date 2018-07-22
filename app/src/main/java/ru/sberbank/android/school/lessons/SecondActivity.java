package ru.sberbank.android.school.lessons;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Messenger serviceMessenger;
    private Messenger messenger;

    public static final Intent newIntent(Context context) {
        return new Intent(context, SecondActivity.class);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            serviceMessenger = new Messenger(service);

            try {
                Message msg = Message.obtain(null, ServiceInfoGenerator.MSG_REGISTER_CLIENT);
                msg.replyTo = messenger;
                serviceMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            serviceMessenger = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    private void init() {
        textView = findViewById(R.id.text_view);
        messenger = new Messenger(new IncomingHandler());
        bindService();
    }


    @Override
    protected void onResume() {
        super.onResume();
        bindService();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBindService();
    }

    public void bindService() {
        bindService(ServiceInfoGenerator.newIntent(SecondActivity.this),
                serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unBindService() {
        try {
            Message msg = Message.obtain(null, ServiceInfoGenerator.MSG_UNREGISTER_CLIENT);
            msg.replyTo = messenger;
            serviceMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        unbindService(serviceConnection);
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            textView.setText(msg.obj.toString());
        }
    }
}

