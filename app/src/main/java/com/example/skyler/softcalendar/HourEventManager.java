package com.example.skyler.softcalendar;

import java.util.ArrayList;

public class HourEventManager {
    public static ArrayList<HourEvent> calendars = new ArrayList<HourEvent>();

    public static void addCalendarObject(HourEvent x){calendars.add(x);
    }
    public static void removeCalendarObject(int x){
        calendars.remove(x);
    }

    public static void setCalendar(ArrayList<HourEvent> newCalendar){
        calendars.clear();
        calendars = new ArrayList<>(newCalendar);
    }
}