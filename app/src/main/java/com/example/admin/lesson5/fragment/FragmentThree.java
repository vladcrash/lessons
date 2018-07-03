package com.example.admin.lesson5.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.lesson5.R;
import com.example.admin.lesson5.adapter.MyAdapter;
import com.example.admin.lesson5.asynchronous.ColorsLoader;

import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment implements LoaderManager.LoaderCallbacks<List<Integer>> {
    public static final int LOADER_ID = 2;
    public static final String COLORS_QUANTITY = "COLORS_QUANTITY";
    private final static int DO_UPDATE_LIST = 0;

    private RecyclerView recyclerView;
    private static MyAdapter adapter;
    private Loader<List<Integer>> loader;
    private MyHandler handler;

    public static final FragmentThree newInstance() {
        return new FragmentThree();
    }

    public FragmentThree() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MyAdapter();
        handler = new MyHandler();
        loader = getLoaderManager().initLoader(LOADER_ID, getBundle(), this);
        loader.forceLoad();
    }

    @NonNull
    private Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(COLORS_QUANTITY, new Random().nextInt());
        return bundle;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public Loader<List<Integer>> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == LOADER_ID) {
            loader = new ColorsLoader(getContext(), args);
        }

        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Integer>> loader, List<Integer> data) {
        updateAdapter(data);
        getLoaderManager().restartLoader(LOADER_ID, getBundle(), this).forceLoad();
    }

    private void updateAdapter(List<Integer> data) {
        Message message = new Message();
        message.what = DO_UPDATE_LIST;
        message.obj = data;
        handler.sendMessage(message);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Integer>> loader) {

    }

    private static class MyHandler extends Handler {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DO_UPDATE_LIST:
                    adapter.setColorList((List<Integer>) msg.obj);
                    break;
            }
        }
    }
}
