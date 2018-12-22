package com.example.jeremy_ssd.appsbergdesigntest;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CircularProgressBar circularProgressBar = findViewById(R.id.circularProgress);
        CircularProgressBar circularProgressBar2 = findViewById(R.id.circularProgress2);
        circularProgressBar.setColor(ContextCompat.getColor(this, R.color.colorTime));
        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackGroundTime));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.default_stroke_width));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.default_background_stroke_width));
        circularProgressBar2.setColor(ContextCompat.getColor(this, R.color.colorCO2));
        circularProgressBar2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackGroundCO2));
        circularProgressBar2.setProgressBarWidth(getResources().getDimension(R.dimen.default_stroke_width));
        circularProgressBar2.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.default_background_stroke_width));
        int animationDuration = 2500; // 2500ms = 2,5s
        circularProgressBar.setProgressWithAnimation(85, animationDuration); // Default duration = 1500ms
        circularProgressBar2.setProgressWithAnimation(65, animationDuration); // Default duration = 1500ms

        LineChart chart = findViewById(R.id.chart);
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(1, 10));
        entries.add(new Entry(2, 5));
        entries.add(new Entry(3, 7));
        entries.add(new Entry(4, 14));
        entries.add(new Entry(5, 11));
        entries.add(new Entry(6, 10));
        entries.add(new Entry(7, 8));
        LineDataSet dataSet = new LineDataSet(entries, "Label");
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }
}
