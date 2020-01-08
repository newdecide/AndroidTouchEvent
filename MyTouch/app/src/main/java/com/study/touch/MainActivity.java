package com.study.touch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.VelocityTrackerCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView touchtext;
    View touchview;
    View gestureview;
    View velocityview;
    GestureDetector gesturedetector;
    VelocityTracker velocityTracker = null;
    Button nextbutton;

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

        velocityview = findViewById(R.id.velocityview);
        velocityview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int index = event.getActionIndex();
                int action = event.getActionMasked();
                int pointerId = event.getPointerId(index);

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        if (velocityTracker == null) {
                            velocityTracker = velocityTracker.obtain();
                        } else {
                            velocityTracker.clear();
                        }

                        velocityTracker.addMovement(event);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        velocityTracker.addMovement(event);
                        velocityTracker.computeCurrentVelocity(1000);

                        float x = VelocityTrackerCompat.getXVelocity(velocityTracker, pointerId);
                        float y = VelocityTrackerCompat.getYVelocity(velocityTracker, pointerId);

                        touchtext.setText("x:" + x + "\ny:" + y);
                        break;

                    case MotionEvent.ACTION_UP:
                        cancleTracker();
                        break;
                }
                return true;
            }
            private void cancleTracker() {
                if (null != velocityTracker) {
                    velocityTracker.recycle();
                    velocityTracker = null;
                }
            }
        });

        nextbutton = findViewById(R.id.nextbutton);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage = new Intent(getApplicationContext(), NextPage.class);
                startActivity(nextpage);
            }
        });

    }

    public void println(String data){
        touchtext.append(data + '\n');
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "시스템 [BACK] 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
            onBackPressed();
            return true;
        }
        return false;
    }
}
