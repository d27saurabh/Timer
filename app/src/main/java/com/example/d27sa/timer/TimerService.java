package com.example.d27sa.timer;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by d27sa on 28-02-2017.
 */

public class TimerService extends Service {

    Intent intent;
    public static final String BROADCAST_ACTION = "com.example.d27sa.timer";
    private final Handler manager = new Handler(); //handles the data to braodcast

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timer timer = new Timer();
       // timer.scheduleAtFixedRate(dothis ,0,5000);
        manager.postDelayed(gap, 0); // handler called starting with no delay
        return START_STICKY; // process is running in the background
    }

    TimerTask dothis = new TimerTask() {
        @Override
        public void run() {
            toastHandler.sendEmptyMessage(0); //calls the toast handler

        }
    };

    private Runnable gap = new Runnable() {
        public void run(){
            displayTimer();
            manager.postDelayed(this, 5000); // % sec delay provided
           toastHandler.sendEmptyMessage(0);
        }
    };



    /*@Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
    }
*/
   private final Handler toastHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            Toast.makeText(getApplicationContext(), "+5", Toast.LENGTH_SHORT).show(); // to taost +5 at every update
        }
    };

    public void displayTimer(){
        intent =new Intent(this,MainActivity.class);
    SimpleDateFormat getVal = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());
    // gets the default time format and passes to the send broadcast
    intent.putExtra("timeNow",getVal.format(System.currentTimeMillis()));
    sendBroadcast(intent);// registered broadcast gets the intent
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
