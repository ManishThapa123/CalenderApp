package com.manish.calenderactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button buttonAlarm;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    private static ArrayList<Alarm> addAlarms;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewAdapter = new RecyclerViewAdapter();

        if (null != addAlarms){
            addAlarms = new ArrayList<>();

        }


        buttonAlarm = findViewById(R.id.btnNewAlarm);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter.setAlarms(addAlarms);

        buttonAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AlarmActivity.class);
                startActivity(intent);

            }
        });

        try {
            Intent intent = getIntent();
//            Bundle bundle = intent.getBundleExtra(getString(R.string.bundle));
            Bundle bundle = intent.getBundleExtra("bundle");
            if (null != bundle){
                int hours = bundle.getInt(getString(R.string.hours),-1);
                int minutes = bundle.getInt(getString(R.string.minutes),-1);
                if (hours != -1){
                    if (minutes != -1){
                        Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                        alarmIntent.putExtra(AlarmClock.EXTRA_HOUR,hours);
                        alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES,minutes);
                        alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE,"Hello from the other side");

                        Alarm alarm = new Alarm(hours,minutes,"Hello");

                        addAlarms.add(alarm);
                        recyclerViewAdapter.setAlarms(addAlarms);

                        startActivity(alarmIntent);
                    }
                }
            }else {
                Log.d(TAG, "onCreate: bundle is null");
            }

        }catch (Exception e){
            Log.d(TAG, "onCreate: something Happened");
            e.printStackTrace();
        }
//
//
//        Calendar calendar = Calendar.getInstance();
////        Log.d(TAG, "onCreate: Year: " + calendar.get(calendar.YEAR));
//        Log.d(TAG, "onCreate: Year: " + calendar.get(calendar.MONTH));
    }
}
