package com.example.whackamole;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
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
    private Drawable birdImage;
    private Drawable cageImage;
    private Drawable ricflairImage;
    private Drawable orangeImage;
    private ImageView[] imageViews;
    private Drawable currentImage;
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
        birdImage = getDrawable(R.drawable.bird);
        cageImage = getDrawable(R.drawable.cage);
        ricflairImage = getDrawable(R.drawable.ricflair);
        orangeImage = getDrawable(R.drawable.orange);
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

    public void imagePressed(View v){
        Intent i = new Intent(this, ImageActivity.class);
        startActivityForResult(i, 1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int image = data.getIntExtra("IMAGE", 1);
        if (image == 1){
            imageViews[moleLocation].setImageDrawable(moleImage);
            currentImage = getDrawable(R.drawable.mole);
        }else if (image == 2){
            imageViews[moleLocation].setImageDrawable(birdImage);
            currentImage = getDrawable(R.drawable.bird);
        }else if (image == 3){
            imageViews[moleLocation].setImageDrawable(cageImage);
            currentImage = getDrawable(R.drawable.cage);
        }else if (image == 4){
            imageViews[moleLocation].setImageDrawable(ricflairImage);
            currentImage = getDrawable(R.drawable.ricflair);
        }else if (image == 5){
            imageViews[moleLocation].setImageDrawable(orangeImage);
            currentImage = getDrawable(R.drawable.orange);
        }
    }

    private class Update implements Runnable {

        public void run(){
            imageViews[moleLocation].setImageDrawable(null);
            moleLocation = rand.nextInt(16);
            imageViews[moleLocation].setImageDrawable(currentImage);
            handler.postDelayed(change, 750);
        }

    }

}
