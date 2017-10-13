package com.example.skyler.softcalendar;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.skyler.softcalendar.uiredo.checklistForm;

import java.util.ArrayList;

public class MainFormElementsAadapter extends RecyclerView.Adapter<MainFormElementsAadapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Object> mDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View v){
            super(v);
            mTextView = (TextView)v.findViewById(R.id.tv);
        }
    }
    public MainFormElementsAadapter(Context context, ArrayList<Object> DataSet){
        mDataSet = DataSet;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        String dataSetName = mDataSet.get(position).getClass().getSimpleName();
        if(dataSetName.equals("CalendarEvent")){
            return -1;
        }else if (dataSetName.equals("HourEvent")){
            return -2;
        } else{
            return 0;}
    }
    @Override
    public MainFormElementsAadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (viewType <= -1){
            View v = LayoutInflater.from(mContext).inflate(R.layout.activity_custom_view,parent,false);
            MainFormElementsAadapter.ViewHolder vh = new MainFormElementsAadapter.ViewHolder(v);
            return vh;
        }else{
            View v = LayoutInflater.from(mContext).inflate(R.layout.activity_checklist_custom_view,parent,false);
            MainFormElementsAadapter.ViewHolder vh = new MainFormElementsAadapter.ViewHolder(v);
            return vh;
        }
    }
    @Override
    public void onBindViewHolder(MainFormElementsAadapter.ViewHolder holder, final int position){
        if (holder.getItemViewType() == -1){
            CalendarEvent title = (CalendarEvent)mDataSet.get(position);
            holder.mTextView.setText(title.getTitle());
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    goToCalendarEventViewer(position);
                }
            });
        } else if (holder.getItemViewType() == -2){
            HourEvent title = (HourEvent)mDataSet.get(position);
            holder.mTextView.setText(title.getTitle());
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    goToHourEventViewer(position);
                }
            });

        }
        else{
            ChecklistObject title = (ChecklistObject)mDataSet.get(position);
            holder.mTextView.setText(title.getName());
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    goToChecklistViewer();
                }
            });
        }

        //TODO: you need to set the custom view's textview attribute, add an onclicked listener to the thing, and call the appropriate form
    }
    @Override
    public int getItemCount(){
        return mDataSet.size();
    }

    //ok shit the position it's sending is sending the position of the element in the array that I built
    //I need to find a way to save the location of every item and retrieve it in here, and then retrieve that elemtn from the array
    //not using the value of the position in the new array
    //that doesn't correspond with the actual array that exists. 

    private void goToCalendarEventViewer (int position){
        Intent intent = new Intent(mContext,CalendarEventViewer.class);
        intent.putExtra("position", position);
        mContext.startActivity(intent);
    }
    private void goToChecklistViewer (){
        Intent intent = new Intent(mContext,checklistForm.class);
        mContext.startActivity(intent);
    }
    private void goToHourEventViewer (int position){
        Intent intent = new Intent(mContext,HourEventViewer.class);
        intent.putExtra("position", position);
        mContext.startActivity(intent);

    }



}
