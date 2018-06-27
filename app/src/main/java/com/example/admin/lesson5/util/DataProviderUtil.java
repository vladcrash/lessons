package com.example.admin.lesson5.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.admin.lesson5.R;

import java.util.Random;

public class DataProviderUtil {

    public static int[] getRandomColors(@NonNull Context context, int count) {
        int[] colors = context.getResources().getIntArray(R.array.colors);
        int[] randomColors = new int[count];

        for (int i = 0; i < randomColors.length; i++) {
            int randomIndex = new Random().nextInt(colors.length);
            randomColors[i] = colors[randomIndex];
        }

        return randomColors;
    }

    public static String[] getRandomWords(@NonNull Context context, int count) {
        String[] lyrics = context.getResources().getStringArray(R.array.words);
        String[] randomWords = new String[count];

        for (int i = 0; i < randomWords.length; i++) {
            int randomIndex = new Random().nextInt(lyrics.length);
            randomWords[i] = lyrics[randomIndex];
        }

        return randomWords;
    }
}
