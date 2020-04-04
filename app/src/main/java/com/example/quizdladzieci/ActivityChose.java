package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityChose extends AppCompatActivity {
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    public int no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);

        Intent intent = getIntent();
        no = intent.getIntExtra(FirstScreenActivity.EXTRA_NUMBER, 0);

        Button buttonQuiz = findViewById(R.id.buttonQuiz);
        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToQuizScreen();
            }
        });

        Button buttonCalc = findViewById(R.id.buttonCalc);
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCalculatorScreen();
            }
        });
    }

    private void goToQuizScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_NUMBER, no);
        startActivity(intent);
    }
    private void goToCalculatorScreen() {
        Intent intent;

        if(no == 1 || no == 2){
            intent = new Intent(this, CalculatorActivity.class);
            intent.putExtra(EXTRA_NUMBER, no);
            startActivity(intent);
        } else if(no == 3){
            /*intent = new Intent(this, ActivityMultiple.class);
            startActivity(intent);*/
        } else if(no == 4){
            /*intent = new Intent(this, ActivityDivide.class);
            startActivity(intent);*/
        } else if(no == 5){
           /* intent = new Intent(this, ActivityDivide2.class);
            startActivity(intent);*/
        }
    }
}
