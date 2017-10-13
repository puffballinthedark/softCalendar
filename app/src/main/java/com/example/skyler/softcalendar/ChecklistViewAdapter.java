package com.example.skyler.softcalendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ChecklistViewAdapter extends RecyclerView.Adapter<ChecklistViewAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ChecklistObject> mDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View v){
            super(v);
            mTextView = (TextView)v.findViewById(R.id.tv);
        }
    }
    public ChecklistViewAdapter(Context context, ArrayList<ChecklistObject> DataSet){
        mDataSet = DataSet;
        mContext = context;
    }

    @Override
    public void onBindViewHolder(ChecklistViewAdapter.ViewHolder holder, final int position){
        holder.mTextView.setText(mDataSet.get(position).getName());
        holder.mTextView.setClickable(true);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
    }

    @Override
    public ChecklistViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_checklist_custom_view,parent,false);
        ChecklistViewAdapter.ViewHolder vh = new ChecklistViewAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount(){
        return mDataSet.size();
    }


}
