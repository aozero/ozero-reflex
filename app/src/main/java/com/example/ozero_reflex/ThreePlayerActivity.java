package com.example.ozero_reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ThreePlayerActivity extends AppCompatActivity {

    private boolean buttonWasTapped = false;

    private Button playerOneButton;
    private Button playerTwoButton;
    private Button playerThreeButton;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_player);

        playerOneButton = (Button) findViewById(R.id.button8);
        playerTwoButton = (Button) findViewById(R.id.button9);
        playerThreeButton = (Button) findViewById(R.id.button10);

        initializeListeners();
    }

    private void buildMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ThreePlayerActivity.this);
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
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_three_player, menu);
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
