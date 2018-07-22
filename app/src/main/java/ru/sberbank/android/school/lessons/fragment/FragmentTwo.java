package ru.sberbank.android.school.lessons.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.lesson5.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;

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
        init(view);
    }

    private void init(View v) {
        buttonOne = v.findViewById(R.id.button1);
        buttonTwo = v.findViewById(R.id.button2);
        buttonThree = v.findViewById(R.id.button3);
        buttonFour = v.findViewById(R.id.button4);
        buttonFive = v.findViewById(R.id.button5);
        buttonSix = v.findViewById(R.id.button6);
        buttonSeven = v.findViewById(R.id.button7);
        buttonEight = v.findViewById(R.id.button8);
        buttonNine = v.findViewById(R.id.button9);
    }

    public void setTextColors(int[] randomColors) {
        buttonOne.setTextColor(randomColors[0]);
        buttonTwo.setTextColor(randomColors[1]);
        buttonThree.setTextColor(randomColors[2]);
        buttonFour.setTextColor(randomColors[3]);
        buttonFive.setTextColor(randomColors[4]);
        buttonSix.setTextColor(randomColors[5]);
        buttonSeven.setTextColor(randomColors[6]);
        buttonEight.setTextColor(randomColors[7]);
        buttonNine.setTextColor(randomColors[8]);
    }

    public void setText(String[] randomWords) {
        buttonOne.setText(randomWords[0]);
        buttonTwo.setText(randomWords[1]);
        buttonThree.setText(randomWords[2]);
        buttonFour.setText(randomWords[3]);
        buttonFive.setText(randomWords[4]);
        buttonSix.setText(randomWords[5]);
        buttonSeven.setText(randomWords[6]);
        buttonEight.setText(randomWords[7]);
        buttonNine.setText(randomWords[8]);
    }

    public void setTextToButton(String text) {
        buttonFive.setText(text);
    }
}
