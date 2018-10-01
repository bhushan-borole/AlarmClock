package com.example.bhushanborole.alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("receiver works","Yayy");

        //get string
        String get_string = intent.getExtras().getString("extra");
        Log.e("What is your key?", get_string);
        //intent to ringtone playing service
        Intent service_intent = new Intent(context, RingtonePlayingService.class);
        //pass the extra string from MainActivity to RingtonePlayingService class
        service_intent.putExtra("extra", get_string);
        //start ringtone service
        context.startService(service_intent);
    }
}
