package com.example.quizdladzieci;

import androidx.appcompat.app.ActionBar;
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

import java.util.Random;

public class ActivityAdd extends MenuForAllAcitivity {
    public static final String EXTRA_COUNTER = "com.example.application.example.EXTRA_COUNTER";
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    private EditText editTextResult;
    private TextView textViewOperation;
    private TextView textView1;
    private Button buttonNext;
    private int numberOne;
    private int numberTwo;
    private int counter;
    private int gameCounter;
    private int range;
    private int no;
    private int pom = 0;
    final Random myRandom = new Random();
    private Toolbar toolbar;
    private boolean answered;


    public void newGame() {
        editTextResult.setText("");
        editTextResult.setBackgroundColor(Color.WHITE);

        if (gameCounter < 10) {
            numberOne = myRandom.nextInt(range);
            numberTwo = myRandom.nextInt(range - numberOne);

            textViewOperation.setText(numberOne + " + " + numberTwo + " = ");

            gameCounter++;
            textView1.setText(gameCounter + "/10");
            answered = false;
            buttonNext.setText("Potwierdź!");
        } else {
            openWin();
        }
    }

    public void checkResult() {
        answered = true;
        if (Integer.parseInt(editTextResult.getText().toString()) == numberOne + numberTwo) {
            editTextResult.setBackgroundColor(Color.GREEN);
            if (gameCounter <= 10) {
                buttonNext.setText("Dalej!");
            } else {
                buttonNext.setText("Sprawdź");
            }
            counter++;
        } else {
            editTextResult.setBackgroundColor(Color.RED);
            if (gameCounter <= 10) {
                buttonNext.setText("Dalej!");
            } else {
                buttonNext.setText("Sprawdź");
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();
        range = intent.getIntExtra(CalculatorActivity.EXTRA_RANGE, 0);
        no = intent.getIntExtra(CalculatorActivity.EXTRA_NUMBER, 0);

        textViewOperation = (TextView) findViewById(R.id.textViewOperation);
        textView1 = (TextView) findViewById(R.id.textViewKtory);
        editTextResult = (EditText) findViewById(R.id.editTextResult);
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


        counter = 0;
        gameCounter = 0;
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
