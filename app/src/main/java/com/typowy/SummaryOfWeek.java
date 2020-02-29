package com.typowy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SummaryOfWeek extends AppCompatActivity {

    TextView podsumowanie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suumary_of_week);

        podsumowanie=findViewById(R.id.Podsumowanie);
        Button mMenu;

        mMenu = findViewById(R.id.buttonMenu);

        mMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startIntent);
            }
        });
        
        String str1 = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        str1= String.valueOf(SummaryOfTheDay.topScoreOfDay[0]);
        str2= String.valueOf(SummaryOfTheDay.topScoreOfDay[1]);
        str3= String.valueOf(SummaryOfTheDay.topScoreOfDay[2]);
        str4= String.valueOf(SummaryOfTheDay.topScoreOfDay[3]);
        str5= String.valueOf(SummaryOfTheDay.topScoreOfDay[4]);

        podsumowanie.setText("Udało ci się, miłego weekendu");
        podsumowanie.append(" \n\n" + "Poniedzialek " +str1);
        podsumowanie.append(" \n\n" + "Wtorek " +str2);
        podsumowanie.append(" \n\n" + "Sroda " +str3);
        podsumowanie.append(" \n\n" + "Czwartek " +str4);
        podsumowanie.append(" \n\n" + "Piatek " +str5);
            }


    }


