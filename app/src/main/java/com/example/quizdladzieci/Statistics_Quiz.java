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

public class Statistics_Quiz extends MenuForAllAcitivity {

    private Toolbar toolbar;

    private  int highscore2 = 0;

    private TextView tvQuizGoodCount, tvQuizBadCount, tvQuizMarkYour, tvQuizMaxYour;

    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics__quiz);

        tvQuizMaxYour = findViewById(R.id.tvQuizMax);
        tvQuizGoodCount = findViewById(R.id.tvQuizGood);
        tvQuizBadCount = findViewById(R.id.tvQuizBad);
        tvQuizMarkYour = findViewById(R.id.tvQuizMark);
        loadHighscore();

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



        Button buttonQuizGo = findViewById(R.id.btnQuizGo);
        buttonQuizGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                goToQuizNow();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
        if ( score > highscore2 ) {
            updateHighScore(score);
        }
    }

    private void goToQuizNow () {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore2 = prefs.getInt(KEY_HIGHSCORE,0);
        tvQuizMaxYour.setText("Maksymalny wynik z quizu: " + highscore2);
    }
    private void updateHighScore( int highscoreNew )
    {
        highscore2 = highscoreNew;
        tvQuizMaxYour.setText("Maksymalny wynik z quizu: " + highscore2);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore2);
        editor.apply();
    }

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
