package com.example.skyler.softcalendar.uiredo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.skyler.softcalendar.CardViewAdapter;
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

       // mAdapter = new CardViewAdapter(data);
        mRecyclerView.setAdapter(mAdapter);


    }
}
