package com.typowy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button activityButton = (Button) findViewById(R.id.buttonStartGame);
        Button rankingButton = (Button) findViewById(R.id.buttonRanking);
        Button exitButton = (Button) findViewById(R.id.buttonExit);
        Button authorsButton = (Button) findViewById(R.id.buttonAuthors);

        activityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent = new Intent(getApplicationContext(),Week.class);
                startActivity(startIntent);
                finish();
            }
        });
        rankingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent = new Intent(getApplicationContext(),RankingActivity.class);
                startActivity(startIntent);
                finish();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
                System.exit(0);
            }
        });
        authorsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent = new Intent(getApplicationContext(),Authors.class);
                startActivity(startIntent);
                finish();
            }
        });


    }
}
