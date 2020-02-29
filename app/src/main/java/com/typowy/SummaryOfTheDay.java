package com.typowy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryOfTheDay extends AppCompatActivity {

    private int[][] zajecia;
    private long[][] time;
    private int day;
    private int dayScore = 0;
    private TextView scoreText;

    private boolean startoweWyniki = false;

    private static final String PREFERENCES_NAME = "myPreferences";
    private SharedPreferences preferences;
    public static String[] topScores = {"PN1","PN2","PN3","WT1","WT2","WT3","SR1","SR2","SR3","CZ1","CZ2","CZ3","PT1","PT2","PT3"};
    public static String[] valuesScores = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
    public static int[] topScoreOfDay = {0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_of_the_day);
        scoreText = (TextView) findViewById(R.id.wyniki);

        preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);


        if(startoweWyniki==false && getDefaults(topScores[0],this)==null){
            SummaryOfTheDay.resetScores(topScores,valuesScores,this);
            startoweWyniki=true;
        }

        Button newDay = findViewById(R.id.newDay);

        day = getIntent().getIntExtra("Day", 0);
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

        day += 1;

        for(int i=0; i<time[day-1].length; i++){
            dayScore+=time[day-1][i];
        }
        dayScore= 10000 - dayScore/time.length;

        switch (day){
            case 1:
                scoreText.setText("Poniedzialek wynik: " + dayScore);
                topScoreOfDay[day-1]=dayScore;
                compare(dayScore,topScores,day,this);
                break;
            case 2:
                scoreText.setText("Wtorek wynik: " + dayScore);
                topScoreOfDay[day-1]=dayScore;
                compare(dayScore,topScores,day,this);
                break;
            case 3:
                scoreText.setText("Sroda wynik: " + dayScore);
                topScoreOfDay[day-1]=dayScore;
                compare(dayScore,topScores,day,this);
                break;
            case 4:
                scoreText.setText("Czwartek wynik: " + dayScore);
                topScoreOfDay[day-1]=dayScore;
                compare(dayScore,topScores,day,this);
                break;
            case 5:
                scoreText.setText("Piatek wynik: " + dayScore);
                topScoreOfDay[day-1]=dayScore;
                compare(dayScore,topScores,day,this);
                Intent intent = new Intent(getApplicationContext(),SummaryOfWeek.class);
                startActivity(intent);
                finish();
                break;
            }

        newDay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent = new Intent(getApplicationContext(),SecondaryActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("zajecia", zajecia);
                mBundle.putSerializable("time",time);
                startIntent.putExtras(mBundle);
                startIntent.putExtra("Day",day);
                startActivity(startIntent);
                finish();
            }
        });
    }

    public static void compare(int value, String[] scores, int day, Context context){
        int convertDay = day*3-3;
        for(int i=0;i<3;i++){
            String getValueString=getDefaults(scores[i+convertDay],context);
            int getValueInt = Integer.parseInt(getValueString);
            if(getValueInt<value){
                if(i==0){
                    setDefaults(scores[2+convertDay],getDefaults(scores[1+convertDay],context), context );
                    setDefaults(scores[1+convertDay],getDefaults(scores[0+convertDay],context), context );
                }
                if(i==1){
                    setDefaults(scores[2+convertDay],getDefaults(scores[1+convertDay],context), context );
                }
                String str1 = Integer.toString(value);
                setDefaults(scores[i+convertDay],str1, context );
                break;
            }
        }
    }
    public static void resetScores(String[] keys, String[] values, Context context){
        for(int i=0;i<15;i++){
            setDefaults(keys[i],values[i],context);
        }
    }

    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}
