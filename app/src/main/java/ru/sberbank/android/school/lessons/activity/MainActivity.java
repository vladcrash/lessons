package ru.sberbank.android.school.lessons.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import ru.sberbank.android.school.lessons.App;
import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.adapter.WeatherAdapter;
import ru.sberbank.android.school.lessons.db.dao.ForecastDao;
import ru.sberbank.android.school.lessons.model.Forecast;
import ru.sberbank.android.school.lessons.service.ForecastService;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String FORECASTS_SAVED = "ru.sberbank.android.school.lessons.activity.MainActivity.FORECASTS_SAVED";

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private WeatherAdapter adapter;
    private ForecastReceiver receiver;
    private IntentFilter intentFilter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getForecasts();
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

    private void init() {
        recyclerView = findViewById(R.id.weather_list);
        refreshLayout = findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        adapter = new WeatherAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnClickItemListener(new WeatherAdapter.OnClickItemListener() {
            @Override
            public void onClick(Integer forecastId) {
                Intent intent = DetailInfoActivity.newIntent(MainActivity.this, forecastId);
                startActivity(intent);
            }
        });
        handler = new Handler();
        receiver = new ForecastReceiver();
        intentFilter = new IntentFilter(FORECASTS_SAVED);
    }

    private void getForecasts() {
        final ForecastDao forecastDao = App.getDatabase().forecastDao();
        App.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                List<Forecast> forecasts = forecastDao.getAll();
                if (forecasts != null && forecasts.size() > 0) {
                    setForecastList(forecasts);
                    Log.wtf("MainActivity", "db");
                } else {
                    startService(ForecastService.newIntent(MainActivity.this));
                    Log.wtf("MainActivity", "network");
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        startService(ForecastService.newIntent(this));
    }

    private void setForecastList(final List<Forecast> forecasts) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter.setForecasts(forecasts);
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void setForecastsAsync() {
        App.getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ForecastDao forecastDao = App.getDatabase().forecastDao();
                List<Forecast> forecasts = forecastDao.getAll();
                setForecastList(forecasts);
                Log.wtf("MainActivity", "setForecastsAsync network");
            }
        });
    }

    public class ForecastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess = intent.getBooleanExtra(ForecastService.IS_SUCCESS, false);
            if (isSuccess) {
                setForecastsAsync();
            }
        }
    }
}
