package com.example.skyler.softcalendar.uiredo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.skyler.softcalendar.MakeCalendarEvent;
import com.example.skyler.softcalendar.R;

public class HourEventsForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_events_form);

        Button createEvent = (Button) findViewById(R.id.buttonCreateHourEvent);
        createEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMakeEvent();
            }
        });}

    private void goToMakeEvent(){
        Intent intent = new Intent(this, MakeCalendarEvent.class);
        startActivity(intent);
    }
    }

