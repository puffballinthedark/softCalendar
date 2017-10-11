package com.example.skyler.softcalendar.uiredo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.skyler.softcalendar.ChecklistEventManager;
import com.example.skyler.softcalendar.ChecklistObject;
import com.example.skyler.softcalendar.ChecklistViewAdapter;
import com.example.skyler.softcalendar.EventAggregatorManager;
import com.example.skyler.softcalendar.R;

public class checklistForm extends AppCompatActivity {

    private static Context mContext;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_form);
        refresh();


        Button add = (Button) findViewById(R.id.buttonChecklistAdd);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ChecklistObject name = new ChecklistObject();
                EditText checkListText = (EditText) findViewById(R.id.editText);

                name.setName(checkListText.getText().toString());

                ChecklistEventManager.addChecklistEvent(name.getName());
                EventAggregatorManager.additem(name);
                checkListText.setText("");
                refresh();
            }
        });}
    private void refresh(){

        mContext = getApplicationContext();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewChecklist);

        mAdapter = new ChecklistViewAdapter(mContext, ChecklistEventManager.checklists);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    }


