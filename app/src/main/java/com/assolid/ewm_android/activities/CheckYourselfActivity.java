package com.assolid.ewm_android.activities;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assolid.ewm_android.R;
import com.assolid.ewm_android.database.DBHandler;
import com.assolid.ewm_android.models.Card;

import java.util.Random;

public class CheckYourselfActivity extends AppCompatActivity implements View.OnTouchListener{
    TextView ruTextView;
    TextView engTextView;
    CardView cardView;
    private GestureDetectorCompat gestureObject;

    boolean isFlipped = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        context = this;
        setContentView(R.layout.check_yourself_layout);
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());
        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150,150);
        engTextView = (TextView) findViewById(R.id.engTextView);
        ruTextView = (TextView) findViewById(R.id.ruTextView);
        cardView = (CardView) findViewById(R.id.check_yourself_card);
        showRandomCard();
        cardView.setOnTouchListener(this);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void showRandomCard(){
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        Card card = new Card();
        try{
            card = dbHandler.getCard(new Random().nextInt(dbHandler.getLastId())+1);
        }catch(Exception e){
            Toast.makeText(this,"Oh! It look's like there're no saved cards",Toast.LENGTH_LONG).show();
        }finally {
            startActivity(new Intent(this,MainActivity.class));
        }
        dbHandler.close();
        isFlipped=false;
        ruTextView.setVisibility(View.INVISIBLE);
        engTextView.setVisibility(View.VISIBLE);
        ruTextView.setText(card.getRuText());
        engTextView.setText(card.getEngText());
    }
    public void flipCard(){
        if(!isFlipped){
            cardView.animate().rotationYBy(180).setDuration(300).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    engTextView.setVisibility(View.INVISIBLE);
                    ruTextView.animate().rotationYBy(180).setDuration(1);
                    engTextView.animate().rotationYBy(180).setDuration(1);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ruTextView.setVisibility(View.VISIBLE);
                    isFlipped = true;
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
//        else{
//            Toast.makeText(this,"You have flipped it",Toast.LENGTH_LONG).show();
//        }
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(final MotionEvent e1, final MotionEvent e2, float velocityX, float velocityY) {
            if(isFlipped){
                if(e2.getRawY() > e1.getRawY()){
                    moveCardDown();
                }
                if(e2.getRawY() < e1.getRawY()){
                    moveCardUp();
                }
            }
            return true;
        }
        void moveCardUp(){
            cardView.animate().yBy(-2000).setDuration(1000).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(final Animator animation1) {
//                    ruTextView.animate().rotationYBy(180).setDuration(1);
//                    engTextView.animate().rotationYBy(180).setDuration(1);
                    showRandomCard();
                    cardView.animate().yBy(+2000).setDuration(1000).setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animation.cancel();
                            animation1.cancel();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        void moveCardDown(){
            cardView.animate().yBy(+2000).setDuration(1000).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(final Animator animation1) {
                    showRandomCard();
//                    ruTextView.animate().rotationYBy(180).setDuration(1);
//                    engTextView.animate().rotationYBy(180).setDuration(1);
                    cardView.animate().yBy(-2000).setDuration(1000).setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animation.cancel();
                            animation1.cancel();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }
}
