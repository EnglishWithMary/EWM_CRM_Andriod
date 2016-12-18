package com.assolid.ewm_android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.view.View;

import com.assolid.ewm_android.models.Card;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cardsDB.db";
    private static final String TABLE_CARDS = "cards";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ENG_TEXT = "engText";
    public static final String COLUMN_RU_TEXT = "ruText";

    public DBHandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CARDS_TABLE = "CREATE TABLE " +
                TABLE_CARDS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_ENG_TEXT
                + " TEXT," + COLUMN_RU_TEXT + " TEXT" + ")";
        db.execSQL(CREATE_CARDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
        onCreate(db);
    }
    public int getLastId() {
        String query = "Select * FROM " + TABLE_CARDS ;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        int id = 0;

        if (cursor.moveToFirst()) {
            cursor.moveToLast();
            id = Integer.parseInt(cursor.getString(0));
            cursor.close();
            db.close();
        }
        return id;
    }
    public int delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        int rnumber = db.delete(TABLE_CARDS, "id=?", new String[] {""+id});
        db.close();
        return rnumber;
    }
    public void addCard(Card card) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_ENG_TEXT, card.getEngText());
        values.put(COLUMN_RU_TEXT, card.getRuText());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_CARDS, null, values);
        db.close();
    }
    public Card getCard(int id) {
        String query = "Select * FROM " + TABLE_CARDS + " WHERE " + COLUMN_ID + " =  \"" + id + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Card card = new Card();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            card.setId(Integer.parseInt(cursor.getString(0)));
            card.setEngText(cursor.getString(1));
            card.setRuText(cursor.getString(2));
            cursor.close();
        } else {
            card = null;
        }
        db.close();
        return card;
    }
}