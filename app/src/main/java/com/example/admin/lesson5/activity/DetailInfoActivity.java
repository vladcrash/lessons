package com.example.admin.lesson5.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.admin.lesson5.App;
import com.example.admin.lesson5.db.dao.HourDao;
import com.example.admin.lesson5.R;
import com.example.admin.lesson5.model.Hour;

import java.util.ArrayList;
import java.util.List;

public class DetailInfoActivity extends AppCompatActivity {

    private static final String ID = "com.example.admin.lesson5.activity.DetailInfoActivity.ID";

    private int position;

    private TableLayout tableLayout;
    private List<Hour> hours = new ArrayList<>();

    public static final Intent newIntent(Context context, Integer forecastId) {
        Intent intent = new Intent(context, DetailInfoActivity.class);
        intent.putExtra(ID, forecastId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Integer forecastId = getIntent().getIntExtra(ID, 0);
        new LoadHoursTask().execute(forecastId);
    }

    private View createTable() {
        tableLayout = new TableLayout(this);
        tableLayout.setLayoutParams(new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        tableLayout.setStretchAllColumns(true);
        initRows();
        return tableLayout;
    }

    private void initRows() {
        for (int i = 0; i < 6; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < 4; j++) {
                Hour hour = getHour();
                LayoutInflater inflater = LayoutInflater.from(this);
                View rowItem = inflater.inflate(R.layout.table_item_detail_info, tableLayout, false);

                TextView time = rowItem.findViewById(R.id.time);
                TextView tempAvg = rowItem.findViewById(R.id.temp_avg);
                TextView feelsLike = rowItem.findViewById(R.id.feels_like);

                time.setText(hour.getHour());
                tempAvg.setText(hour.getTemp());
                feelsLike.setText(hour.getFeelsLike());

                tableRow.addView(rowItem);
            }
            tableLayout.addView(tableRow);
        }
    }

    private Hour getHour() {
        return hours.get(position++);
    }

    private class LoadHoursTask extends AsyncTask<Integer, Void, List<Hour>> {

        @Override
        protected List<Hour> doInBackground(Integer... integers) {
            int forecastId = integers[0];
            HourDao hourDao = App.getDatabase().hourDao();
            return hourDao.getHoursByForecastId(forecastId);
        }

        @Override
        protected void onPostExecute(List<Hour> hours) {
            Log.wtf("DetailInfoActivity", "" + hours.size());
            DetailInfoActivity.this.hours.addAll(hours);
            setContentView(createTable());
        }
    }
}
