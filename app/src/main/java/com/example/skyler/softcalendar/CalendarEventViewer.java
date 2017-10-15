package com.example.skyler.softcalendar;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.skyler.softcalendar.uiredo.calendarEventsForm;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CalendarEventViewer extends AppCompatActivity {
    CalendarEvent calendar;
    com.google.api.services.calendar.Calendar.Events events = null;

    private class backgroundTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... Void){
            makeEvent();
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event_viewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        events = MainActivity.getmService().events();


        Intent mIntent = getIntent();
        final int position = mIntent.getIntExtra("position", 0);


        //TODO: the hour event viewer isn't indexing properly.
        //it's looking for the index of the calendareventmanager instead of the houreventmanager
        //this is something I don't know why it is happening
        //also why it's not calling houreventviewer????
        //I need to figure out where it's calling this from.

        calendar = CalendarEventManager.calendars.get(position);

        TextView startTime = (TextView) findViewById(R.id.startTime);
        TextView endTime = (TextView) findViewById(R.id.endTime);
        TextView startDate = (TextView) findViewById(R.id.startDate);
        TextView endDate = (TextView) findViewById(R.id.endDate);
        TextView notes = (TextView) findViewById(R.id.notes);
        TextView title = (TextView) findViewById(R.id.title);

        title.setText(calendar.getTitle());
        notes.setText(calendar.getNotes());
        startTime.setText(calendar.getStart().toString().substring(11,16));
        endTime.setText(calendar.getEnd().toString().substring(11,16));
        startDate.setText(calendar.getStart().toString().substring(0,10));
        endDate.setText(calendar.getEnd().toString().substring(0,10));


      Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                if (CalendarEventManager.calendars.size()-1 != calendar.getPosition()) {
                    for (int i = position; i < CalendarEventManager.calendars.size(); i++){
                        CalendarEventManager.calendars.get(i).setPosition(i-1);
                    }
                }

                CalendarEventManager.removeCalendarObject(position);

                //TODO: this doesn't work either, you need to rethink your shit ok
                ArrayList<Object> calendarEventObjectArray = new ArrayList<Object>(CalendarEventManager.calendars);
                ArrayList<Object> hourEventObjectArray = new ArrayList<Object>(HourEventManager.calendars);
                ArrayList<Object> checklistEventObjectArray = new ArrayList<Object>(ChecklistEventManager.checklists);
                ArrayList<Object> allItems = new ArrayList<>();

                allItems.addAll(calendarEventObjectArray);
                allItems.addAll(hourEventObjectArray);
                allItems.addAll(checklistEventObjectArray);

                EventAggregatorManager.setItems(allItems);
                SharedPreferences.Editor ed = MainActivity.EventData.edit();
                Gson gson = new Gson();
                String calendarEvents = gson.toJson(CalendarEventManager.calendars);
                String aggregatedEvents = gson.toJson(EventAggregatorManager.items);

                ed.putString("SavedCalendarEvents", calendarEvents);
                ed.putString("SavedAggregatedEvents", aggregatedEvents);
                ed.apply();

                goback();
            }
        });
        Button addToCalendar = (Button) findViewById(R.id.addToCalendar);
        addToCalendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                backgroundTask calendarAdd = new backgroundTask();
                calendarAdd.execute();

                if (CalendarEventManager.calendars.size()-1 != calendar.getPosition()) {
                    for (int i = position; i < CalendarEventManager.calendars.size(); i++) {
                        CalendarEventManager.calendars.get(i).setPosition(i - 1);
                    }
                }
                CalendarEventManager.removeCalendarObject(position);

                ArrayList<Object> calendarEventObjectArray = new ArrayList<Object>(CalendarEventManager.calendars);
                ArrayList<Object> hourEventObjectArray = new ArrayList<Object>(HourEventManager.calendars);
                ArrayList<Object> checklistEventObjectArray = new ArrayList<Object>(ChecklistEventManager.checklists);
                ArrayList<Object> allItems = new ArrayList<Object>();

                allItems.addAll(calendarEventObjectArray);
                allItems.addAll(hourEventObjectArray);
                allItems.addAll(checklistEventObjectArray);

                EventAggregatorManager.setItems(allItems);
                SharedPreferences.Editor ed = MainActivity.EventData.edit();
                Gson gson = new Gson();
                String calendarEvents = gson.toJson(CalendarEventManager.calendars);
                String aggregatedEvents = gson.toJson(EventAggregatorManager.items);

                ed.putString("SavedCalendarEvents", calendarEvents);
                ed.putString("SavedAggregatedEvents", aggregatedEvents);
                ed.apply();

                goback();

            }
        });
    }
    private void goback(){
        Intent intent = new Intent(this, calendarEventsForm.class);
        startActivity(intent);
    }
    private void makeEvent(){
        Event event = new Event()
                .setSummary(calendar.getTitle())
                .setDescription(calendar.getNotes());

        EventDateTime start = new EventDateTime()
                .setDateTime(calendar.getStart())
                //TODO: you need to do something about the timezones
                .setTimeZone("America/Los_Angeles");
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(calendar.getEnd())
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
    }

    }


