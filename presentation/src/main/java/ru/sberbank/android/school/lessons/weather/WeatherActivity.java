package ru.sberbank.android.school.lessons.weather;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import ru.sberbank.android.school.lessons.App;
import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.databinding.ActivityWeatherBinding;

public class WeatherActivity extends AppCompatActivity {

    @Inject
    WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
        init();
        load();
    }

    private void setup() {
        App.get(this)
            .plusWeatherComponent()
            .inject(this);
    }

    private void load() {
        viewModel.populateData();
    }

    private void init() {
        ActivityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.get(this).releaseWeatherComponent();
    }
}
