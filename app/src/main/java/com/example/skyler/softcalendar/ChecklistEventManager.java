package com.example.skyler.softcalendar;

import java.util.ArrayList;

//TODO: explain why you're using an array of strings instead of an array of checklist objects,
//and if you're doign taht for a reason, why do checklist objects exist at all????
public class ChecklistEventManager {
    public static ArrayList<ChecklistObject> checklists = new ArrayList<>();

    public static void addChecklistEvent (ChecklistObject x){
        checklists.add(x);
    }
    public static void removeChecklistEvent (int x){checklists.remove(x);}
    public static void setChecklists(ArrayList<ChecklistObject> newChecklists){
        checklists.clear();
        checklists = new ArrayList<>(newChecklists);
    }
    public static String getType(){
        return "ChecklistEvent";
    }


}
