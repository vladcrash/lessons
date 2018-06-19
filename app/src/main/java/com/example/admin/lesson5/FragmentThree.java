package com.example.admin.lesson5;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment {
    private TextView textView;

    public static final FragmentThree newInstance(String data) {
        Bundle args = new Bundle();
        args.putString(MainActivity.EXTRA_DATA, data);
        FragmentThree fragment = new FragmentThree();
        fragment.setArguments(args);
        return fragment;
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
        textView = view.findViewById(R.id.textview);
        textView.setText(getArguments().getString(MainActivity.EXTRA_DATA));

    }
}
