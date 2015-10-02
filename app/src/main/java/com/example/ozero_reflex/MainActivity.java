package com.example.ozero_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startOnePlayer(View view)
    {
        Intent onePlayerIntent = new Intent(this, OnePlayerActivity.class);
        startActivity(onePlayerIntent);
    }

    public void startTwoPlayer(View view)
    {
        Intent twoPlayerIntent = new Intent(this, TwoPlayerActivity.class);
        startActivity(twoPlayerIntent);
    }

    public void startThreePlayer(View view)
    {
        Intent threePlayerIntent = new Intent(this, ThreePlayerActivity.class);
        startActivity(threePlayerIntent);
    }

    public void startFourPlayer(View view)
    {
        Intent fourPlayerIntent = new Intent(this, FourPlayerActivity.class);
        startActivity(fourPlayerIntent);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
