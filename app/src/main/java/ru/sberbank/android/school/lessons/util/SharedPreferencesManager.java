package ru.sberbank.android.school.lessons.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesManager {
    public static final String TEXT_STORAGE = "TEXT_STORAGE";
    public static final String TEXT = "TEXT";

    public static void write(Context context, String text) {
        SharedPreferences.Editor editor = context.getSharedPreferences(TEXT_STORAGE, MODE_PRIVATE).edit();
        editor.putString(TEXT, text);
        editor.commit();
    }

    public static String read(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(TEXT_STORAGE, MODE_PRIVATE);
        return preferences.getString(TEXT, "Oops");
    }
}
