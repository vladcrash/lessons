package ru.sberbank.android.school.lessons.ui.weather;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.sberbank.android.school.lessons.data.model.Forecast;

public class WeatherListBindings {

    @BindingAdapter({"app:adapter", "app:data", "app:onItemClick"})
    public void bind(RecyclerView recyclerView, WeatherAdapter adapter, ObservableField<List<Forecast>> forecasts,
                     WeatherAdapter.OnItemClickListener listener) {
        recyclerView.setAdapter(adapter);
        adapter.setForecasts(forecasts.get());
        adapter.setOnItemClickListener(listener);
    }

}
