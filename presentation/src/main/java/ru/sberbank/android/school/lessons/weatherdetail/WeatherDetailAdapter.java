package ru.sberbank.android.school.lessons.weatherdetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.domain.model.Hour;

public class WeatherDetailAdapter extends RecyclerView.Adapter<WeatherDetailAdapter.DetailInfoViewHolder> {

    private List<Hour> hours;

    @NonNull
    @Override
    public DetailInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new DetailInfoViewHolder(inflater.inflate(R.layout.list_item_weather_detail,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailInfoViewHolder holder, int position) {
        Hour hour = hours.get(position);
        holder.bind(hour);
    }

    @Override
    public int getItemCount() {
        return hours != null ? hours.size() : 0;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
        notifyDataSetChanged();
    }

    public class DetailInfoViewHolder extends RecyclerView.ViewHolder {

        private WeakReference<Context> context;
        private TextView hour;
        private TextView temperature;
        private TextView feelsLikeTemperature;

        public DetailInfoViewHolder(View itemView) {
            super(itemView);
            context = new WeakReference<>(itemView.getContext());
            hour = itemView.findViewById(R.id.hour);
            temperature = itemView.findViewById(R.id.temp_avg);
            feelsLikeTemperature = itemView.findViewById(R.id.feels_like);
        }

        public void bind(Hour hour) {
            this.hour.setText(hour.getHour());
            temperature.setText(getFormattedValue(hour.getTemp()));
            feelsLikeTemperature.setText(getFormattedValue(hour.getFeelsLike()));
        }

        private String getFormattedValue(int value) {
            return context.get().getResources().getString(R.string.temperature_value, value);
        }
    }
}
