package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Statistics extends MenuForAllAcitivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);


        Button buttonQuizStats = findViewById(R.id.btnQuizStats);
        buttonQuizStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                goToQuizStatsScreen();
            }
        });

        Button buttonCalcStats = findViewById(R.id.btnCalcStats);
        buttonCalcStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                goToCalculatorStatsScreen();
            }
        });


    }


    private void goToQuizStatsScreen() {
        Intent intent = new Intent(this, Statistics_Quiz.class);
        startActivity(intent);
    }
    private void goToCalculatorStatsScreen() {
        Intent intent = new Intent(this, Statistics_Calculator.class);
        startActivity(intent);
    }


    }

