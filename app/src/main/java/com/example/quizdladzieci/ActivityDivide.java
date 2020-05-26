package com.example.quizdladzieci;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.BaseColumns;
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

public class ActivityDivide extends MenuForAllAcitivity {
    public static final String EXTRA_COUNTER = "com.example.application.example.EXTRA_COUNTER";
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";

    private EditText editTextResult;
    private TextView textViewQuestion;
    private TextView textViewScore;
    private Button buttonNext;

    private List<DivideQuestions> divideQuestionsList;
    private int questionCountTotal;
    private DivideQuestions currentQuestion;

    private int counter;
    private int wrongAns;
    private  int goodAns;
    private int gameCounter;
    private int no;
    private boolean answered;
    private int nrQuestion;

    private Toolbar toolbar;

    final Random myRandom = new Random();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divide);

        Intent intent = getIntent();
        no = intent.getIntExtra(ActivityChose.EXTRA_NUMBER, 0);

        textViewQuestion = (TextView)findViewById(R.id.textViewOperation);
        editTextResult = (EditText)findViewById(R.id.editTextResult);
        textViewScore = (TextView)findViewById(R.id.textViewKtory);
        buttonNext = (Button)findViewById(R.id.buttonNext);
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




        CalculatorDbHelper dbHelper = new CalculatorDbHelper(this);
        divideQuestionsList = dbHelper.getAllDivideQuestions();

        questionCountTotal = divideQuestionsList.size();
        Collections.shuffle(divideQuestionsList);

        counter = 0;
        wrongAns = 0;
        goodAns = 0;
        gameCounter = 0;
        nrQuestion = myRandom.nextInt(questionCountTotal-11);
        newGame();

        editTextResult.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mp.start();

                    if (!answered) {
                        if (editTextResult.getText().toString().trim().length() == 0) {
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
                    if (editTextResult.getText().toString().trim().length() == 0) {
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
        editTextResult.setBackgroundColor(Color.WHITE);

        if (gameCounter < 10) {
            currentQuestion = divideQuestionsList.get(nrQuestion);
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
        if (Integer.parseInt(editTextResult.getText().toString()) == currentQuestion.getResult()) {
            editTextResult.setBackgroundColor(Color.GREEN);
            if (gameCounter <= 10) {
                buttonNext.setText("Dalej!");
            } else {
                buttonNext.setText("Sprawdź");
            }
            counter++;
            goodAns++;
        } else {
            editTextResult.setBackgroundColor(Color.RED);
            if (gameCounter <= 10) {
                buttonNext.setText("Dalej!");
            } else {
                buttonNext.setText("Sprawdź");
            }
            wrongAns++;
        }
    }

    public void showMsg() {
        Toast.makeText(this, "Wpisz wynik!", Toast.LENGTH_SHORT).show();
    }

    public void openWin() {
        Intent intent = new Intent(this, ActivityWin.class);
        intent.putExtra(EXTRA_NUMBER, no);
        intent.putExtra(EXTRA_COUNTER, counter);
        intent.putExtra("wrongAns_Divide", wrongAns);
        intent.putExtra("goodAns_Divide", goodAns);
        startActivity(intent);
    }
}