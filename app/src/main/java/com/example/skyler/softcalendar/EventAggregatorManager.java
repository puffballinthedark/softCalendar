package com.example.skyler.softcalendar;

import java.util.ArrayList;

public class EventAggregatorManager {
    public static ArrayList<Object> items = new ArrayList<Object>();

    public static void additem(Object x){
        items.add(x);
    }
    public static void removeitem(int x){
        items.remove(x);
    }
    public static void setItems(ArrayList<Object> newArray){
        items.clear();
        items = new ArrayList<>(newArray);
    }

}
