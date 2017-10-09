package com.example.skyler.softcalendar.uiredo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.skyler.softcalendar.CalendarAdapter;
import com.example.skyler.softcalendar.CalendarEventManager;
import com.example.skyler.softcalendar.CardViewAdapter;
import com.example.skyler.softcalendar.MakeCalendarEvent;
import com.example.skyler.softcalendar.R;

public class calendarEventsForm extends AppCompatActivity{

    RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_events_form);
        mContext = getApplicationContext();

        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayoutCalendarEvents);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewCalendarEvent);

        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CardViewAdapter(mContext,CalendarEventManager.calendars);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setVisibility(View.VISIBLE);

        setContentView(R.layout.activity_calendar_events_form);
        Button createEvent = (Button) findViewById(R.id.buttonCreateEvent);
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


