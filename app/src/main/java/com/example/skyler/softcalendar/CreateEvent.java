package com.example.skyler.softcalendar;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.api.client.util.DateTime;

public class CreateEvent extends AppCompatActivity {
    CalendarEvent calendar = new CalendarEvent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                goToMainForm();
            }
        });
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){

                DatePicker date = (DatePicker) findViewById(R.id.datePicker);

                java.util.Calendar dateMaker = java.util.Calendar.getInstance();

                dateMaker.set( date.getYear(), date.getMonth(), date.getDayOfMonth(), 0, 0, 0);
                calendar.setCalendar(dateMaker);
                goToChooseTime();
            }
        });

    }

    private void goToChooseTime(){
        Intent intent = new Intent (this, ChooseTime.class);
        intent.putExtra("calendarObject", calendar);
        startActivity(intent);
    }
    private void goToMainForm(){
        Intent intent = new Intent(this, MainForm.class);
        startActivity(intent);
    }


}

