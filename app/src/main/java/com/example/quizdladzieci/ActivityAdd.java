package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActivityAdd extends AppCompatActivity {
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


    public void newGame() {
        gameCounter++;
        editTextResult.setText(null);
        textView1.setText(gameCounter + "/10");

        numberOne = myRandom.nextInt(range);
        numberTwo = myRandom.nextInt(range - numberOne);

        textViewOperation.setText(numberOne + " + " + numberTwo + " = ");
    }

    public void checkResult() {
        if (Integer.parseInt(editTextResult.getText().toString()) == numberOne + numberTwo) {
            counter++;
        }
        newGame();
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

        counter = 0;
        gameCounter = 0;
        newGame();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();



                    if (gameCounter == 10) {
                        if (Integer.parseInt(editTextResult.getText().toString()) == numberOne * numberTwo) {
                            counter++;
                        }
                        openWin();
                    } else checkResult();
                }
        });

    }

    public void openWin() {
        Intent intent = new Intent(this, ActivityWin.class);
        intent.putExtra(EXTRA_NUMBER, no);
        intent.putExtra(EXTRA_COUNTER, counter);
        startActivity(intent);
    }
}
