package com.example.skyler.softcalendar;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.skyler.softcalendar.uiredo.calendarEventsForm;

public class MakeCalendarEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_calendar_event2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button cancel = (Button) findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                goBack();
            }
            });

        final TextView startTime = (TextView) findViewById(R.id.textViewStartTime);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                DialogFragment pickTime = timePickerFragment;
                pickTime.show(getFragmentManager(),"TimePicker");
                timePickerFragment.setEndStart(true);
            }
        });
        TextView startDate = (TextView) findViewById(R.id.textViewStartDate);

        TextView endTime = (TextView) findViewById(R.id.textViewEndTime);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                DialogFragment pickTime = timePickerFragment;
                pickTime.show(getFragmentManager(),"TimePicker");
                timePickerFragment.setEndStart(false);
            }
        });
        TextView endDate = (TextView) findViewById(R.id.textViewEndDate);
    }



    private void goBack(){
        Intent intent = new Intent(this, calendarEventsForm.class);
        startActivity(intent);
    }
}
