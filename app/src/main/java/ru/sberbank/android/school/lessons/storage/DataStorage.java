package ru.sberbank.android.school.lessons.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class DataStorage {
    private static final String PREF_NAME = "PREF";
    private static final String TEXT_SIZE_KEY = "TEXT_SIZE";
    private static final String COLOR_KEY = "COLOR";
    private Context context;

    public DataStorage(Context context) {
        this.context = context;
    }

    public float getTextSize() {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getFloat(TEXT_SIZE_KEY, 16);
    }

    public void saveTextSize(float size) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putFloat(TEXT_SIZE_KEY, size);
        editor.commit();
    }

    public int getColor() {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(COLOR_KEY, context.getResources().getColor(android.R.color.primary_text_light));
    }

    public void saveColor(int color) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(COLOR_KEY, color);
        editor.commit();
    }
}

