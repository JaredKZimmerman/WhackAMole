package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable moleImage;
    private ImageView[] imageViews;
    private int moleLocation;
    public int score;
    private Random rand;
    public Update change;
    public Handler handler;
    public Button Start;
    public TextView scoreCount;
    public boolean on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreCount = findViewById(R.id.scoreCount);
        grid = findViewById(R.id.gridLayout);
        moleImage = getDrawable(R.drawable.mole);
        imageViews = new ImageView[16];
        rand = new Random();
        handler = new Handler();
        Start = findViewById(R.id.Start);
        moleLocation = rand.nextInt(16);
        on = false;
        change = new Update();
        for(int i=0; i<16; i++){
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.mole_view, null);
            imageViews[i].setMinimumWidth(270);
            imageViews[i].setMinimumHeight(270);
            if(i == moleLocation) imageViews[i].setImageDrawable(moleImage);
            grid.addView(imageViews[i]);
        }
    }

    public void startPressed(View v) {
        if(on){
            on = false;
            handler.removeCallbacks(change);
            score = 0;
            scoreCount.setText(score+"");
            Start.setText("Start");
        }else {
            on = true;
            handler.postDelayed(change, 1000);
            Start.setText("Reset");
        }
    }

    public void increment(View v){
        if(v==imageViews[moleLocation]){
            score++;
            scoreCount.setText(score+"");
        }
    }

    private class Update implements Runnable {

        public void run(){
            imageViews[moleLocation].setImageDrawable(null);
            moleLocation = rand.nextInt(16);
            imageViews[moleLocation].setImageDrawable(moleImage);
            handler.postDelayed(change, 750);
        }

    }

}
