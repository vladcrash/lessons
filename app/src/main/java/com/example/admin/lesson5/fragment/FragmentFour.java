package com.example.admin.lesson5.fragment;


import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.lesson5.R;
import com.example.admin.lesson5.service.ServiceFour;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFour extends Fragment {
    private TextView textViewOne;
    private TextView textViewTwo;
    private Button button;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            final ServiceFour serviceFour = ((ServiceFour.LocalBinder) service).getService();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long millis = TimeUnit.SECONDS.toMillis(12);
                    ValueAnimator valueAnimator = serviceFour.animateButton(button, millis);
                    animateButton(valueAnimator);
                    for (int i = 0; i < 1000; i++) {
                        try {
                            String[] randomWords = serviceFour.getRandomWords(2);
                            updateUI(randomWords);
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        private void updateUI(final String[] randomWords) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewOne.setText(randomWords[0]);
                    textViewTwo.setText(randomWords[1]);
                }
            });
        }

        private void animateButton(final ValueAnimator valueAnimator) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    valueAnimator.start();
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public static final FragmentFour newInstance() {
        return new FragmentFour();
    }

    public FragmentFour() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().bindService(ServiceFour.newIntent(getActivity().getApplicationContext()), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unbindService(serviceConnection);
    }

    private void init(View view) {
        textViewOne = view.findViewById(R.id.textView1);
        textViewTwo = view.findViewById(R.id.textView2);
        button = view.findViewById(R.id.button);
    }
}
