package com.example.skyler.softcalendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChooseName extends AppCompatActivity {
    private CalendarEvent calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                goToMainForm();
            }
        });

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    calendar = (CalendarEvent) getIntent().getSerializableExtra("calendarObject");
                }

                EditText Title = (EditText) findViewById(R.id.Title);
                EditText Notes = (EditText) findViewById(R.id.Notes);

                calendar.setTitle(Title.getText().toString());
                calendar.setNotes(Notes.getText().toString());

                save();
            }
        });

    }
    private void goToMainForm(){
        Intent intent = new Intent(this, MainForm.class);
        startActivity(intent);
    }
    private void save(){
        CalendarEventManager.addCalendarObject(calendar);
        goToMainForm();
    }

}
