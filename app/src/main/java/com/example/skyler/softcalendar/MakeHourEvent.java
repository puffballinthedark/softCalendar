package com.example.skyler.softcalendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.skyler.softcalendar.uiredo.calendarEventsForm;
import com.example.skyler.softcalendar.uiredo.hourEventsForm;
import com.google.api.client.util.DateTime;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.Locale;


public class MakeHourEvent extends AppCompatActivity implements View.OnClickListener{

    private TextView time, date;
    private EditText title, notes;
    private Button cancel, save;
    private NumberPicker eventLength;
    private String TimeString, DateString, EndTimeString;

    private int mYear, mMonth, mDay, mHour, mMinute;

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

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        time.setText(mHour + ":" + mMinute);

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        date.setText(mDay + "-" + mMonth + "-" + mYear);

        String cleanDay = String.format(Locale.getDefault(),"%02d", mDay);
        String cleanMonth = String.format(Locale.getDefault(),"%02d", mMonth+1);
        String cleanHour = String.format(Locale.getDefault(),"%02d", mHour);
        String cleanMinute = String.format(Locale.getDefault(),"%02d", mMinute);

        TimeString = cleanHour + ":" + cleanMinute + ":" + "00" + "+00:00";
        DateString = mYear + "-" + cleanMonth + "-" + cleanDay + "T";

        time.setOnClickListener(this);
        date.setOnClickListener(this);
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v == time) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            //TODO: you need to make sure that the start time and date aren't greater than the end time
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String cleanHour = String.format(Locale.getDefault(),"%02d", hourOfDay);
                            String cleanMinute = String.format(Locale.getDefault(),"%02d", minute);

                            TimeString = cleanHour + ":" + cleanMinute + ":" + "00" + "+00:00";
                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();}
        if (v == date){

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
                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            String cleanDay = String.format(Locale.getDefault(),"%02d", dayOfMonth);
                            String cleanMonth = String.format(Locale.getDefault(),"%02d", monthOfYear+1);

                            DateString = year + "-" + cleanMonth + "-" + cleanDay + "T";
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == save){
            //TODO: you have to be super sure that the thing doesn't make a time that's over 13 hours.
            int hourTime = Integer.parseInt(TimeString.substring(0,2));
            String EventLengthHour = String.format(Locale.getDefault(),"%02d", hourTime+ eventLength.getValue());
            String endTime = TimeString.replace(TimeString.substring(0,2), EventLengthHour);

            calendar.setTitle(title.getText().toString());
            calendar.setNotes(notes.getText().toString());
            calendar.setStart(DateTime.parseRfc3339(DateString + TimeString));
            calendar.setEnd(DateTime.parseRfc3339(DateString + endTime));
            HourEventManager.addCalendarObject(calendar);
            goBack();
        }
        if (v == cancel){
            goBack();
        }

    }

    private void goBack(){
        Intent intent = new Intent(this, hourEventsForm.class);
        startActivity(intent);
    }
}
