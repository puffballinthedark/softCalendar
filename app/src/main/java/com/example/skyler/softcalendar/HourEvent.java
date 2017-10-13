package com.example.skyler.softcalendar;

import com.google.api.client.util.DateTime;


public class HourEvent {
    private String title = "";
    private String notes = "";
    private DateTime start;
    private DateTime end;
    private java.util.Calendar calendar;
    private int Position;
    //TODO: you need to name calendar something that isn't fucking miserable oh my GOD


    public DateTime getStart(){
        return start;
    }
    public DateTime getEnd(){return end;}
    public String getNotes(){
        return notes;
    }
    public String getTitle(){
        return title;
    }
    public java.util.Calendar getCalendar(){return calendar;}
    public int getPosition(){
        return Position;
    }

    public void setStart(DateTime x){
        start = x;
    }
    public void setEnd (DateTime x) {end = x;}
    public void setNotes(String x){
        notes = x;
    }
    public void setTitle(String x){
        title = x;
    }
    public void setCalendar (java.util.Calendar x) {calendar = x;}
    public void setPosition(int x){
        Position = x;
    }
}

