package com.typowy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Week extends AppCompatActivity {

    private int[][] zajecia;
    private long[][] time;
    private int[] BuildingsIDList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        Button poniedzialek = findViewById(R.id.zaczynajmy);

        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(R.array.budynki);
        int n = ta.length();
        BuildingsIDList = new int[n];
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            if (id > 0) {
                BuildingsIDList[i] = id;
            }
        }
        ta.recycle();

        //zajecia przechowuje id budynkow z podzialem na dni
        zajecia = new int[5][];
        time = new long[5][];
        for(int i=0; i<5; i++){
            int rnd = getRandom(2)+4;
            zajecia[i] = new int[rnd];
            time[i] = new long[rnd];
            for (int j=0;j<rnd;j++){
                zajecia[i][j] = BuildingsIDList[getRandom(BuildingsIDList.length)];
                while(j>0 && zajecia[i][j] == zajecia[i][j-1]){
                    zajecia[i][j] = BuildingsIDList[getRandom(BuildingsIDList.length)];
                }
            }
        }


        poniedzialek.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent = new Intent(getApplicationContext(),SecondaryActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("zajecia", zajecia);
                mBundle.putSerializable("time", time);
                startIntent.putExtras(mBundle);
                startIntent.putExtra("Day",0);
                startActivity(startIntent);
                finish();
            }
        });
    }

    public static int getRandom(int length) {
        int rnd = new Random().nextInt(length);
        return rnd;
    }
}
