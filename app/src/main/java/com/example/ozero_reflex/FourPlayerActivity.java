package com.example.ozero_reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.view.Menu;
//import android.view.MenuItem;

public class FourPlayerActivity extends AppCompatActivity {
    private boolean buttonWasTapped = false;

    private Button playerOneButton;
    private Button playerTwoButton;
    private Button playerThreeButton;
    private Button playerFourButton;

    Handler waitH = new Handler();
    Runnable waitRPlayer1 = new Runnable() {
        @Override
        public void run() {
            buildMessageDialog("Player 1 tapped first!");
        }
    };
    Runnable waitRPlayer2 = new Runnable() {
        @Override
        public void run() {
            buildMessageDialog("Player 2 tapped first!");
        }
    };
    Runnable waitRPlayer3 = new Runnable() {
        @Override
        public void run() {
            buildMessageDialog("Player 3 tapped first!");
        }
    };
    Runnable waitRPlayer4 = new Runnable() {
        @Override
        public void run() {
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

    private void initializeListeners() {
        playerOneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!buttonWasTapped) {
                    buttonWasTapped = true;
                    // Delay the dialog by half a second to prevent closing it accidentally
                    waitH.postDelayed(waitRPlayer1, 500);
                }
            }
        });
        playerTwoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!buttonWasTapped) {
                    buttonWasTapped = true;
                    // Delay the dialog by half a second to prevent closing it accidentally
                    waitH.postDelayed(waitRPlayer2, 500);
                }
            }
        });
        playerThreeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!buttonWasTapped) {
                    buttonWasTapped = true;
                    // Delay the dialog by half a second to prevent closing it accidentally
                    waitH.postDelayed(waitRPlayer3, 500);
                }
            }
        });
        playerFourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!buttonWasTapped) {
                    buttonWasTapped = true;
                    // Delay the dialog by half a second to prevent closing it accidentally
                    waitH.postDelayed(waitRPlayer4, 500);
                }
            }
        });
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_four_player, menu);
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
