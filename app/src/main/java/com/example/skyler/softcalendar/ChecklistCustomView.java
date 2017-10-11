package com.example.skyler.softcalendar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class ChecklistCustomView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_custom_view);

        final CheckBox checkbox = (CheckBox) findViewById(R.id.checkBoxCheckList);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(checkbox.isChecked()){
                    TextView text = (TextView) findViewById(R.id.tv);
                    text.setTextColor(Color.RED);
                }else{
                }
            }
        });
    }
}
