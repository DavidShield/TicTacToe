package com.shield.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // 0 for Red, 1 for Yellow, 2 for empty
    private int color = 0;

    public void dropIn(View view){
        ImageView image = (ImageView) view;
        ((ImageView) view).setImageResource(R.drawable.red);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
