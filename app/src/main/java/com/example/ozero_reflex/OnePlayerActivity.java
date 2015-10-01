package com.example.ozero_reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
//import android.view.Menu;
//import android.view.MenuItem;


public class OnePlayerActivity extends AppCompatActivity {

    private long startTime;
    private long reactionTime;
    private boolean timerStarted = false;
    // To assist with onPause() and onResume()
    private boolean wasPaused = false;
    private boolean messageDismissed = false;

    static final int minTime = 10;
    static final int maxTime = 2000;

    private Button button;
    private TextView text;

    // Handlers and Runnables:
    // https://stackoverflow.com/questions/4597690/android-timer-how 2015-09-27
    // User: Dave.B
    Handler timerH = new Handler();
    Runnable timerR = new Runnable() {

        @Override
        public void run() {
            startTime = System.currentTimeMillis();
            text.setText(R.string.reaction_timer_now);
            timerStarted = true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
        text = (TextView) findViewById(R.id.reactionText);
        button = (Button) findViewById(R.id.reactionButton);
        //Bring up the instructions
        buildMessageDialog("When the text changes, tap as quickly as you can. " +
                "As you close this dialog, the timer will begin!");
        //Start the game/test
        initializeListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        timerH.removeCallbacks(timerR);
        wasPaused = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasPaused) {
            if (messageDismissed) {
                buildMessageDialog("When the text changes, tap as quickly as you can. " +
                        "As you close this dialog, the timer will begin!");
                text.setText(R.string.reaction_timer_wait);
                //timerH.postDelayed(timerR, randomTime());
                //wasPaused = false;
            } else {
                // Let things resume as they were
            }
        }
    }

    private void buildMessageDialog(String message) {
        messageDismissed = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(OnePlayerActivity.this);
        builder.setCancelable(false);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.one_player_dialog_ok,
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                messageDismissed = true;
                text.setText(R.string.reaction_timer_wait);
                timerH.postDelayed(timerR, randomTime());
            }
        });
        builder.show();
    }

    // Pick a random time for the timer
    public int randomTime() {
        Random random = new Random();
        return random.nextInt(maxTime - minTime) + minTime;
    }

    private void initializeListener() {
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (timerStarted) {
                    reactionTime = System.currentTimeMillis() - startTime;
                    timerStarted = false;
                    buildMessageDialog("Your reaction time is " + String.valueOf(reactionTime) + "ms");
                }
                else {
                    timerH.removeCallbacks(timerR);
                    text.setText(R.string.reaction_timer_wait);
                    buildMessageDialog("Wait for the text to change! Timer reset.");
                }
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_one_player, menu);
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
    }*/
}
