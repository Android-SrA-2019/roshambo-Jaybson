/**
 * Jeremy Johnson
 * Assignment 2
 * March 8th, 2019
 */

package com.example.jerem.rockpaperscissors;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView gameCount;
    Rochambo ro = new Rochambo();
    int win;
    int lost;
    int draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This click event gets the selection you made, and the Games selection.
     * It then uses the Rochambo class to determine if you won or lost or draw the game.
     * It then updates the result text according to if you won, lost or draw.
     * I added extra features such as a count  tbat keeps track of the wins/lost/draws.
     * I also added colored text depending on win/lost/draw, and animations for the ImageViews.
     */
    public void onClick(View view){
        ImageView playerImg = findViewById(R.id.playersMoveImage);
        ImageView gamesImg = findViewById(R.id.gamesMoveImage);

        /**
         * ObjectAnimator that will animate the 2 ImageViews when a selection is clicked.
         */
        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(playerImg,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(gamesImg,
                "rotationX", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();

        /**
         * Switch statement that will get the Player's choice depending on what ImageView they
         * clicked on. It will then set the result image depending on their choice.
         */
        switch (view.getId()) {
            case R.id.rockButton :
                ro.playerMakesMove(0);
                playerImg.setImageResource(R.mipmap.ic_rock_button_round);
                break;
            case R.id.paperButton :
                ro.playerMakesMove(1);
                playerImg.setImageResource(R.mipmap.ic_paper_button_round);
                break;
            case R.id.scissorsButton :
                ro.playerMakesMove(2);
                playerImg.setImageResource(R.mipmap.ic_scissor_button_round);
                break;
        }


        /**
         * Switch statement that will set the Game's image according to their randomized choice.
         */
        int gameMove = ro.getGameMove();
        switch(gameMove){
            case 0:
                gamesImg.setImageResource(R.mipmap.ic_rock_button_round);
                break;
            case 1:
                gamesImg.setImageResource(R.mipmap.ic_paper_button_round);
                break;
            case 2:
                gamesImg.setImageResource(R.mipmap.ic_scissor_button_round);
                break;
        }

        /**
         * Sets the textView to tell you if you won, lost, or had a draw.
         */
        ro.winLoseOrDraw();
        TextView resultTextView = findViewById(R.id.resultText);
        resultTextView.setText(ro.winLoseOrDraw());

        /**
         * Sets the colored text depending on if you won, lost, or had a draw.
         * Also adds to the counter.
         */
        if(ro.winLoseOrDraw() == ro.WIN){
            win++;
            gameCount = findViewById(R.id.gamesWonText);
            gameCount.setText("Won: " + win);
            resultTextView.setTextColor(Color.parseColor("#22bf2f"));
        }
        else if(ro.winLoseOrDraw() == ro.LOSE){
            lost++;
            gameCount = findViewById(R.id.gamesLostText);
            gameCount.setText("Lost: " + lost);
            resultTextView.setTextColor(Color.parseColor("#c10b0b"));
        }
        else if(ro.winLoseOrDraw() == ro.DRAW){
            draw++;
            gameCount = findViewById(R.id.gamesDrawText);
            gameCount.setText("Draw: " + draw);
            resultTextView.setTextColor(Color.parseColor("#3c73d1"));
        }
    }
}
