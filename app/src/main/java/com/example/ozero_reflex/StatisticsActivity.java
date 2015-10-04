package com.example.ozero_reflex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.view.Menu;
//import android.view.MenuItem;

public class StatisticsActivity extends AppCompatActivity {

    private ArrayAdapter<String> statsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ListView listView = (ListView) findViewById(R.id.listView);

        ReactionTest test = new ReactionTest(StatisticsActivity.this);
        statsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                test.getStats().getStatsStrings());
        listView.setAdapter(statsAdapter);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
