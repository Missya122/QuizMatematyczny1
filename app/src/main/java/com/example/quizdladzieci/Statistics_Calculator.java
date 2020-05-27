package com.example.quizdladzieci;

import androidx.annotation.NonNull;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Statistics_Calculator extends MenuForAllAcitivity {

    private Toolbar toolbar;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    private  int highscore2 = 0;


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

        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);

                tvCalcGoodCount.setText("Liczba wszystkich poprawnych odpowiedzi to: " + userProfile.getUserGoodAnswersQuiz());
                tvCalcMaxYour.setText("Twój najlepszy wynik: " + userProfile.getUserBestScoreQuiz());
                tvCalcBadCount.setText("Liczba wszystkich błędnych odpowiedzi to: " + userProfile.getUserBadAnswersQuiz());
                float all_question = (float) (userProfile.getUserBadAnswersQuiz() + userProfile.getUserGoodAnswersQuiz());

                float result = (float) (userProfile.getUserGoodAnswersQuiz())/all_question;
                int mark = 0;

                if( result >= 0 && result < 0.3 )
                {
                    mark = 1;
                }
                else if( result >= 0.3 && result < 0.5 ) {
                    mark = 2;
                }
                else if ( result >= 0.5 && result <0.75 ) {
                    mark = 3;
                }
                else if( result >= 0.75 && result <0.9 ) {
                    mark = 4;
                }
                else if( result >=0.9 && result <=1 )
                {
                    mark = 5;
                }
                else if( all_question < 10.0 )
                {
                    tvCalcMarkYour.setText("Odpowiedz na co najmniej 10 pytań!");
                }

                highscore2 = userProfile.getUserBestScoreCalc();
                tvCalcMaxYour.setText("Maksymalny wynik z kalkulatora: " + highscore2);


                tvCalcMarkYour.setText( String.valueOf(mark));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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



    private void goToCalcNow () {

        Intent intent = new Intent(this, ActivityChose.class);
        startActivity(intent);
    }





}
