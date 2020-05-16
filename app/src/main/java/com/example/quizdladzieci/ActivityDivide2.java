package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ActivityDivide2 extends MenuForAllAcitivity {
    public static final String EXTRA_COUNTER = "com.example.application.example.EXTRA_COUNTER";
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";

    private EditText editTextResult;
    private EditText editTextRest;
    private TextView textViewQuestion;
    private TextView textViewScore;
    private Button buttonNext;

    private List<DivideRestQuestions> divideRestQuestionsList;
    private int questionCountTotal;
    private DivideRestQuestions currentQuestion;

    private int counter;
    private int gameCounter;
    private int no;
    private boolean answered;
    private int nrQuestion;

    private Toolbar toolbar;

    final Random myRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divide2);

        Intent intent = getIntent();
        no = intent.getIntExtra(ActivityChose.EXTRA_NUMBER, 0);

        textViewQuestion = (TextView)findViewById(R.id.textViewOperation);
        editTextResult = (EditText)findViewById(R.id.editTextWynik);
        editTextRest = (EditText)findViewById(R.id.editTextReszta);
        textViewScore = (TextView)findViewById(R.id.textViewKtory);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);
        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);

        DbHelper2 dbHelper = new DbHelper2(this);
        divideRestQuestionsList = dbHelper.getAllDivideRestQuestions();

        questionCountTotal = divideRestQuestionsList.size();
        Collections.shuffle(divideRestQuestionsList);

        counter = 0;
        gameCounter = 0;
        nrQuestion = myRandom.nextInt(questionCountTotal-11);
        newGame();

        editTextResult.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mp.start();

                    if (!answered) {
                        if (editTextResult.getText().toString().trim().length() == 0 ||
                            editTextRest.getText().toString().trim().length() == 0) {
                            showMsg();
                        } else {
                            checkResult();
                        }
                    } else {
                        newGame();
                    }
                }
                return false;
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                if (!answered) {
                    if (editTextResult.getText().toString().trim().length() == 0 ||
                            editTextRest.getText().toString().trim().length() == 0) {
                        showMsg();
                    } else {
                        checkResult();
                    }
                } else {
                    newGame();
                }
            }
        });
    }

    public void newGame() {
        editTextResult.setText("");
        editTextRest.setText("");
        editTextResult.setBackgroundColor(Color.WHITE);
        editTextRest.setBackgroundColor(Color.WHITE);
        editTextResult.setFocusableInTouchMode(true);
        editTextResult.requestFocus();

        if (gameCounter < 10) {
            currentQuestion = divideRestQuestionsList.get(nrQuestion);
            textViewQuestion.setText(currentQuestion.getQuestion());

            nrQuestion++;
            gameCounter++;
            textViewScore.setText(gameCounter + "/10");
            answered = false;
            buttonNext.setText("Potwierdź!");
        } else {
            openWin();
        }
    }

    public void checkResult() {
        answered = true;
        if (Integer.parseInt(editTextResult.getText().toString()) == currentQuestion.getResult() &&
                Integer.parseInt(editTextRest.getText().toString()) == currentQuestion.getRest()) {
            editTextResult.setBackgroundColor(Color.GREEN);
            editTextRest.setBackgroundColor(Color.GREEN);
            if (gameCounter <= 10) {
                buttonNext.setText("Dalej!");
            } else {
                buttonNext.setText("Sprawdź");
            }
            counter++;
        } else {
            editTextResult.setBackgroundColor(Color.RED);
            editTextRest.setBackgroundColor(Color.RED);
            if (gameCounter <= 10) {
                buttonNext.setText("Dalej!");
            } else {
                buttonNext.setText("Sprawdź");
            }
        }
    }

    public void showMsg() {
        Toast.makeText(this, "Wpisz wynik!", Toast.LENGTH_SHORT).show();
    }

    public void openWin() {
        Intent intent = new Intent(this, ActivityWin.class);
        intent.putExtra(EXTRA_NUMBER, no);
        intent.putExtra(EXTRA_COUNTER, counter);
        startActivity(intent);
    }
}
