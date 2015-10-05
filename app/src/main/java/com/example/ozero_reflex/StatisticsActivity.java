/*
    Copyright 2015 Alexander Ozero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

           http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
package com.example.ozero_reflex;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

// The UI to display the statistics collected by the reaction/buzzer modes. Allows emailing and
// clearing the statistics
public class StatisticsActivity extends AppCompatActivity {

    private Button emailButton;
    private Button clearButton;

    private ReactionTest test;
    private ArrayAdapter<String> statsAdapter;
    private ListView listView;

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
        // Send an email with the stats in clean, human-readable form in the body
        emailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:ozero@ualberta.ca"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Reflex Statistics");
                intent.putExtra(Intent.EXTRA_TEXT, test.getStats().getStatsAsString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
        // Clear the stats and update the adapter
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                test.clearStats();
                statsAdapter.clear();
                statsAdapter.addAll(test.getStats().getStatsStrings());
            }
        });
    }
}
