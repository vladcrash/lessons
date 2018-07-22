package ru.sberbank.android.school.lessons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ScreenOneActivity extends AppCompatActivity {
    public static final String TEXT_EXTRA = "text_extra";

    private TextView textView;

    public static final Intent newIntent(Context context, String text) {
        Intent intent = new Intent(context, ScreenOneActivity.class);
        intent.putExtra(TEXT_EXTRA, text);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_one);
        init();
    }

    private void init() {
        textView = findViewById(R.id.textView2);
        textView.setText(getIntent().getStringExtra(TEXT_EXTRA));
    }
}