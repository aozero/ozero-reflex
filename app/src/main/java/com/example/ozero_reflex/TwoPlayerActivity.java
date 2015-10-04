package com.example.ozero_reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//import android.view.Menu;
//import android.view.MenuItem;

public class TwoPlayerActivity extends AppCompatActivity {

    private boolean buttonWasTapped = false;

    private Button playerOneButton;
    private Button playerTwoButton;

    // For statistics saving
    private static final String FILENAME = "twoPlayerStats.sav";
    Stats stats = new Stats();

    Handler waitH = new Handler();
    Runnable waitRPlayer1 = new Runnable() {
        @Override
        public void run() {
            stats.addBuzzerStat(0);
            saveInFile();
            buildMessageDialog("Player 1 tapped first!");
        }
    };
    Runnable waitRPlayer2 = new Runnable() {
        @Override
        public void run() {
            stats.addBuzzerStat(1);
            saveInFile();
            buildMessageDialog("Player 2 tapped first!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

        playerOneButton = (Button) findViewById(R.id.button6);
        playerTwoButton = (Button) findViewById(R.id.button7);
        // Load statistics file
        loadFromFile();

        initializeListeners();
    }

    private void buildMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TwoPlayerActivity.this);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dialog_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        buttonWasTapped = false;
                    }
                });
        builder.show();
    }

    private void buttonTap(Runnable runnable) {
        if (!buttonWasTapped) {
            buttonWasTapped = true;
            // Delay the dialog by half a second to prevent closing it accidentally
            waitH.postDelayed(runnable, 500);
        }
    }

    private void initializeListeners() {
        playerOneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonTap(waitRPlayer1);
            }
        });
        playerTwoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonTap(waitRPlayer2);
            }
        });
    }

    // From CMPUT 301 Lab 3
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            stats = gson.fromJson(in, Stats.class);
        } catch (FileNotFoundException e) {
            stats = new Stats();
            stats.createBuzzerStat(2);
            saveInFile();
        } /* catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } */
    }

    // From CMPUT 301 Lab 3
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(stats, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_two_player, menu);
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
