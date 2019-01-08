package com.example.jeremy_ssd.appsbergdesigntest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
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

        final String[] quarters = new String[] { "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim" };

        CombinedChart chart = findViewById(R.id.chart);
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

        };

        List<BarEntry> entries = new ArrayList<BarEntry>();
        entries.add(new BarEntry(0, 10));
        entries.add(new BarEntry(1, 5));
        entries.add(new BarEntry(2, 7));
        entries.add(new BarEntry(3, 14));
        entries.add(new BarEntry(4, 11));
        entries.add(new BarEntry(5, 10));
        entries.add(new BarEntry(6, 8));
        BarDataSet dataSet = new BarDataSet(entries, "Label");

        List<Entry> entriesLine = new ArrayList<Entry>();
        entriesLine.add(new Entry(0, 100));
        entriesLine.add(new Entry(1, 50));
        entriesLine.add(new Entry(2, 70));
        entriesLine.add(new Entry(3, 140));
        entriesLine.add(new Entry(4, 110));
        entriesLine.add(new Entry(5, 100));
        entriesLine.add(new Entry(6, 80));
        LineDataSet dataSetLine = new LineDataSet(entriesLine, "Label");
        LineData lineData = new LineData(dataSetLine);

        CombinedData data = new CombinedData();
        BarData barData = new BarData(dataSet);

        dataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
        dataSetLine.setAxisDependency(YAxis.AxisDependency.LEFT);
        data.setData(barData);
        data.setData(lineData);
        chart.setData(data);
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
        chart.invalidate(); // refresh

        String timeTotalText = "<font color='#3da1a3'><big><b>0H 00M</b></font></big><small>/1H 30M</small>";
        String tempsUtilisation = "Temps Utilisation";
        TextView textViewTime = findViewById(R.id.textViewResumeTime);
        textViewTime.setText(Html.fromHtml(timeTotalText + "<br>" + tempsUtilisation));

        String co2TotalText = "<font color='#a5dad2'><big><b> 0g CO2</b></font></big><small>/20g</small>";
        String emissionCO2 = "Emission CO2";
        TextView textViewCO2 = findViewById(R.id.textViewResumeCO2);
        textViewCO2.setText(Html.fromHtml(co2TotalText + "<br>" + emissionCO2));

        String timeTotalTextWeek = "<font color='#3da1a3'><big><b>10H 24M</b></font></big>";
        String tempsUtilisationWeek = "Temps Utilisation";
        TextView textViewTimeWeek = findViewById(R.id.textViewResumeTimeWeek);
        textViewTimeWeek.setText(Html.fromHtml(timeTotalTextWeek + "<br>" + tempsUtilisationWeek));

        String co2TotalTextWeek = "<font color='#a5dad2'><big><b> 62g CO2</b></font></big>";
        String emissionCO2Week = "Emission CO2";
        TextView textViewCO2Week = findViewById(R.id.textViewResumeCO2Week);
        textViewCO2Week.setText(Html.fromHtml(co2TotalTextWeek + "<br>" + emissionCO2Week));

        String textTips ="<font color='#3da1a3'><big><b>Le saviez-vous?</b></font></big><br>Les data centers où sont stockées  vos données sont responsables de 2% des émissions de CO2, soit autant que la flotte aérienne mondiale";
        TextView textViewAdvice = findViewById(R.id.textViewAdvice);
        textViewAdvice.setText(Html.fromHtml(textTips));

        List<AppModel> tweets = genererTweets();
        ListView mListView = findViewById(R.id.pkg_list);
        AppAdapter adapter = new AppAdapter(MainActivity.this, tweets);
        mListView.setAdapter(adapter);
        mListView.setEnabled(false);

        TextView textViewComparatif = findViewById(R.id.textview_equivalence_co2);

        String texEquivalenceAmpoule = "<br>· une ampoule allumée pendant <font color='#a5dad2'><big><b>78</b></font></big> heures";
        String texEquivalenceVoiture = "<br>· <font color='#a5dad2'><big><b>7 km</b></font></big> en voiture citadine";
        String textEquivalence = "<font color='#a5dad2'><big><b> 62g de CO2</b></font></big> équivaut à:"+texEquivalenceAmpoule+texEquivalenceVoiture;
        textViewComparatif.setText(Html.fromHtml(textEquivalence));

        startCountAnimation();
    }

    private void startCountAnimation() {
        final int valueCO2 = 17;
        final int valueTime = 87;
        final String timeString = convertAffichageHour(valueTime);
        final ValueAnimator animatorCO2 = ValueAnimator.ofInt(0, valueCO2);
        final ValueAnimator animatorTime = ValueAnimator.ofInt(0, valueTime);
        animatorTime.setDuration(2000);
        animatorCO2.setDuration(1000);
        final Button todayUsageButton = findViewById(R.id.buttonTodayActivity);
        final String tempsUtilisation = "Temps Utilisation";
        final TextView textViewTime = findViewById(R.id.textViewResumeTime);
        final String emissionCO2 = "Emission CO2";
        final TextView textViewCO2 = findViewById(R.id.textViewResumeCO2);
        CircularProgressBar circularProgressBar = findViewById(R.id.circularProgress);
        circularProgressBar.setProgressWithAnimation(85, 2000);
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
                circularProgressBar2.setProgressWithAnimation(65, 1000);
            }
        });
        animatorTime.start();
    }

    protected String convertAffichageHour(int minutes) {
        int resultMinutes = minutes % 60;
        int resultHour = minutes / 60;
        return resultHour + "H " + String.format(Locale.FRANCE,"%02d", resultMinutes) + "M";
    }

    private ArrayList<AppModel> genererTweets(){
        ArrayList<AppModel> tweets = new ArrayList<>();
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Facebook", getResources().getIdentifier("facebook", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Instagram", getResources().getIdentifier("instagram", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Youtube", getResources().getIdentifier("youtube", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Chrome", getResources().getIdentifier("leafecology", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Snapchat", getResources().getIdentifier("pingouinlite", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Facebook", getResources().getIdentifier("facebook", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Instagram", getResources().getIdentifier("instagram", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Youtube", getResources().getIdentifier("youtube", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Chrome", getResources().getIdentifier("leafecology", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Snapchat", getResources().getIdentifier("pingouinlite", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Facebook", getResources().getIdentifier("facebook", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Instagram", getResources().getIdentifier("instagram", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Youtube", getResources().getIdentifier("youtube", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Chrome", getResources().getIdentifier("leafecology", "drawable", getPackageName())));
        tweets.add(new AppModel("02:12:45", "visites: 47", "25g CO2", "Snapchat", getResources().getIdentifier("pingouinlite", "drawable", getPackageName())));
//        tweets.add(new AppModel(Color.BLUE, "Kevin", "C'est ici que ça se passe !"));
//        tweets.add(new AppModel(Color.GREEN, "Logan", "Que c'est beau..."));
//        tweets.add(new AppModel(Color.RED, "Mathieu", "Il est quelle heure ??"));
//        tweets.add(new AppModel(Color.GRAY, "Willy", "On y est presque"));
        return tweets;
    }

    public void goToListActivity(View view){
        Intent intent = new Intent(this, ListActivity.class);
        ArrayList<AppModel> tweets = genererTweets();
        intent.putExtra("DataDaily", tweets);
        startActivity(intent);
    }
}
