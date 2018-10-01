package com.example.bhushanborole.alarmclock;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class RingtonePlayingService extends Service {
    MediaPlayer media_song;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("in the service", "startCommand");

        //create an instance of the media player
        media_song = MediaPlayer.create(this, R.raw.koyal);
        media_song.start();

        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "on destroy method called", Toast.LENGTH_SHORT).show();
    }
}
