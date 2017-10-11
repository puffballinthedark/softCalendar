package com.example.skyler.softcalendar;

import java.util.ArrayList;

public class EventAggregator {
    public static ArrayList<Object> items = new ArrayList<Object>();

    public static void additem(Object x){
        items.add(x);
    }
    public static void removeitem(int x){
        items.remove(x);
    }

}
