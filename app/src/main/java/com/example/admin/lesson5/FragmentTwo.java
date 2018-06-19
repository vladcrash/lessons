package com.example.admin.lesson5;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {
    private Button launchFragmentButton;
    private String text;

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
        launchFragmentButton = view.findViewById(R.id.launch_fragment_btn);
        launchFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchFragment();
            }
        });
    }

    public void setText(String text) {
        this.text = text;
        launchFragment();
    }

    private void launchFragment() {
        FragmentManager manager = getChildFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container, FragmentThree.newInstance(text))
                .commit();
    }
}
