package com.example.skyler.softcalendar;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.skyler.softcalendar.uiredo.calendarEventsForm;
import com.google.api.client.util.DateTime;

import java.util.Locale;

public class MakeCalendarEvent extends AppCompatActivity implements View.OnClickListener{

    private int mYear, mMonth, mDay, mHour, mMinute;
    private TextView startTime, startDate, endTime, endDate;
    private EditText title, notes;
    private Button cancel, save;

    private String StartDate, EndDate;
    private String StartTime, EndTime;

    CalendarEvent calendar = new CalendarEvent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_calendar_event2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Calendar c = Calendar.getInstance();

        cancel = (Button) findViewById(R.id.buttonCancel);
        save = (Button) findViewById(R.id.buttonSave);

        startTime = (TextView) findViewById(R.id.textViewStartTime);
        startDate = (TextView) findViewById(R.id.textViewStartDate);
        endTime = (TextView) findViewById(R.id.textViewEndTime);
        endDate = (TextView) findViewById(R.id.textViewEndDate);
        title = (EditText) findViewById(R.id.editTextTitle);
        notes = (EditText) findViewById(R.id.editTextNotes);

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        startTime.setText(mHour + ":" + mMinute);
        endTime.setText(mHour + ":" + mMinute);

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        startDate.setText(mDay + "-" + mMonth + "-" + mYear);
        endDate.setText(mDay + "-" + mMonth + "-" + mYear);

        String cleanDay = String.format(Locale.getDefault(),"%02d", mDay);
        String cleanMonth = String.format(Locale.getDefault(),"%02d", mMonth+1);
        String cleanHour = String.format(Locale.getDefault(),"%02d", mHour);
        String cleanMinute = String.format(Locale.getDefault(),"%02d", mMinute);

        StartTime = cleanHour + ":" + cleanMinute + ":" + "00" + "+00:00";
        StartDate = mYear + "-" + cleanMonth + "-" + cleanDay + "T";
        EndTime = cleanHour + ":" + cleanMinute + ":" + "00" + "+00:00";
        EndDate = mYear + "-" + cleanMonth + "-" + cleanDay + "T";

        startTime.setOnClickListener(this);
        startDate.setOnClickListener(this);
        endTime.setOnClickListener(this);
        endDate.setOnClickListener(this);
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == startTime) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String cleanHour = String.format(Locale.getDefault(),"%02d", hourOfDay);
                            String cleanMinute = String.format(Locale.getDefault(),"%02d", minute);

                            StartTime = cleanHour + ":" + cleanMinute + ":" + "00" + "+00:00";
                            startTime.setText(hourOfDay + ":" + minute);


                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == endTime){
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            endTime.setText(hourOfDay + ":" + minute);
                            //TODO: I think the +00:00 is the time offset, which you need to get eventually.
                            String cleanHour = String.format(Locale.getDefault(),"%02d", hourOfDay);
                            String cleanMinute = String.format(Locale.getDefault(),"%02d", minute);

                            EndTime = cleanHour + ":" + cleanMinute + ":" + "00" + "+00:00";
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();

        }
        if (v == startDate){

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            startDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            String cleanDay = String.format(Locale.getDefault(),"%02d", dayOfMonth);
                            String cleanMonth = String.format(Locale.getDefault(),"%02d", monthOfYear+1);

                            StartDate = year + "-" + cleanMonth + "-" + cleanDay + "T";
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == endDate){

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            endDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            String cleanDay = String.format(Locale.getDefault(),"%02d", dayOfMonth);
                            String cleanMonth = String.format(Locale.getDefault(),"%02d", monthOfYear +1);
                            EndDate = ( year + "-" + cleanMonth + "-" + cleanDay + "T");
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if (v == save){
            calendar.setTitle(title.getText().toString());
            calendar.setNotes(notes.getText().toString());
            calendar.setStart(DateTime.parseRfc3339(StartDate + StartTime));
            calendar.setEnd(DateTime.parseRfc3339(EndDate + EndTime));
            CalendarEventManager.addCalendarObject(calendar);
            goBack();
        }
        if (v == cancel){
            goBack();
        }
    }


    private void goBack(){
        Intent intent = new Intent(this, calendarEventsForm.class);
        startActivity(intent);
    }
}
