package com.shield.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0 for Red, 1 for Yellow, 2 for empty
    private int color = 0;
    private boolean isActive = true;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){

        ImageView image = (ImageView) view;
        int pos = Integer.parseInt(view.getTag().toString());

        if(gameState[pos]==2 && isActive){

            view.setTranslationX(-1000f);
            if(color==0) {
                ((ImageView) view).setImageResource(R.drawable.red);
                color = 1;
                gameState[pos] = 0;
            }
            else{
                ((ImageView) view).setImageResource(R.drawable.yellow);
                color = 0;
                gameState[pos] = 1;
            }
            view.animate().translationXBy(1000f).setDuration(400);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    //someone win
                    isActive = false;
                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 1)
                        winner = "Yellow";

                    TextView text = (TextView)findViewById(R.id.winner);
                    text.setText(winner + " win!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
                    layout.setVisibility(View.VISIBLE);

                }
                else {
                    boolean gameIsOver = true;

                    for (int state : gameState)
                        if (state == 2) gameIsOver = false;

                    if (gameIsOver) {

                        TextView winnerMessage = (TextView) findViewById(R.id.winner);
                        winnerMessage.setText("It's a draw");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }

    public void playAgain(View view){
        isActive = true;
        Arrays.fill(gameState, 2);

        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i< gridLayout.getChildCount(); i++)
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
