package com.example.quizdladzieci;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import javax.security.auth.callback.Callback;

import static com.example.quizdladzieci.MainActivity.SHARED_PREFS;
import static com.example.quizdladzieci.QuizActivity.EXTRA_SCORE;

public class FinishQuiz extends AppCompatActivity {

    Button button_return;

    public TextView score_view;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private  int highscore2 = 0;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_quiz);

        Intent intent = getIntent();

        final int score = intent.getIntExtra("score", -1);

        final int wrongAns = intent.getIntExtra("wrongAns",-2);

        final int question_count = intent.getIntExtra("question_count", -3);

        score_view = findViewById(R.id.tvScore);

        score_view.setText("Twój wynik: " + score);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        loadHighscore();

        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE,score);
        setResult(RESULT_OK, resultIntent);

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        button_return = findViewById(R.id.btnReturn);

        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                button_return.setText(String.valueOf(userProfile.userBestScoreQuiz));
                //String bestQuiz = newUserName.getText().toString();
                //UserProfile userProfile = new UserProfile(age, email, name,0,0,0,0,0,0);
                //                    databaseReference.setValue(userProfile);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FinishQuiz.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        }); */

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                userProfile.userGoodAnswersQuiz = score + userProfile.getUserGoodAnswersQuiz();
                button_return.setText(String.valueOf(userProfile.userGoodAnswersQuiz));
                if ( score > highscore2 ) {
                    updateHighScore(score);
                }


                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("userGoodAnswersQuiz");

                myRef.setValue(userProfile.userGoodAnswersQuiz);

                userProfile.userBestScoreQuiz = highscore2;

                DatabaseReference myRef_highscore = firebaseDatabase.getReference(firebaseAuth.getUid()).child("userBestScoreQuiz");

                myRef_highscore.setValue(userProfile.userBestScoreQuiz);

                userProfile.userBadAnswersQuiz = wrongAns + userProfile.getUserBadAnswersQuiz();
                DatabaseReference myRef_bad = firebaseDatabase.getReference(firebaseAuth.getUid()).child("userBadAnswersQuiz");

                myRef_bad.setValue(userProfile.userBadAnswersQuiz);

                userProfile.userTotalQuestionsQuiz = question_count + userProfile.getUserTotalQuestionsQuiz();
                DatabaseReference myRef_total = firebaseDatabase.getReference(firebaseAuth.getUid()).child("userTotalQuestionsQuiz");

                myRef_total.setValue(userProfile.userTotalQuestionsQuiz);









            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToMainScreen();
            }
        });




    }
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
        if ( score > highscore2 ) {
            updateHighScore(score);
        }
    }*/
    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore2 = prefs.getInt(KEY_HIGHSCORE,0);

    }
    private void updateHighScore( int highscoreNew )
    {
        highscore2 = highscoreNew;


        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore2);
        editor.apply();
    }


    private void goToMainScreen() {
        Intent intent = new Intent(this, FirstScreenActivity.class);
        startActivity(intent);
    }

}
