package com.example.admin.lesson5.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.lesson5.R;
import com.example.admin.lesson5.asynchronous.ColorLoader;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment implements LoaderManager.LoaderCallbacks<Integer>{
    public static final int LOADER_ID = 1;

    private TextView textView;
    private Loader<Integer> loader;

    public static final FragmentOne newInstance() {
        return new FragmentOne();
    }

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loader = getLoaderManager().initLoader(LOADER_ID, null, this);
        loader.forceLoad();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.text_view);
    }

    @NonNull
    @Override
    public Loader<Integer> onCreateLoader(int id, @Nullable Bundle bundle) {
        if (id == LOADER_ID) {
            loader = new ColorLoader(getContext());
        }

        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Integer> loader, Integer integer) {
        textView.setBackgroundColor(integer);
        loader.forceLoad();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Integer> loader) {

    }
}
