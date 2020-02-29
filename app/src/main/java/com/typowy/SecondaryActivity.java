package com.typowy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class SecondaryActivity extends AppCompatActivity implements View.OnClickListener {

    private int[][] zajecia;
    private long[][] time;
    private TextView whatIsToClick;
    private TextView points;
    private TextView stoper;
    private int pkt = 0;
    private int day;
    private int tempAlpha = 0;

    ConstraintLayout layout;
    Handler customHandler = new Handler();
    long startTime =0L, timeMiliseconds = 0L, updateTime = 0l;

//Stoper

    Runnable updateTimeThread = new Runnable() {
        @Override
        public void run() {
            updateTime = SystemClock.uptimeMillis()-startTime;
            int secs = ((int) (updateTime/1000))%60;
            int miliseconds = (int) (updateTime%1000);
            stoper.setText("" + String.format("%2d",secs) + ":" + String.format("%3d",miliseconds));
            customHandler.postDelayed(this,0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        layout = (ConstraintLayout)findViewById(R.id.kampus);

        Object[] objectArray = (Object[]) getIntent().getExtras().getSerializable("zajecia");
        if(objectArray!=null){
            zajecia = new int[objectArray.length][];
            for(int i=0;i<objectArray.length;i++){
                zajecia[i]=(int[]) objectArray[i];
            }
        }
        Object[] objectArray2 = (Object[]) getIntent().getExtras().getSerializable("time");
        if(objectArray2!=null){
            time = new long[objectArray2.length][];
            for(int i=0;i<objectArray2.length;i++){
                time[i]=(long[]) objectArray2[i];
            }
        }
        day = getIntent().getIntExtra("Day",0);
        whatIsToClick = findViewById(R.id.whatToClick);
        points = findViewById(R.id.points);
        stoper = findViewById(R.id.stoper);

        Button returnToMenu = findViewById(R.id.backToMenu);

        returnToMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("zajecia", zajecia);
                mBundle.putSerializable("time",time);
                startIntent.putExtras(mBundle);
                startIntent.putExtra("Day",day);
                startActivity(startIntent);
                finish();
            }
        });

        View v;
        ViewGroup group = (ViewGroup)findViewById(R.id.kampus);
        for(int i = 0; i < group.getChildCount(); i++) {
            v = group.getChildAt(i);
            if(v instanceof Button) {
                v.setOnClickListener(this);
            }
        }

        String[] nazwytegosamego = getResources().getStringArray(zajecia[day][pkt]);
        //BuildingsIDList[buildingID] to nasze ID!!!!!

        whatIsToClick.setText(nazwytegosamego[getRandom(nazwytegosamego.length)]);
        points.setText("0 / " + zajecia[day].length);

        startTime = SystemClock.uptimeMillis();
        timeMiliseconds = SystemClock.uptimeMillis()-startTime;
        customHandler.postDelayed(updateTimeThread, 0);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button)v;
        System.out.println(zajecia[day][pkt]);
        System.out.println(getResources().getIdentifier(button.getText().toString(),"array", getPackageName()));
        System.out.println(findViewById(R.id.B7));


        if(getResources().getIdentifier(button.getText().toString(),"array", getPackageName()) == zajecia[day][pkt]){
            button.setBackgroundColor(Color.TRANSPARENT);
            tempAlpha = 0;
            pkt += 1;
            points.setText(pkt + " / " + zajecia[day].length);
            if(pkt == zajecia[day].length){
                time[day][pkt-1] = updateTime - timeMiliseconds;
                Intent intent = new Intent(getApplicationContext(),SummaryOfTheDay.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("zajecia", zajecia);
                mBundle.putSerializable("time", time);
                intent.putExtras(mBundle);
                intent.putExtra("Day",day);
                startActivity(intent);
                finish();

            } else {
                String[] nazwytegosamego = getResources().getStringArray(zajecia[day][pkt]);
                whatIsToClick.setText(nazwytegosamego[getRandom(nazwytegosamego.length)]);
                time[day][pkt-1] = updateTime - timeMiliseconds;
                timeMiliseconds = updateTime;
            }
        }
        else{
            if (tempAlpha<200){
                tempAlpha+=40;
            }
            ViewGroup group = (ViewGroup)findViewById(R.id.kampus);
            for(int i = 0; i < group.getChildCount(); i++) {
                v = group.getChildAt(i);
                if(v instanceof Button) {
                    Button tmp = (Button) v;

                    if(getResources().getIdentifier(tmp.getText().toString(),"array", getPackageName()) == zajecia[day][pkt]){
                        tmp.setBackgroundColor(Color.GREEN);
                        tmp.getBackground().setAlpha(tempAlpha);
                    }
                }
            }
        }
    }

    public static int getRandom(int length) {
        int rnd = new Random().nextInt(length);
        return rnd;
    }

}
