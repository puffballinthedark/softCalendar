package com.example.skyler.softcalendar;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.widget.TextView;
import android.widget.DatePicker;
import android.app.Dialog;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    boolean endStart = true;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //Create a new DatePickerDialog instance and return it
        /*
            DatePickerDialog Public Constructors - Here we uses first one
            public DatePickerDialog (Context context, DatePickerDialog.OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth)
            public DatePickerDialog (Context context, int theme, DatePickerDialog.OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth)
         */
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        if (endStart){
            TextView dateTextView = (TextView) getActivity().findViewById(R.id.textViewStartDate);
            dateTextView.setText(String.valueOf(year) + "/" +String.valueOf(month)+ "/" + String.valueOf(day));
        }
        else{
            TextView dateTextView = (TextView) getActivity().findViewById(R.id.textViewEndDate);
            dateTextView.setText(String.valueOf(year) + "/" +String.valueOf(month)+ "/" + String.valueOf(day));
        }
    }
    public void setEndStart(boolean x){
        endStart = x;
    }
}