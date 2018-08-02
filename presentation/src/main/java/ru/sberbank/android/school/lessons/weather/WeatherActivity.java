package ru.sberbank.android.school.lessons.weather;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.sberbank.android.school.lessons.MainThreadImpl;
import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.data.repository.ForecastRepositoryImpl;
import ru.sberbank.android.school.lessons.databinding.ActivityWeatherBinding;
import ru.sberbank.android.school.lessons.domain.executor.ThreadExecutor;
import ru.sberbank.android.school.lessons.weatherdetail.WeatherDetailActivity;

public class WeatherActivity extends AppCompatActivity {

    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
        init();
    }

    private void init() {
        viewModel.populateData();
    }

    private void bind() {
        ActivityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        ForecastRepositoryImpl repository = new ForecastRepositoryImpl();
        WeatherAdapter adapter = new WeatherAdapter();
        adapter.setOnItemClickListener(new WeatherAdapter.OnItemClickListener() {
            @Override
            public void onClick(Integer forecastId) {
                Intent intent = WeatherDetailActivity.newIntent(WeatherActivity.this, forecastId);
                startActivity(intent);
            }
        });

        viewModel = new WeatherViewModel(MainThreadImpl.getInstance(), ThreadExecutor.getInstance(),
                repository, adapter);
        binding.setViewModel(viewModel);
    }
}
