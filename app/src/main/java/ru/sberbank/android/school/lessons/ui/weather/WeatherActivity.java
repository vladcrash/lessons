package ru.sberbank.android.school.lessons.ui.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.data.ForecastRepository;
import ru.sberbank.android.school.lessons.databinding.ActivityWeatherBinding;
import ru.sberbank.android.school.lessons.service.ForecastService;
import ru.sberbank.android.school.lessons.ui.weatherdetail.WeatherDetailActivity;

public class WeatherActivity extends AppCompatActivity {

    public static final String FORECASTS_SAVED = "ru.sberbank.android.school.lessons.ui.weather.WeatherActivity.FORECASTS_SAVED";

    private ForecastReceiver receiver;
    private IntentFilter intentFilter;
    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
        init();
    }

    private void init() {
        receiver = new ForecastReceiver();
        intentFilter = new IntentFilter(FORECASTS_SAVED);
        viewModel.populateData();
    }

    private void bind() {
        ActivityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        ForecastRepository repository = new ForecastRepository(this);
        WeatherAdapter adapter = new WeatherAdapter();
        adapter.setOnItemClickListener(new WeatherAdapter.OnItemClickListener() {
            @Override
            public void onClick(Integer forecastId) {
                Intent intent = WeatherDetailActivity.newIntent(WeatherActivity.this, forecastId);
                startActivity(intent);
            }
        });
        viewModel = new WeatherViewModel(repository, adapter);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    public class ForecastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess = intent.getBooleanExtra(ForecastService.IS_SUCCESS, false);
            if (isSuccess) {
                viewModel.setForecasts();
            }
        }
    }
}
