package com.assolid.ewm_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.assolid.ewm_android.R;
import com.assolid.ewm_android.database.DBHandler;
import com.assolid.ewm_android.models.Card;

public class WorkWithCardActivity extends AppCompatActivity {
    EditText engTextBox;
    EditText ruTextBox;
    Button createCardBtn;
    Button editCardBtn;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_card_layout);
        engTextBox = (EditText) findViewById(R.id.engText);
        ruTextBox = (EditText) findViewById(R.id.ruText);
        createCardBtn = (Button) findViewById(R.id.createCardBtn);
        editCardBtn = (Button) findViewById(R.id.saveCardBtn);
        createCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveCard();
            }
        });
    }
    public void SaveEdited(){
        
    }
    public void SaveCard(){
        DBHandler dbHandler = new DBHandler(getBaseContext(), null, null, 1);

        int cardId = dbHandler.getLastId()+1;

        Card card =
                new Card(cardId,engTextBox.getText().toString(),ruTextBox.getText().toString());

        dbHandler.addCard(card);
        dbHandler.close();
        engTextBox.setText("");
        ruTextBox.setText("");
        startActivity(new Intent(WorkWithCardActivity.this, MainActivity.class));
    }
}
