package com.example.admin.lesson5;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

public class CustomAdapter extends RecyclerView.Adapter {
    private List<ViewHolderBinder> binders;
    private SparseArray<ViewHolderFactory> factoryMap;

    public CustomAdapter() {
        initFactory();
    }

    private void initFactory() {
        factoryMap = new SparseArray<>();
        factoryMap.put(ItemTypes.ONE_BUTTON_TYPE.type,
                new OneButtonViewHolderFactory());
        factoryMap.put(ItemTypes.TWO_BUTTONS_TYPE.type,
                new TwoButtonsViewHolderFactory());
        factoryMap.put(ItemTypes.THREE_BUTTONS_TYPE.type,
                new ThreeButtonsViewHolderFactory());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
