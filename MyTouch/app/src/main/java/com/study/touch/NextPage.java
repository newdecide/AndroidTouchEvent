package com.study.touch;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NextPage extends AppCompatActivity {

    Button androidimage;
    LinearLayout moveimage;

    private static Toast ControlToastMessage;

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
//                        Toast.makeText(getApplicationContext(),"이동중입니다.",Toast.LENGTH_SHORT).show();
                        ToastCheck(getApplicationContext(), "이동했습니다.");
                        androidimage.setX(event.getX()-120);
                        androidimage.setY(event.getY()-120);
                }
                return true;
            }
        });
        androidimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastCheck(getApplicationContext(),"이미지 버튼을 클릭했습니다.");
            }
        });
    }


    public void ToastCheck(Context context, String message){
        if(message != null) {
            if(ControlToastMessage == null){
                ControlToastMessage = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            } else {
                ControlToastMessage.setText(message);
            }
            ControlToastMessage.show();
        }
    }
}

