package com.example.admin.lesson5.asynchronous;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.admin.lesson5.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ColorLoader extends AsyncTaskLoader<Integer> {

    public ColorLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getRandomColor();
    }

    private int getRandomColor() {
        int[] colors = getContext().getResources().getIntArray(R.array.colors);
        int randomIndex = new Random().nextInt(colors.length);
        return colors[randomIndex];
    }
}
