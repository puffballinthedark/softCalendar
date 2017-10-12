package com.example.skyler.softcalendar;

import java.util.ArrayList;

public class HourEventManager {
    public static ArrayList<HourEvent> calendars = new ArrayList<HourEvent>();

    public static void addCalendarObject(HourEvent x){calendars.add(x);
    }
    public static void removeCalendarObject(int x){
        calendars.remove(x);
    }
}