package com.example.skyler.softcalendar;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;



import java.util.Calendar;

public class ChooseTime extends AppCompatActivity {
    private CalendarEvent calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);
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

                TimePicker eventTime = (TimePicker) findViewById(R.id.timePicker);

                /*eventTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        System.out.println(hourOfDay+"");
                    }
                });*/




                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    calendar = (CalendarEvent) getIntent().getSerializableExtra("calendarObject");
                }

                //are these actually outputting 12h format??/
                int hour = eventTime.getHour();
                int minute = eventTime.getMinute();

                calendar.getCalendar().set(Calendar.HOUR_OF_DAY,hour);
                calendar.getCalendar().set(Calendar.MINUTE, minute);

                goToChooseName();
            }
        });
    }



    private void goToChooseName(){
        Intent intent = new Intent (this, ChooseName.class);
        intent.putExtra("calendarObject", calendar);
        startActivity(intent);
    }
    private void goToMainForm(){
        Intent intent = new Intent(this, MainForm.class);
        startActivity(intent);
    }

}

