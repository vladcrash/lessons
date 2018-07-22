package ru.sberbank.android.school.lessons.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.storage.DataStorage;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerView;

public class SettingsActivity extends AppCompatActivity {
    private EditText textSize;
    private ColorPickerView colorPickerView;
    private DataStorage dataStorage;
    private int color;

    public static final Intent newIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
        setListeners();
    }

    private void init() {
        dataStorage = new DataStorage(this);
        colorPickerView = findViewById(R.id.color_picker);
        textSize = findViewById(R.id.input_text_size);
        textSize.setText(String.valueOf(dataStorage.getTextSize()));
    }

    private void setListeners() {
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(ColorEnvelope colorEnvelope) {
                int[] colorRGB = colorEnvelope.getColorRGB();
                color = Color.rgb(colorRGB[0], colorRGB[1], colorRGB[2]);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                dataStorage.saveColor(color);
                dataStorage.saveTextSize(Float.valueOf(textSize.getText().toString()));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
