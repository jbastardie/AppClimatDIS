package com.example.jeremy_ssd.appsbergdesigntest;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();


        ArrayList<AppModel> dataList=
                (ArrayList<AppModel>)bundle.getSerializable("DataDaily");
        ListView mListView = findViewById(R.id.pkg_list_solo);
        AppAdapter adapter = new AppAdapter(ListActivity.this, dataList);
        mListView.setAdapter(adapter);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
