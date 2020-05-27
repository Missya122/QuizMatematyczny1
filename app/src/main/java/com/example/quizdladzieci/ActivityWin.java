package com.example.quizdladzieci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.quizdladzieci.QuizActivity.EXTRA_SCORE;

public class ActivityWin extends MenuForAllAcitivity {
    private Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    private  int highscore2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        int counter;
        TextView textView = (TextView)findViewById(R.id.textView2);
        Button buttonOK = (Button)findViewById((R.id.buttonOK));
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);
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


        final Intent intent = getIntent();

        //final int score_add = intent.getIntExtra(ActivityAdd.EXTRA_COUNTER, -1);
        final int goodAns_add_k = intent.getIntExtra("goodAns_AddK", 0);
        final int goodAns_minus = intent.getIntExtra("goodAns_Minus", 0);
        final int goodAns_minus_k = intent.getIntExtra("goodAns_MinusK", 0);
        final int goodAns_divide = intent.getIntExtra("goodAns_Divide", 0);
        final int goodAns_divide_k = intent.getIntExtra("goodAns_Divide2", 0);
        final int goodAns_multiple = intent.getIntExtra("goodAns_Multiple", 0);

        final int wrongAns_add = intent.getIntExtra("wrongAns_Add",0);
        final int wrongAns_add_k = intent.getIntExtra("wrongAns_AddK",0);
        final int wrongAns_divide = intent.getIntExtra("wrongAns_Divide",0);
        final int wrongAns_divide2 = intent.getIntExtra("wrongAns_Divide2",0);
        final int wrongAns_minus = intent.getIntExtra("wrongAns_Minus",0);
        final int wrongAns_minus_k = intent.getIntExtra("wrongAns_MinusK",0);
        final int wrongAns_multiple = intent.getIntExtra("wrongAns_Multiple",0);

        final int goodAns_add = intent.getIntExtra("goodAns_Add", 0);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        Intent resultIntent_add = new Intent();
        resultIntent_add.putExtra("goodAns_Add", goodAns_add);
        setResult(RESULT_OK, resultIntent_add);

        Intent resultIntent_add_k = new Intent();
        resultIntent_add_k.putExtra("goodAns_AddK", goodAns_add_k);
        setResult(RESULT_OK, resultIntent_add_k);

        Intent resultIntent_divide = new Intent();
        resultIntent_divide.putExtra("goodAns_Divide", goodAns_divide);
        setResult(RESULT_OK, resultIntent_divide);

        Intent resultIntent_divide2 = new Intent();
        resultIntent_divide2.putExtra("goodAns_Divide2", goodAns_divide_k);
        setResult(RESULT_OK, resultIntent_divide2);

        Intent resultIntent_minus = new Intent();
        resultIntent_minus.putExtra("goodAns_Minus", goodAns_minus);
        setResult(RESULT_OK, resultIntent_minus);

        Intent resultIntent_minus_k = new Intent();
        resultIntent_minus_k.putExtra("goodAns_MinusK", goodAns_minus_k);
        setResult(RESULT_OK, resultIntent_minus_k);

        Intent resultIntent_multiple = new Intent();
        resultIntent_multiple.putExtra("goodAns_Multiple", goodAns_multiple);
        setResult(RESULT_OK, resultIntent_multiple);








        int no = intent.getIntExtra(ActivityMinus.EXTRA_NUMBER, 0);
        switch(no){
            case 1:
                counter = intent.getIntExtra(ActivityAdd.EXTRA_COUNTER, 0);
                textView.setText("Twój wynik to: \n" + counter + "\n Brawo!");
                break;
            case 2:
                counter = intent.getIntExtra(ActivityMinus.EXTRA_COUNTER, 0);
                textView.setText("Twój wynik to: \n" + counter + "\n Brawo!");
                break;
            case 3:
                counter = intent.getIntExtra(ActivityMultiple.EXTRA_COUNTER, 0);
                textView.setText("Twój wynik to: \n" + counter + "\n Brawo!");
                break;
            case 4:
                counter = intent.getIntExtra(ActivityDivide.EXTRA_COUNTER, 0);
                textView.setText("Twój wynik to: \n" + counter + "\n Brawo!");
                break;
            case 5:
                counter = intent.getIntExtra(ActivityDivide2.EXTRA_COUNTER, 0);
                textView.setText("Twój wynik to: \n" + counter + "\n Brawo!");
                break;
            case 6:
                counter = intent.getIntExtra(ActivityAddK.EXTRA_COUNTER, 0);
                textView.setText("Twój wynik to: \n" + counter + "\n Brawo!");
                break;
            case 7:
                counter = intent.getIntExtra(ActivityMinusK.EXTRA_COUNTER, 0);
                textView.setText("Twój wynik to: \n" + counter + "\n Brawo!");
                break;
        }

        buttonOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.start();

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                        highscore2 = userProfile.getUserBestScoreCalc();

                        /*int[] wyniki = new int [7];

                        wyniki[0] = goodAns_add;
                        wyniki[1] = goodAns_add_k;
                        wyniki[2] = goodAns_divide;
                        wyniki[3] = goodAns_divide_k;
                        wyniki[4] = goodAns_minus;
                        wyniki[5] = goodAns_minus_k;
                        wyniki[6] = goodAns_multiple;


                         int maks = wyniki[0];


                        for (int i = 0; i < wyniki.length ; i++) {
                            if (wyniki[i] > maks) {
                                maks = wyniki[i];
                                if( maks > highscore2 )
                                {
                                    updateHighScore(maks);
                                }

                            }
                        }*/

                        List<Integer> list = Arrays.asList(goodAns_add, goodAns_add_k, goodAns_divide, goodAns_divide_k, goodAns_minus, goodAns_minus_k, goodAns_multiple);

                        //int min = Collections.min(list);

                        int max = Collections.max(list);

                        if( max > highscore2) updateHighScore(max);

                        //ystem.out.println(min);
                        //System.out.println(max);




                        userProfile.userBestScoreCalc = highscore2;
                        DatabaseReference myRef_highscore = firebaseDatabase.getReference(firebaseAuth.getUid()).child("userBestScoreCalc");

                        myRef_highscore.setValue(userProfile.userBestScoreCalc);




                        userProfile.userGoodAnswersCalc = goodAns_add + goodAns_add_k + goodAns_minus + goodAns_minus_k + goodAns_divide + goodAns_divide_k + goodAns_multiple +userProfile.getUserGoodAnswersCalc();


                        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("userGoodAnswersCalc");
                        myRef.setValue(userProfile.userGoodAnswersCalc);

                        userProfile.userBadAnswersCalc = wrongAns_add + wrongAns_add_k + wrongAns_divide + wrongAns_divide2+ wrongAns_minus + wrongAns_minus_k + wrongAns_multiple + userProfile.getUserBadAnswersCalc();
                        DatabaseReference myRef_bad = firebaseDatabase.getReference(firebaseAuth.getUid()).child("userBadAnswersCalc");
                        myRef_bad.setValue(userProfile.userBadAnswersCalc);

                        /*updateHighScore(goodAns_add);
                        if(goodAns_add_k > highscore2) { updateHighScore(goodAns_add_k); }
                        else {
                            if( goodAns_minus > highscore2 ) { updateHighScore(goodAns_minus); }
                            else {
                                if ( goodAns_minus_k > highscore2 ) { updateHighScore(goodAns_minus_k); }
                                else {
                                    if ( goodAns_divide> highscore2 ) { updateHighScore(goodAns_divide); }
                                    else {
                                        if ( goodAns_divide_k > highscore2 ) { updateHighScore(goodAns_divide_k); }
                                        else {
                                            if ( goodAns_multiple > highscore2 ) { updateHighScore(goodAns_multiple); }
                                            else {
                                                if ( goodAns_add > highscore2 ) {  updateHighScore(goodAns_add); }
                                            }
                                        }
                                    }
                                }
                            }
                        }*/










                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                backToFirstScreen();
            }
        });
    }



    private void updateHighScore( int highscoreNew )
    {
        highscore2 = highscoreNew;

    }

    public void backToFirstScreen() {
        Intent intent = new Intent(this, FirstScreenActivity.class);
        startActivity(intent);
    }
}
