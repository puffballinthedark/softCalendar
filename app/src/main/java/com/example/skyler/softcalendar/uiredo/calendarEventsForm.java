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

import com.example.skyler.softcalendar.CalendarEventManager;
import com.example.skyler.softcalendar.CardViewAdapter;
import com.example.skyler.softcalendar.MakeCalendarEvent;
import com.example.skyler.softcalendar.R;

public class calendarEventsForm extends AppCompatActivity{
    private static Context mContext;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_events_form);
        mContext = getApplicationContext();


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewCalendarEvent);

        mAdapter = new CardViewAdapter(mContext,CalendarEventManager.calendars);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();


        Button createEvent = (Button) findViewById(R.id.buttonCreateEvent);
        createEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMakeEvent();
            }
        });
    }


    private void goToMakeEvent(){
        Intent intent = new Intent(this, MakeCalendarEvent.class);
        startActivity(intent);
    }
    }


