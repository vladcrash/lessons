package ru.sberbank.android.school.lessons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class ScreenTwoActivity extends AppCompatActivity {
    public static final String TEXT_EXTRA = "text_extra";

    private EditText editText;

    public static final Intent newIntent(Context context, String text) {
        Intent intent = new Intent(context, ScreenTwoActivity.class);
        intent.putExtra(TEXT_EXTRA, text);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_two);
        init();
    }

    private void init() {
        editText = findViewById(R.id.edit_text);
        editText.setText(getIntent().getStringExtra(TEXT_EXTRA));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_TEXT, editText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}