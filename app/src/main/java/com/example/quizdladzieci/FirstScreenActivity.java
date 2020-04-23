package com.example.quizdladzieci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstScreenActivity extends MenuForAllAcitivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);


        Button buttonQuiz = findViewById(R.id.buttonQuiz);
        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                goToQuizScreen();
            }
        });

        Button buttonCalc = findViewById(R.id.buttonCalc);
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                goToCalculatorScreen();
            }
        });


    }


    private void goToQuizScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void goToCalculatorScreen() {
        Intent intent = new Intent(this, ActivityChose.class);
        startActivity(intent);
    }
}
