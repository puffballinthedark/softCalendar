package com.example.skyler.softcalendar;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        String shit = mDataSet.get(position).getClass().getSimpleName();
        if(shit.equals("CalendarEvent")){
            System.out.println("I guess it fucking worked");
            return -1;
        }else{
            return 0;}
    }
    @Override
    public MainFormElementsAadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        if (viewType == -1){
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
        String shit = mDataSet.get(position).getClass().getSimpleName();
        if(shit.equals("CalendarEvent")){
            holder.mTextView.setClickable(true);
        }else{

        }

        //TODO: you need to set the custom view's textview attribute, add an onclicked listener to the thing, and call the appropriate form
    }
    @Override
    public int getItemCount(){
        return mDataSet.size();
    }



}
