package com.example.skyler.softcalendar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class HourEventAdapter extends RecyclerView.Adapter<HourEventAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<HourEvent> mDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View v){
            super(v);
            mTextView = (TextView)v.findViewById(R.id.tv);
        }
    }
    public HourEventAdapter(Context context, ArrayList<HourEvent> DataSet){
        mDataSet = DataSet;
        mContext = context;
    }

    @Override
    public HourEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_custom_view,parent,false);
        HourEventAdapter.ViewHolder vh = new HourEventAdapter.ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(HourEventAdapter.ViewHolder holder, final int position){


        holder.mTextView.setText(mDataSet.get(position).getTitle());
        holder.mTextView.setClickable(true);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToCalendarViewer(position);
            }
        });

    }

    @Override
    public int getItemCount(){
        return mDataSet.size();
    }

    private void goToCalendarViewer(int position){
        Intent intent = new Intent(mContext,HourEventViewer.class);
        intent.putExtra("position", position);
        mContext.startActivity(intent);
    }



}