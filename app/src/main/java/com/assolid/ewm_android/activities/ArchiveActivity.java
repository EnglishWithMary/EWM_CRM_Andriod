package com.assolid.ewm_android.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.assolid.ewm_android.R;
import com.assolid.ewm_android.RecyclerViewAdapter;
import com.assolid.ewm_android.database.DBHandler;
import com.assolid.ewm_android.models.Card;

import java.util.ArrayList;
import java.util.List;

public class ArchiveActivity extends AppCompatActivity{

    EditText searchIdBox;
    List<Card> rowListCard;
    Button showAllCardsBtn;

    private GridLayoutManager lLayout;

    ArrayList<Card> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_archive);
        rowListCard = getAllItemList();
        lLayout = new GridLayoutManager(ArchiveActivity.this, 2);

        final RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);
        rView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });

        final RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(ArchiveActivity.this, rowListCard);
        rView.setAdapter(rcAdapter);

        //cards = new ArrayList<Card>();

//        searchBtn = (Button) findViewById(R.id.searchBtn);
//        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchCards();
//            }
//        });
        showAllCardsBtn = (Button) findViewById(R.id.showAllCardsBtn);
        showAllCardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowListCard = getAllItemList();
                rView.setAdapter(rcAdapter);
            }
        });
//        showAllCards();
    }
    public List<Card> getAllItemList(){
        List<Card> allItems = new ArrayList<Card>();
        DBHandler db = new DBHandler(this,null,null,1);
        for(int i=1;i <= db.getLastId();i++){
            Card card = db.getCard(i);
            allItems.add(card);
        }
        db.close();
        return allItems;
    }
//    public void searchCards(){
//        DBHandler dbHandler = new DBHandler(this, null, null, 1);
//        Card learningCard = dbHandler.getCard(Integer.parseInt(searchIdBox.getText().toString()));
//        dbHandler.close();
//
//        Card card = new Card(getBaseContext());
//        CardHeader header = new CardHeader(getBaseContext());
//        header.setTitle(learningCard.getEngText()+"|"+ learningCard.getRuText());
//        card.addCardHeader(header);
//        mCardArrayAdapter.clear();
//        cardViewNative = (CardViewNative) findViewById(R.id.card);
//        assert cardViewNative != null;
//        cardViewNative.setCard(card);
//    }
}
