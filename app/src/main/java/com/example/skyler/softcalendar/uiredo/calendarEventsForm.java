package com.example.skyler.softcalendar.uiredo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.skyler.softcalendar.MakeCalendarEvent;
import com.example.skyler.softcalendar.R;

public class calendarEventsForm extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_events_form);

        RecyclerView rv = (RecyclerView)findViewById(R.id.recyclerViewCalendarEvent);
        rv.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(mLayoutManager);


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


