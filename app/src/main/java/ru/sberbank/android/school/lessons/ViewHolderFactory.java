package ru.sberbank.android.school.lessons;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public interface ViewHolderFactory {

    RecyclerView.ViewHolder createViewHolder(ViewGroup viewGroup, LayoutInflater inflater);
}
