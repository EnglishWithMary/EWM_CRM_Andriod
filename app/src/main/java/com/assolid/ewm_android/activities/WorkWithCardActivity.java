package com.assolid.ewm_android.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.assolid.ewm_android.R;
import com.assolid.ewm_android.database.DBHandler;
import com.assolid.ewm_android.models.Card;

import java.util.prefs.Preferences;

public class WorkWithCardActivity extends AppCompatActivity {
    EditText engTextBox;
    EditText ruTextBox;
    Button createCardBtn;
    SharedPreferences prefs;
    boolean editOrNot = false;
    int editingCardId = 0;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_create_or_edit_layout);
        engTextBox = (EditText) findViewById(R.id.engText);
        ruTextBox = (EditText) findViewById(R.id.ruText);
        createCardBtn = (Button) findViewById(R.id.saveCardBtn);
        createCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCard();
            }
        });
        prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        try{
            loadCardToEdit(prefs.getInt("EditId",0));
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("EditId", 0);
            editor.apply();
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void loadCardToEdit(int id){
        if(id!=0){
            DBHandler db = new DBHandler(this,null,null,1);
            Card card = db.getCard(id);
            engTextBox.setText(card.getEngText());
            ruTextBox.setText(card.getRuText());
            editOrNot=true;
            editingCardId = id;
        }
    }
    @Override
    public void onBackPressed()
    {
        if(!editOrNot){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
        }else {
            startActivity(new Intent(getBaseContext(),ArchiveActivity.class));
        }
        super.onBackPressed();  // optional depending on your needs
    }

    public void saveCard(){
        if(!editOrNot) {
            DBHandler dbHandler = new DBHandler(getBaseContext(), null, null, 1);
            int cardId = dbHandler.getLastId() + 1;
            Card card =
                    new Card(cardId, engTextBox.getText().toString(), ruTextBox.getText().toString());
            dbHandler.addCard(card);
            dbHandler.close();
            engTextBox.setText("");
            ruTextBox.setText("");
            startActivity(new Intent(WorkWithCardActivity.this, MainActivity.class));
        }else{
            DBHandler dbHandler = new DBHandler(getBaseContext(), null, null, 1);
            Card card =
                    new Card(editingCardId,engTextBox.getText().toString(),ruTextBox.getText().toString());
            dbHandler.replaceCard(card);
            dbHandler.close();
            engTextBox.setText("");
            ruTextBox.setText("");
            startActivity(new Intent(WorkWithCardActivity.this, ArchiveActivity.class));
        }
    }
}
