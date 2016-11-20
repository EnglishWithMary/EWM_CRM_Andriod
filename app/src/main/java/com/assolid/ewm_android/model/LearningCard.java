package com.assolid.ewm_android.model;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Данила on 13.11.2016.
 */
public class LearningCard {
    private int id;
    private String engText;
    private String ruText;
    public LearningCard() {

    }
    public LearningCard(int id, String engText, String ruText)
    {
        this.id=id;
        this.engText=engText;
        this.ruText=ruText;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setEngText(String engText) {
        this.engText = engText;
    }
    public void setRuText(String ruText) {
        this.ruText = ruText;
    }
    public int getId() {
        return id;
    }
    public String getEngText() {
        return engText;
    }
    public String getRuText() {
        return ruText;
    }
}
