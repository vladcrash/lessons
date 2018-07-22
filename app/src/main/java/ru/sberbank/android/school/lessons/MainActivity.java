package ru.sberbank.android.school.lessons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button serviceButton;
    private Button secondActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListeners();
    }

    private void init() {
        serviceButton = findViewById(R.id.service_button);
        secondActivityButton = findViewById(R.id.second_activity_button);
    }

    private void setListeners() {
        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = ServiceInfoGenerator.newIntent(MainActivity.this);
                startService(intent);
            }
        });

        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SecondActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }
}
