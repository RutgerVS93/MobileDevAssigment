package com.example.rutge.mobdevassignment;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class ExtraActivity extends AppCompatActivity {

    float x1, x2, y1, y2;
    private int score = 0;

    private FloatingActionButton fab;
    private TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        fab = findViewById(R.id.plusButton);
        scoreText = findViewById(R.id.scoreText);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveButton();
            }
        });
    }

    void moveButton(){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int xVal;
        int yVal;
        int maxX = size.x - 200;
        int minX = 0;
        int maxY = size.y - 500;
        int minY = 0;

        xVal = new Random().nextInt((maxX - minX) + 1) + minX;
        yVal = new Random().nextInt((maxY - minY) + 1) + minY;

        fab.setX(xVal);
        fab.setY(yVal);

        score += 1;
        scoreText.setText("Score:" + score);
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch (touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;

            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x2 > x1){
                    Intent i = new Intent(ExtraActivity.this, DbActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}
