package com.example.admin.lesson5.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.lesson5.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {
    private TextView textViewOne;
    private TextView textViewTwo;
    private TextView textViewThree;

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View v) {
        textViewOne = v.findViewById(R.id.textview1);
        textViewTwo = v.findViewById(R.id.textview2);
        textViewThree = v.findViewById(R.id.textview3);
    }

    public void setBackgroundColors(int[] backgroundColors) {
        textViewOne.setBackgroundColor(backgroundColors[0]);
        textViewTwo.setBackgroundColor(backgroundColors[1]);
        textViewThree.setBackgroundColor(backgroundColors[2]);
    }
}
