package ru.sberbank.android.school.lessons.weather;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import ru.sberbank.android.school.lessons.domain.model.Forecast;

public class WeatherListBindings {

    @BindingAdapter({"adapter", "data"})
    public static void bind(RecyclerView recyclerView, WeatherAdapter adapter, ObservableField<List<Forecast>> forecasts) {
        recyclerView.setAdapter(adapter);
        adapter.setForecasts(forecasts.get());
    }
}
