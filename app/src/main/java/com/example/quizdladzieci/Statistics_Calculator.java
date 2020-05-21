package com.example.quizdladzieci;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Statistics_Calculator extends MenuForAllAcitivity {

    private Toolbar toolbar;

    //private  int highscore2 = 0;

    private TextView tvCalcGoodCount, tvCalcBadCount, tvCalcMarkYour, tvCalcMaxYour;

    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics__calculator);

        tvCalcMaxYour = findViewById(R.id.tvCalcMax);
        tvCalcGoodCount = findViewById(R.id.tvCalcGood);
        tvCalcBadCount = findViewById(R.id.tvCalcBad);
        tvCalcMarkYour = findViewById(R.id.tvCalcMark);
        //loadHighscore();

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



        Button buttonCalcGo = findViewById(R.id.btnCalcGo);
        buttonCalcGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                goToCalcNow();
            }
        });



    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int score = data.getIntExtra(CalcActivity.EXTRA_SCORE, 0);
        if ( score > highscore2 ) {
            updateHighScore(score);
        }
    }*/

    private void goToCalcNow () {

        Intent intent = new Intent(this, ActivityChose.class);
        startActivity(intent);
    }

    /*private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore2 = prefs.getInt(KEY_HIGHSCORE,0);
        tvCalcMaxYour.setText("Maksymalny wynik z Calcu: " + highscore2);
    }
    private void updateHighScore( int highscoreNew )
    {
        highscore2 = highscoreNew;
        tvCalcMaxYour.setText("Maksymalny wynik z Calcu: " + highscore2);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore2);
        editor.apply();
    }*/

    private void loadGoodAnswers( int goodAnswersNew )
    {

    }

    private void loadBadAnswers ( int badAnswersNew )
    {

    }

    private void loadMark ()
    {

    }



}
