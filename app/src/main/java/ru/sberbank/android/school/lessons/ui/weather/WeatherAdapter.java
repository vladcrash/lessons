package ru.sberbank.android.school.lessons.ui.weather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.data.model.Forecast;

import java.lang.ref.WeakReference;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    public interface OnItemClickListener {
        void onClick(Integer forecastId);
    }

    private List<Forecast> forecasts;
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new WeatherViewHolder(inflater.inflate(R.layout.list_item_weather, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        Forecast forecast = forecasts.get(position);
        holder.bind(forecast);
    }

    @Override
    public int getItemCount() {
        return forecasts != null ? forecasts.size() : 0;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Integer forecastId;
        private TextView date;
        private TextView temperature;
        private TextView feelsLikeTemperature;
        private WeakReference<Context> context;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            context = new WeakReference<>(itemView.getContext());
            date = itemView.findViewById(R.id.date);
            temperature = itemView.findViewById(R.id.temperature_value);
            feelsLikeTemperature = itemView.findViewById(R.id.temperature_feels_like_value);
        }

        public void bind(Forecast forecast) {
            forecastId = forecast.getId();
            date.setText(forecast.getDate());
            temperature.setText(getFormattedValue(forecast.getParts().getDay().getTemp()));
            feelsLikeTemperature.setText(getFormattedValue(forecast.getParts().getDay().getFeelsLike()));
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(forecastId);
        }

        private String getFormattedValue(int value) {
            return context.get().getResources().getString(R.string.temperature_value, value);
        }
    }
}
