package ru.sberbank.android.school.lessons.weather;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.sberbank.android.school.lessons.R;
import ru.sberbank.android.school.lessons.domain.model.Forecast;
import ru.sberbank.android.school.lessons.databinding.ListItemWeatherBinding;

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
        holder.setData(forecast);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull WeatherViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull WeatherViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
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
        private ListItemWeatherBinding binding;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            bind();
        }

        public void setData(Forecast forecast) {
            forecastId = forecast.getId();
            setViewModel(new WeatherItemViewModel(forecast));
        }

        public void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        public void unbind() {
            if (binding != null) {
                binding.unbind();
            }
        }

        public void setViewModel(WeatherItemViewModel viewModel) {
            if (binding != null) {
                binding.setViewModel(viewModel);
            }
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onClick(forecastId);
        }
    }
}