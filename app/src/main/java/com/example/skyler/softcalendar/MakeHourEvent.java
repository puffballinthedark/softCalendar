package com.example.skyler.softcalendar;

import android.icu.util.Calendar;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.travijuu.numberpicker.library.NumberPicker;

import org.w3c.dom.Text;

public class MakeHourEvent extends AppCompatActivity implements View.OnClickListener{

    private TextView time, date;
    private EditText title, notes;
    private Button cancel, save;
    private NumberPicker eventLength;

    CalendarEvent calendar = new CalendarEvent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_hour_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Calendar c = Calendar.getInstance();
        cancel = (Button) findViewById(R.id.buttonCancel);
        save = (Button) findViewById(R.id.buttonSave);

        time = (TextView) findViewById(R.id.textViewStartTime);
        date = (TextView) findViewById(R.id.textViewStartDate);
        title = (EditText) findViewById(R.id.editTextTitle);
        notes = (EditText) findViewById(R.id.editTextNotes);
        eventLength = (NumberPicker) findViewById(R.id.number_picker);



    }
    @Override
    public void onClick(View v) {}
}
