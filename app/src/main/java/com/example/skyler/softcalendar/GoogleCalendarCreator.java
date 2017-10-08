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
import com.google.api.services.calendar.model.EventReminder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;


public class GoogleCalendarCreator extends AppCompatActivity {
    com.google.api.services.calendar.Calendar.Events events = null;

    private class backgroundTask extends AsyncTask<Integer, Void, Void>{
        @Override
        protected Void doInBackground(Integer... x){
            makeBetterEvent(x[0]);
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int x = getIntent().getIntExtra("i", 0);

        setContentView(R.layout.activity_google_calendar_creator);


        Button addToCalendar = (Button) findViewById(R.id.addToCalendarButton);
        addToCalendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                backgroundTask calendarAdd = new backgroundTask();
                calendarAdd.execute((Integer)x);
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
    }

    public void goToMainForm(){
        Intent intent = new Intent (this, MainForm.class);
        this.startActivity(intent);
    }

    private void makeBetterEvent(int x){
        events = MainActivity.getmService().events();


        String minute = String.format(Locale.getDefault(),"%02d",CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.MINUTE));
        String hour  = String.format(Locale.getDefault(),"%02d",CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.HOUR_OF_DAY));
        String day = String.format(Locale.getDefault(),"%02d", CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.DAY_OF_MONTH));
        String month = String.format(Locale.getDefault(),"%02d",CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.MONTH));
        String second = String.format(Locale.getDefault(),"%02d", CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.SECOND));

        String datemaker =  CalendarEventManager.calendars.get(x).getCalendar().get(Calendar.YEAR) + "-"+
                month+"-"+
                day +
                "T"+
                hour +":"+
                minute+":"+
                second+""+
                "+00:00";


        Event event = new Event()
                .setSummary("oh christ")
                .setDescription(CalendarEventManager.calendars.get(x).getNotes());
        DateTime calendarStart = DateTime.parseRfc3339(datemaker);
        EventDateTime start = new EventDateTime()
                .setDateTime(calendarStart)
                //TODO: you need to do something about the timezones
                .setTimeZone("America/Los_Angeles");

        event.setStart(start);

                //TODO: you need to add an end time
        DateTime calendarEnd= new DateTime("2018-05-28T17:00:00-07:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(calendarEnd)
                .setTimeZone("America/Los_Angeles");

        event.setEnd(end);

        String[] recurrence = new String[]{"RRULE:FREQ=DAILY;COUNT=1"};

        event.setRecurrence(Arrays.asList(recurrence));

        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("popup").setMinutes(10),
        };

        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        String calendarId = "primary";

        try {
            events.insert(calendarId, event).execute();
        } catch (IOException e) {
        }

/*

        //DateTime calendarEnd = DateTime.parseRfc3339(endDateMaker);

        Calendar getCalendar = CalendarEventManager.calendars.get(x).getCalendar();

        TextView dateBox = (TextView) findViewById(R.id.dateBox);
        dateBox.setText((getCalendar.get(Calendar.MONTH)+" / "+
                getCalendar.get(Calendar.DAY_OF_MONTH) + " / " +
                getCalendar.get(Calendar.YEAR)));
        TextView timeBox = (TextView) findViewById(R.id.timeBox);
        timeBox.setText((getCalendar.get(Calendar.HOUR)+ ":"+
                minute));
        TextView notesBox = (TextView) findViewById(R.id.notesBox);
        notesBox.setText(CalendarEventManager.calendars.get(x).getNotes() + datemaker+""); */

    }



}
