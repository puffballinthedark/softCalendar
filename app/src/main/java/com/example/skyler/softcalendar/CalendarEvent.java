package com.example.skyler.softcalendar;
import android.icu.util.Calendar;

import com.google.api.client.util.DateTime;
import java.io.Serializable;

public class CalendarEvent implements Serializable {
    private String title = "";
    private String notes = "";
    private DateTime start;
    private java.util.Calendar calendar;
    //TODO: you need to name calendar something that isn't fucking miserable oh my GOD



    public DateTime getStart(){
        return start;
    }
    public String getNotes(){
        return notes;
    }
    public String getTitle(){
        return title;
    }
    public java.util.Calendar getCalendar(){return calendar;}

    public void setStart(DateTime x){
        start = x;
    }
    public void setNotes(String x){
        notes = x;
    }
    public void setTitle(String x){
        title = x;
    }
    public void setCalendar (java.util.Calendar x) {calendar = x;}

}
