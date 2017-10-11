package com.example.skyler.softcalendar.uiredo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.skyler.softcalendar.CalendarEventManager;
import com.example.skyler.softcalendar.CardViewAdapter;
import com.example.skyler.softcalendar.EventAggregatorManager;
import com.example.skyler.softcalendar.MainFormElementsAadapter;
import com.example.skyler.softcalendar.R;

public class openui extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    String userEmail = "";
    String userName= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userEmail = getIntent().getStringExtra("userEmail");
        userName = getIntent().getStringExtra("userName");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openui);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = getApplicationContext();

        //TODO: you need to make this into a kind of recyclerview that can take all forms of input

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMainForm);

        mAdapter = new MainFormElementsAadapter(mContext, EventAggregatorManager.items);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.openui, menu);

        TextView userNameTextView = (TextView) findViewById(R.id.textViewUserName);
        userNameTextView.setText(userName);
        TextView userIDTextview = (TextView) findViewById(R.id.textViewEmail);
        userIDTextview.setText(userEmail);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calendarEvents) {
            Intent intent = new Intent(this, calendarEventsForm.class);
            startActivity(intent);

        } else if (id == R.id.nav_hourEvents) {
            Intent intent = new Intent(this, hourEventsForm.class);
            startActivity(intent);

        } else if (id == R.id.nav_checklist) {
            Intent intent = new Intent(this, checklistForm.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
