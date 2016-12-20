package com.assolid.ewm_android.activities;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.assolid.ewm_android.R;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    public ListView mDrawerList;
    public ArrayAdapter<String> sideBarAdapter;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("EditId",0);
        editor.apply();
        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) startActivity(new Intent(MainActivity.this, WorkWithCardActivity.class));
                if(position==1) startActivity(new Intent(MainActivity.this, ArchiveActivity.class));
                if(position==2) startActivity(new Intent(MainActivity.this, CheckYourselfActivity.class));
            }
        });
    }
    private void addDrawerItems() {
        String[] osArray = { "Create new card", "Archive", "Check yourself"};
        sideBarAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(sideBarAdapter);
    }

    @Override
    public void onBackPressed()
    {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        super.onBackPressed();
    }
}
