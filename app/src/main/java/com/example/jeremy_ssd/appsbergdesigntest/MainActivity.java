package com.example.jeremy_ssd.appsbergdesigntest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        String timeTotalText = "<font color='#3da1a3'><big><b>0H 00M</b></font></big><small>/1H 30M</small>";
        String tempsUtilisation = "Temps Utilisation";
        TextView textViewTime = findViewById(R.id.textViewResumeTime);
        textViewTime.setText(Html.fromHtml(timeTotalText + "<br>" + tempsUtilisation));

        String co2TotalText = "<font color='#a5dad2'><big><b> 0g CO2</b></font></big><small>/20g</small>";
        String emissionCO2 = "Emission CO2";
        TextView textViewCO2 = findViewById(R.id.textViewResumeCO2);
        textViewCO2.setText(Html.fromHtml(co2TotalText + "<br>" + emissionCO2));
        startCountAnimation();
    }

    private void startCountAnimation() {
        final int valueCO2 = 17;
        final int valueTime = 87;
        final String timeString = convertAffichageHour(valueTime);
        final ValueAnimator animatorCO2 = ValueAnimator.ofInt(0, valueCO2);
        final ValueAnimator animatorTime = ValueAnimator.ofInt(0, valueTime);
        animatorTime.setDuration(2500);
        animatorCO2.setDuration(2000);
        final Button todayUsageButton = findViewById(R.id.buttonTodayActivity);
        final String tempsUtilisation = "Temps Utilisation";
        final TextView textViewTime = findViewById(R.id.textViewResumeTime);
        final String emissionCO2 = "Emission CO2";
        final TextView textViewCO2 = findViewById(R.id.textViewResumeCO2);
        CircularProgressBar circularProgressBar = findViewById(R.id.circularProgress);
        circularProgressBar.setProgressWithAnimation(85, 2500);
        animatorCO2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                String stringCO2 = String.format(Locale.FRANCE,"%1$2s", animation.getAnimatedValue().toString());
                String buttonDaily = "<font color='#3da1a3'><big><b>" + timeString + "</b></font></big><br><font color='#a5dad2'><big><b>" + stringCO2 + "g CO2</b></font></big>";
                todayUsageButton.setText(Html.fromHtml(buttonDaily));
                String co2TotalText = "<font color='#a5dad2'><big><b></s>"+stringCO2+"g CO2</b></font></big><small>/20g</small>";
                textViewCO2.setText(Html.fromHtml(co2TotalText + "<br>" + emissionCO2));

            }
        });
        animatorTime.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                String convertedTime = convertAffichageHour(Integer.valueOf(animatorTime.getAnimatedValue().toString()));
                String buttonDaily = "<font color='#3da1a3'><big><b>" +convertedTime+ "</b></font></big><br><font color='#a5dad2'><big><b>0g CO2</b></font></big>";
                todayUsageButton.setText(Html.fromHtml(buttonDaily));
                String timeTotalText = "<font color='#3da1a3'><big><b>"+convertedTime+"</b></font></big><small>/1H 30M</small>";
                textViewTime.setText(Html.fromHtml(timeTotalText + "<br>" + tempsUtilisation));
            }
        });

        animatorTime.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorCO2.start();
                CircularProgressBar circularProgressBar2 = findViewById(R.id.circularProgress2);
                circularProgressBar2.setProgressWithAnimation(65, 2000);
            }
        });
        animatorTime.start();
    }

    protected String convertAffichageHour(int minutes) {
        int resultMinutes = minutes % 60;
        int resultHour = minutes / 60;
        return resultHour + "H " + String.format(Locale.FRANCE,"%02d", resultMinutes) + "M";
    }
}
