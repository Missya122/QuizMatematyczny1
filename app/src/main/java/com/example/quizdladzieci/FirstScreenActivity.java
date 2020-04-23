package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
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
