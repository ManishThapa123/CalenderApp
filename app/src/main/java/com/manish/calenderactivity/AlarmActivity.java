package com.manish.calenderactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlarmActivity extends AppCompatActivity {
    private static final String TAG = "AlarmActivity";
    private Button btnAddAlarm;
    private EditText editTextHour, editTextMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        btnAddAlarm = findViewById(R.id.btnAddAlarmNew);
        editTextHour = findViewById(R.id.edtTxtHour);
        editTextMinutes = findViewById(R.id.edtTxtMinutes);

        btnAddAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewAlarm();
            }
        });
    }
    private void addNewAlarm(){
        Log.d(TAG, "addNewAlarm: Alarm Started");
        //receive input and save in integer.
        int hours = Integer.valueOf(editTextHour.getText().toString());
        int minutes = Integer.valueOf(editTextMinutes.getText().toString());

        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.hours),hours);
        bundle.putInt(getString(R.string.minutes),minutes);

        Intent intent = new Intent(AlarmActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("bundle",bundle);
        startActivity(intent);
    }
}
