package ru.sberbank.android.school.lessons.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.lesson5.R;
import ru.sberbank.android.school.lessons.activity.MainActivity;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment {
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Callback callback;

    public interface Callback {
        void sendData(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (MainActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity should implement Callback interface");
        }
    }

    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        sendData();
    }


    private void init(View v) {
        buttonOne = v.findViewById(R.id.button1);
        buttonTwo = v.findViewById(R.id.button2);
        buttonThree = v.findViewById(R.id.button3);
    }

    public void setText(String[] text) {
        buttonOne.setText(text[0]);
        buttonTwo.setText(text[1]);
        buttonThree.setText(text[2]);
    }

    public void sendData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        callback.sendData(buttonTwo.getText().toString() + " " + String.valueOf(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
