package com.example.skyler.softcalendar;

import java.util.ArrayList;

/**
 * Created by Skyler on 10/10/2017.
 */

public class ChecklistEventManager {    public static ArrayList<String> checklists = new ArrayList<String>();

    public static void addChecklistEvent (String x){
        checklists.add(x);
    }
    public static void removeChecklistEvent (int x){checklists.remove(x);}

}
