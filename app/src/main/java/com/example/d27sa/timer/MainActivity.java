package com.example.d27sa.timer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent (this,TimerService.class);

    }

    @Override
    public void onResume(){
        super.onResume();
        startService(intent);    //intent to switch to service
        registerReceiver(macron,new IntentFilter(TimerService.BROADCAST_ACTION));// receives intent from the given path
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(macron);// to unregister the receiver from getting data from service specified
        stopService(intent);
    }

    public BroadcastReceiver macron = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) { // as the received intent is lost it is passed to display
            display(intent);
        }
    };

//    public void StopService(View view)
//    {
//        Intent intent = new Intent (this,TimerService.class);
//        stopService(intent);
//    }


    public void display (Intent intent)
    {
        String time = intent.getStringExtra("timeNow");
        System.out.println(time);
        TextView txtvw = (TextView) findViewById(R.id.textView);// to update the time in the textview
        txtvw.setText(time);
    }


}
