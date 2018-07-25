package ru.sberbank.android.school.lessons.ui.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ru.sberbank.android.school.lessons.App;
import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.data.ForecastRepository;
import ru.sberbank.android.school.lessons.databinding.ActivityWeatherBinding;
import ru.sberbank.android.school.lessons.ui.weatherdetail.WeatherDetailActivity;
import ru.sberbank.android.school.lessons.data.db.dao.ForecastDao;
import ru.sberbank.android.school.lessons.data.model.Forecast;
import ru.sberbank.android.school.lessons.service.ForecastService;

import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    public static final String FORECASTS_SAVED = "ru.sberbank.android.school.lessons.ui.weather.WeatherActivity.FORECASTS_SAVED";

    private ForecastReceiver receiver;
    private IntentFilter intentFilter;
    private WeatherViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        bind();
    }

    private void init() {
        receiver = new ForecastReceiver();
        intentFilter = new IntentFilter(FORECASTS_SAVED);
    }

    private void bind() {
        ActivityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        ForecastRepository repository = new ForecastRepository(this);
        viewModel = new WeatherViewModel(repository);
        viewModel.setOnItemClickListener(new WeatherAdapter.OnItemClickListener() {
            @Override
            public void onClick(Integer forecastId) {
                Intent intent = WeatherDetailActivity.newIntent(WeatherActivity.this, forecastId);
                startActivity(intent);
            }
        });
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
        viewModel.populateData();
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
