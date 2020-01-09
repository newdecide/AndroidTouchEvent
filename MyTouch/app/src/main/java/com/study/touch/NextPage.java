package com.study.touch;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NextPage extends AppCompatActivity {

    ImageView androidimage;
    LinearLayout moveimage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nextpage);

        androidimage = findViewById(R.id.androidimage);
        moveimage = findViewById(R.id.moveimage);

        moveimage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(getApplicationContext(),"클릭 됐습니다.",Toast.LENGTH_SHORT).show();
                        androidimage.setX(event.getX()-120);
                        androidimage.setY(event.getY()-120);
                    case MotionEvent.ACTION_MOVE:
                        Toast.makeText(getApplicationContext(),"이동중입니다.",Toast.LENGTH_SHORT).show();
                        androidimage.setX(event.getX()-120);
                        androidimage.setY(event.getY()-120);
                }
                return true;
            }
        });

    }
}
