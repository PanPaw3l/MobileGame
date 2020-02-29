package com.typowy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RankingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner mSpinner;
    TextView mOutputSpinner;
    Button mReset;
    Button mMenu;
    String[] mDays = {"Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek"};
    String[] topScores = {"PN1","PN2","PN3","WT1","WT2","WT3","SR1","SR2","SR3","CZ1","CZ2","CZ3","PT1","PT2","PT3"};
    String[] valuesScores = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};

    private static final String file_name = "scores.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        mSpinner = findViewById(R.id.spinner);
        mOutputSpinner = findViewById(R.id.wyniki);
        mReset = findViewById(R.id.buttonReset);
        mMenu = findViewById(R.id.buttonBack);


        ArrayAdapter mAdapter= new ArrayAdapter(this,android.R.layout.simple_spinner_item, mDays);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(this);

        mReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                 SummaryOfTheDay.resetScores(topScores,valuesScores,getApplicationContext());
            }
        });

        mMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startIntent);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0){
            showRecords(topScores,position,this,mOutputSpinner);
        }if (position == 1){
            showRecords(topScores,position,this,mOutputSpinner);
        }if (position == 2){
            showRecords(topScores,position,this,mOutputSpinner);
        }if (position == 3){
            showRecords(topScores,position,this,mOutputSpinner);
        }if (position == 4){
            showRecords(topScores,position,this,mOutputSpinner);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public static void showRecords(String[] topScores, int position, Context context, TextView pole){
        int convert = position*3;
        String str1 = "";
        String str2 = "";
        String str3 = "";
        str1=SummaryOfTheDay.getDefaults(topScores[0+convert],context);
        str2=SummaryOfTheDay.getDefaults(topScores[1+convert],context);
        str3=SummaryOfTheDay.getDefaults(topScores[2+convert],context);
        pole.setText("1. " +str1);
        pole.append(" \n\n" + "2. " +str2);
        pole.append(" \n\n" + "3. " +str3);
    }

}
