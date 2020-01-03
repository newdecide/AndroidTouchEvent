package com.study.touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView touchtext;
    View touchview;

    View gestureview;
    GestureDetector gesturedetector;

    // 터치 이벤트
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        touchtext = findViewById(R.id.touchtext);

        touchview = findViewById(R.id.touchview);

        touchview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int touchaction = event.getAction();
                float curX = event.getX();
                float curY = event.getY();

                if (touchaction == MotionEvent.ACTION_DOWN){
                    println("손가락 눌림 : " + curX + ", "+ curY);
                } else if (touchaction == MotionEvent.ACTION_MOVE){
                    println("손가락 이동 : " + curX + ", "+ curY);
                } else if (touchaction == MotionEvent.ACTION_UP){
                    println("손가락 땜 : " + curX + ", " +curY);
                }
                return true;
            }
        });

        gesturedetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown() 호출됨");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onShowPress() 호출됨");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapup() 호출됨");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onScroll() 호출됨 : "+distanceX +", "+distanceY);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress() 호출됨");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling() 호출됨 : "+ velocityX+ ", "+velocityY);
                return true;
            }
        });

        gestureview = findViewById(R.id.gestureview);
        gestureview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gesturedetector.onTouchEvent(event);
                return true;
            }
        });
    }

    public void println(String data){
        touchtext.append(data + '\n');
    }
}
