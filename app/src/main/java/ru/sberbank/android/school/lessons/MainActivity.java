package ru.sberbank.android.school.lessons;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    public static final String EXTRA_TEXT = "extra_text";

    private TextView textView;
    private Button screenButton1;
    private Button screenButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListeners();
    }

    private void init() {
        textView = findViewById(R.id.textView);
        screenButton1 = findViewById(R.id.button);
        screenButton2 = findViewById(R.id.button2);
    }

    private void setListeners() {
        screenButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ScreenOneActivity.newIntent(MainActivity.this, textView.getText().toString());
                startActivity(intent);
            }
        });

        screenButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ScreenTwoActivity.newIntent(MainActivity.this, textView.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    textView.setText(data.getStringExtra(EXTRA_TEXT));
                }
            }
        }
    }
}
