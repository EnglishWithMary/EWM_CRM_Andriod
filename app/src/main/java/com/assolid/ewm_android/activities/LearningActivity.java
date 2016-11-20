package com.assolid.ewm_android.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.assolid.ewm_android.model.LearningCard;

import java.util.ArrayList;

/**
 * Created by Данила on 20.11.2016.
 */
public class LearningActivity extends AppCompatActivity{
    ArrayList<LearningCard> learningCards;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        learningCards = new ArrayList<LearningCard>();
        super.onCreate(savedInstanceState);
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
        addToLearningCards("Door","Дверь");
    }
    public void addToLearningCards(String engText,String ruText){
        LearningCard learningCard = new LearningCard();
        learningCard.setEngText(engText);
        learningCard.setRuText(ruText);
        learningCards.add(learningCard);
    }
}
