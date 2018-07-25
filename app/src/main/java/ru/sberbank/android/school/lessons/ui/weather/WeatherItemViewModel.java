package ru.sberbank.android.school.lessons.ui.weather;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import ru.sberbank.android.school.lessons.data.model.Forecast;

public class WeatherItemViewModel {

    private ObservableField<String> date = new ObservableField<>();
    private ObservableInt temperature = new ObservableInt();
    private ObservableInt feelsLike = new ObservableInt();

    public WeatherItemViewModel(Forecast forecast) {
        date.set(forecast.getDate());
        temperature.set(forecast.getParts().getDay().getTemp());
        feelsLike.set(forecast.getParts().getDay().getFeelsLike());
    }

    public ObservableField<String> getDate() {
        return date;
    }

    public ObservableInt getTemperature() {
        return temperature;
    }

    public ObservableInt getFeelsLike() {
        return feelsLike;
    }
}
