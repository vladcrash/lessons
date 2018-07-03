package com.example.admin.lesson5.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.lesson5.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {
    private TextView textView;

    public static final FragmentTwo newInstance() {
        return new FragmentTwo();
    }

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.text_view);
        new RandomNumberTask().execute();
    }

    public class RandomNumberTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                try {
                    publishProgress(random.nextInt());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return random.nextInt();
        }

        @Override
        protected void onPostExecute(Integer randomNumber) {
            super.onPostExecute(randomNumber);
            textView.setText(String.valueOf(randomNumber));
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.setText(String.valueOf(values[0]));
        }
    }

}
