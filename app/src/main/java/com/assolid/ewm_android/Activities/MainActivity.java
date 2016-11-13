package com.assolid.ewm_android.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.assolid.ewm_android.R;
import com.assolid.ewm_android.database.DBHandler;
import com.assolid.ewm_android.model.NewCard;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardViewNative;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }
    public void showCardMenu(View view){
        setContentView(R.layout.create_card_layout);
    }
    EditText engTextBox;
    EditText ruTextBox;
    public void createNewCard(View view){
        engTextBox = (EditText) findViewById(R.id.engText);
        ruTextBox = (EditText) findViewById(R.id.ruText);
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        int cardId = dbHandler.getLastId()+1;

        NewCard card =
                new NewCard(cardId,engTextBox.getText().toString(),ruTextBox.getText().toString());

        dbHandler.addCard(card);
        dbHandler.close();
        engTextBox.setText("");
        ruTextBox.setText("");
        setContentView(R.layout.main_menu);
    }
    public void showCards(View view){
        setContentView(R.layout.all_cards);
    }
    public void goSearch(View view){
        EditText searchIdBox = (EditText) findViewById(R.id.searchId);

        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        NewCard newCard = dbHandler.findCard(Integer.parseInt(searchIdBox.getText().toString()));
        dbHandler.close();

        Card card = new Card(getBaseContext());
        CardHeader header = new CardHeader(getBaseContext());
        header.setTitle(newCard.getEngText()+"|"+newCard.getRuText());
        card.addCardHeader(header);
        CardViewNative cardView = (CardViewNative) findViewById(R.id.card);
        assert cardView != null;
        cardView.setCard(card);
    }
}
