package com.example.skyler.softcalendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<CalendarEvent> mDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View v){
            super(v);
            mTextView = (TextView)v.findViewById(R.id.tv);
        }
    }
    public CardViewAdapter(Context context, ArrayList<CalendarEvent> DataSet){
        mDataSet = DataSet;
        mContext = context;
    }

    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_custom_view,parent,false);
        CardViewAdapter.ViewHolder vh = new CardViewAdapter.ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(CardViewAdapter.ViewHolder holder, final int position){
        holder.mTextView.setText(mDataSet.get(position).getTitle());
        holder.mTextView.setText(mDataSet.get(position).getTitle());
        holder.mTextView.setClickable(true);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: open a form here that shows you all your stuff
            }
        });
    }

    @Override
    public int getItemCount(){
        return mDataSet.size();
    }


}