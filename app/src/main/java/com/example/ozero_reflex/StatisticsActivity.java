package com.example.ozero_reflex;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
//import android.view.Menu;
//import android.view.MenuItem;

public class StatisticsActivity extends AppCompatActivity {

    Button emailButton;
    Button clearButton;

    ReactionTest test;
    ArrayAdapter<String> statsAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        listView = (ListView) findViewById(R.id.listView);
        emailButton = (Button) findViewById(R.id.button15);
        clearButton = (Button) findViewById(R.id.button16);

        test = new ReactionTest(StatisticsActivity.this);
        statsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, test.getStats().getStatsStrings());
        listView.setAdapter(statsAdapter);

        initializeListeners();
    }

    private void initializeListeners() {
        emailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:ozero@ualberta.ca"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Reflex Stats");
                intent.putExtra(Intent.EXTRA_TEXT, test.getStats().getStatsAsString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                test.clearStats();
                statsAdapter.clear();
                statsAdapter.addAll(test.getStats().getStatsStrings());
            }
        });
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
