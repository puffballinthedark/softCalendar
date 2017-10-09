package com.example.skyler.softcalendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    private ArrayList<CalendarEvent> mDataSet;
    private Context mContext;

    public CardViewAdapter(Context context, ArrayList<CalendarEvent> DataSet){
        mDataSet = DataSet;
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View v){
            super(v);
            //what view am I looking at???
            mTextView = (TextView)v.findViewById(R.id.tv);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_custom_view,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataSet.get(position).getTitle());
        holder.mTextView.setClickable(true);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainForm.createCalendarEvent(position);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override

    public int getItemCount(){
        return mDataSet.size();
    }}