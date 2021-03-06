package com.example.skyler.softcalendar.uiredo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.skyler.softcalendar.HourEventAdapter;
import com.example.skyler.softcalendar.HourEventManager;
import com.example.skyler.softcalendar.MakeHourEvent;
import com.example.skyler.softcalendar.R;

public class hourEventsForm extends AppCompatActivity {
    private static Context mContext;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_events_form);
        mContext = getApplicationContext();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewHourEvents);

        mAdapter = new HourEventAdapter(mContext, HourEventManager.calendars);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();


        Button createEvent = (Button) findViewById(R.id.buttonCreateHourEvent);
        createEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToMakeEvent();
            }
        });}

    private void goToMakeEvent(){
        Intent intent = new Intent(this, MakeHourEvent.class);
        startActivity(intent);
    }
    }

