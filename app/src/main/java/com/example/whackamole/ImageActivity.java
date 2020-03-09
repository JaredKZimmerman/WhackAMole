package com.example.whackamole;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {

    private RadioButton moleButton;
    private RadioButton birdButton;
    private RadioButton cageButton;
    private RadioButton flairButton;
    private int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        moleButton = findViewById(R.id.moleButton);
        birdButton = findViewById(R.id.birdButton);
        cageButton = findViewById(R.id.cagebutton);
        flairButton = findViewById(R.id.flairButton);
    }

    public void onBackPressed(){

        if (moleButton.isChecked()){
            image = 1;
            //image = getDrawable(R.drawable.mole);
        }else if (birdButton.isChecked()){
            image = 2;
            //image = getDrawable(R.drawable.bird);
        }else if (cageButton.isChecked()){
            image = 3;
            //image = getDrawable(R.drawable.cage);
        }else if (flairButton.isChecked()){
            image = 4;
            //image = getDrawable(R.drawable.ricflair);
        }else{
            image = 5;
            //image = getDrawable(R.drawable.orange);
        }
        Intent data = new Intent();
        data.putExtra("IMAGE", image);
        setResult(RESULT_OK, data);
        finish();
    }

}
