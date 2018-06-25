package com.example.admin.lesson5;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.textview);
        new RandomNumberTask().execute();
    }

    public class RandomNumberTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            return new Random().nextInt();
        }

        @Override
        protected void onPostExecute(Integer randomNumber) {
            super.onPostExecute(randomNumber);
            textView.setText(randomNumber.toString());
        }
    }

}
