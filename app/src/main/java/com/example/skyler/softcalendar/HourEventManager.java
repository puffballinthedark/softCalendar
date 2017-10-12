package com.example.skyler.softcalendar;

import java.util.ArrayList;

public class HourEventManager {
    public static ArrayList<CalendarEvent> calendars = new ArrayList<CalendarEvent>();

    public static void addCalendarObject(CalendarEvent x){calendars.add(x);
    }
    public static void removeCalendarObject(int x){
        calendars.remove(x);
    }
}