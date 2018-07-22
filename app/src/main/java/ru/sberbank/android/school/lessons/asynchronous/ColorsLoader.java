package ru.sberbank.android.school.lessons.asynchronous;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.admin.lesson5.R;
import ru.sberbank.android.school.lessons.fragment.FragmentThree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ColorsLoader extends AsyncTaskLoader<List<Integer>> {
    private int quantity;

    public ColorsLoader(@NonNull Context context, Bundle bundle) {
        super(context);
        quantity = Math.abs(bundle.getInt(FragmentThree.COLORS_QUANTITY));
    }

    @Nullable
    @Override
    public List<Integer> loadInBackground() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getRandomColors();
    }

    public List<Integer> getRandomColors() {
        int[] colors = getContext().getResources().getIntArray(R.array.colors);
        int size = quantity % colors.length;
        List<Integer> randomColors = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            int randomIndex = new Random().nextInt(colors.length);
            randomColors.add(colors[randomIndex]);
        }

        return randomColors;
    }
}
