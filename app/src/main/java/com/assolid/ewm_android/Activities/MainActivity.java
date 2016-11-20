package com.assolid.ewm_android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.assolid.ewm_android.R;

public class MainActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        mDrawerList = (ListView)findViewById(R.id.navList);
        addDrawerItems();
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) startActivity(new Intent(MainActivity.this, CreateCardActivity.class));
                if(position==1) startActivity(new Intent(MainActivity.this, ArchiveActivity.class));
                if(position==2) startActivity(new Intent(MainActivity.this, CheckYourselfActivity.class));
            }
        });
    }
    private void addDrawerItems() {
        String[] osArray = { "Create new card", "Archive", "Check yourself" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }
}
