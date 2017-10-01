package com.example.skyler.softcalendar;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Event;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;


public class GoogleCalendarCreator extends AppCompatActivity {
    com.google.api.services.calendar.Calendar.Events events =null;
    Event event = new Event();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_calendar_creator);

        final int x = getIntent().getIntExtra("i", 0);

        String minute = String.format("%02d",CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.MINUTE));
        String hour  = String.format("%02d",CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.HOUR_OF_DAY));
        String day = String.format("%02d", CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.DAY_OF_MONTH));
        String month = String.format("%02d",CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.MONTH));
        String second = String.format("%02d", CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.SECOND));

        String datemaker =  CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.YEAR) + "-"+
        month+"-"+
        day +
        "T"+
        hour +":"+
        minute+":"+
        second+""+
        "+00:00";

        String endDateMaker = CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.YEAR) + "-"+
        month+"-"+
        day +
        "T"+
        hour +":"+
        minute+":"+
        second+""+
        "+00:00";

        DateTime calendarAdd = DateTime.parseRfc3339(datemaker);
        DateTime calendarEnd = DateTime.parseRfc3339(endDateMaker);


        Button addToCalendar = (Button) findViewById(R.id.addToCalendarButton);
        addToCalendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                goToMainForm();
            }
        });

        Button cancel = (Button) findViewById(R.id.CancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMainForm();
            }
        });
        Button delete = (Button) findViewById(R.id.DeleteButton);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CalendarEventManager.removeCalendarObject(x);
                goToMainForm();
            }
        });

        Calendar getCalendar = CalendarEventManager.calendars.get(x).getCalendar();

        TextView dateBox = (TextView) findViewById(R.id.dateBox);
        dateBox.setText((getCalendar.get(Calendar.MONTH)+" / "+
                         getCalendar.get(Calendar.DAY_OF_MONTH) + " / " +
                         getCalendar.get(Calendar.YEAR)));
        TextView timeBox = (TextView) findViewById(R.id.timeBox);
        timeBox.setText((getCalendar.get(Calendar.HOUR)+ ":"+
                         minute));
        TextView notesBox = (TextView) findViewById(R.id.notesBox);
        notesBox.setText(CalendarEventManager.calendars.get(x).getNotes() + datemaker+"");

        events = MainActivity.getmService().events();


        DateTime startDateTime = new DateTime("2017-10-01T09:00:00-07:00");
        DateTime endDateTime = new DateTime("2017-10-01T17:00:00-07:00");


        event = new Event()
                .setLocation("Dhaka")
                .setDescription("New Event 1")
                .setSummary("Google I/O 2015");

        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Los_Angeles");

        event.setStart(start);
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Los_Angeles");
        String[] recurrence = new String[] {"RRULE:FREQ=NEVER;COUNT=0"};
        event.setRecurrence(Arrays.asList(recurrence));
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(true);
        event.setReminders(reminders);
        event.setEnd(end);



        class TestAsync extends AsyncTask<Void, Integer, String>{
            @Override
            protected String doInBackground(Void... params) {
                try {
                    events.insert("primary", event).execute();
                }catch(IOException e){
                }
                return null;
            }
        }
        new TestAsync().execute();
    }






    public void goToMainForm(){
        Intent intent = new Intent (this, MainForm.class);
        this.startActivity(intent);
    }
}
