package com.assolid.ewm_android.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.assolid.ewm_android.R;
import com.assolid.ewm_android.RecyclerViewAdapter;
import com.assolid.ewm_android.database.DBHandler;
import com.assolid.ewm_android.models.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class ArchiveActivity extends AppCompatActivity {

    EditText searchIdBox;
    List<Card> rowListCard;
    Button showAllCardsBtn;

    RecyclerView rView;
    RecyclerViewAdapter rcAdapter;

    SharedPreferences prefs;

    private GridLayoutManager lLayout;

    ArrayList<Card> cards;

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        super.onBackPressed();  // optional depending on your needs
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_archive);
        rowListCard = getAllItemList();
        lLayout = new GridLayoutManager(ArchiveActivity.this, 2);
        rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);
//        rView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return false;
//            }
//        });

        rcAdapter = new RecyclerViewAdapter(ArchiveActivity.this, rowListCard);
        rView.setAdapter(rcAdapter);
        showAllCardsBtn = (Button) findViewById(R.id.showAllCardsBtn);
        showAllCardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowListCard = getAllItemList();
                startActivity(new Intent(getBaseContext(),ArchiveActivity.class));
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        DBHandler db = new DBHandler(this,null,null,1);
        switch (item.getItemId())
        {
            case 101:
                try{
                SharedPreferences prefs = PreferenceManager
                        .getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("EditId",item.getGroupId());
                editor.commit();
                    Toast.makeText(this, "Edit this", Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
                startActivity(new Intent(this, WorkWithCardActivity.class));
                break;
            case 102:
                db.deleteCard(item.getGroupId());
                db.close();
                startActivity(new Intent(getBaseContext(),ArchiveActivity.class));
                break;
            default:
                return super.onContextItemSelected(item);
        }
        db.close();
        return true;
    }

    public List<Card> getAllItemList() {
        List<Card> allItems = new ArrayList<Card>();
        DBHandler db = new DBHandler(this, null, null, 1);
        for (int i = 1; i <= db.getLastId(); i++) {
            Card card = db.getCard(i);
            allItems.add(card);
        }
        db.close();
        return allItems;
    }

}