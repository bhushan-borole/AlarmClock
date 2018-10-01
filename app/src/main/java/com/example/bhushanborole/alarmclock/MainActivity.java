package com.example.bhushanborole.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarm_manager;
    TimePicker time_picker;
    TextView txt_view;
    Context context;
    Button btn_set;
    Button btn_unset;

    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;

        //initialize alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //initialize our timepicker
        time_picker = findViewById(R.id.time_picker);

        btn_set = findViewById(R.id.btn_set);
        btn_unset = findViewById(R.id.btn_unset);
        txt_view = findViewById(R.id.txt_view);
        final Calendar calendar = Calendar.getInstance();

        //create intent to my receiver class
        final Intent intent = new Intent(this.context,Alarm_Receiver.class);

        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setting calendar instance with time we picked from alarm picker.
                calendar.set(Calendar.HOUR_OF_DAY, time_picker.getHour());
                calendar.set(Calendar.MINUTE, time_picker.getMinute());

                int hour = time_picker.getHour();
                int minute = time_picker.getMinute();
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if(hour>12)
                    hour_string = String.valueOf(hour-12);
                if(minute<10)
                    minute_string = "0"+minute_string;


                String text = "Alarm set to "+hour_string+" : "+minute_string;
                txt_view.setText(text);

                //put an extra string to tell clock that you pressed "alarm on" button
                intent.putExtra("extra", "alarm on");

                //create an intent that delays the intent until specific time is reached.
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                //startActivity(intent);
                //set the alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            }
        });
        btn_unset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = "Alarm Off!";
                txt_view.setText(txt);

                alarm_manager.cancel(pendingIntent);
                intent.putExtra("extra", "alarm off");
                sendBroadcast(intent);

            }
        });



    }

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
    }


}
