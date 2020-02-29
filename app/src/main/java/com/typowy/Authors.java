package com.typowy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Authors extends AppCompatActivity {

    TextView autorzy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);

        autorzy=findViewById(R.id.Autorzy);
        Button mMenu;

        mMenu = findViewById(R.id.buttonMenuAuthors);

        mMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startIntent);
                finish();
            }
        });

        autorzy.setText("Paweł Białek");
        autorzy.append(" \n\n" + "Paweł Mysłowski");
    }
}
