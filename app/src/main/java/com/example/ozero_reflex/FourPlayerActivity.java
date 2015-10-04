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

public class FourPlayerActivity extends AppCompatActivity {

    private boolean buttonWasTapped = false;

    private Button playerOneButton;
    private Button playerTwoButton;
    private Button playerThreeButton;
    private Button playerFourButton;

    // For statistics saving
    private static final String FILENAME = "stats.sav";
    Stats stats = new Stats();

    Handler waitH = new Handler();
    Runnable waitRPlayer1 = new Runnable() {
        @Override
        public void run() {
            stats.addFourPlayerStat(0);
            saveInFile();
            buildMessageDialog("Player 1 tapped first!");
        }
    };
    Runnable waitRPlayer2 = new Runnable() {
        @Override
        public void run() {
            stats.addFourPlayerStat(1);
            saveInFile();
            buildMessageDialog("Player 2 tapped first!");
        }
    };
    Runnable waitRPlayer3 = new Runnable() {
        @Override
        public void run() {
            stats.addFourPlayerStat(2);
            saveInFile();
            buildMessageDialog("Player 3 tapped first!");
        }
    };
    Runnable waitRPlayer4 = new Runnable() {
        @Override
        public void run() {
            stats.addFourPlayerStat(3);
            saveInFile();
            buildMessageDialog("Player 4 tapped first!");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_player);

        playerOneButton = (Button) findViewById(R.id.button11);
        playerTwoButton = (Button) findViewById(R.id.button12);
        playerThreeButton = (Button) findViewById(R.id.button13);
        playerFourButton = (Button) findViewById(R.id.button14);

        // Load statistics file
        loadFromFile();

        initializeListeners();
    }

    private void buildMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FourPlayerActivity.this);
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
        playerThreeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonTap(waitRPlayer3);
            }
        });
        playerFourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonTap(waitRPlayer4);
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
            //stats.createBuzzerStat(4);
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
}
