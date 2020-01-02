package com.study.touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView touchtext;
    View touchview;

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
    }

    public void println(String data){
        touchtext.append(data + '\n');
    }
}
