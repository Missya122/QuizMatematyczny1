package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityChose extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    private Button buttonAdd;
    private Button buttonMinus;
    private Button buttonMultiple;
    private Button buttonDivide;
    private Button buttonDivide2;
    public int no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonMultiple = (Button) findViewById(R.id.buttonMultiple);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonDivide2 = (Button) findViewById(R.id.buttonDivide2);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);

        buttonAdd.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonMultiple.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonDivide2.setOnClickListener(this);



    }


    public void openActivity() {
        Intent intent;
        if(no == 1 || no == 2){
            intent = new Intent(this, CalculatorActivity.class);
            intent.putExtra(EXTRA_NUMBER, no);
            startActivity(intent);
        } else if(no == 3){
            intent = new Intent(this, ActivityMultiple.class);
            intent.putExtra(EXTRA_NUMBER, no);
            startActivity(intent);
        } else if(no == 4){
            intent = new Intent(this, ActivityDivide.class);
            intent.putExtra(EXTRA_NUMBER, no);
            startActivity(intent);
        } else if(no == 5){
           /* intent = new Intent(this, ActivityDivide2.class);
            startActivity(intent);*/
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonAdd:
                no = 1;
                openActivity();
                break;
            case R.id.buttonMinus:
                no = 2;
                openActivity();
                break;
            case R.id.buttonMultiple:
                no = 3;
                openActivity();
                break;
            case R.id.buttonDivide:
                no = 4;
                openActivity();
                break;
            case R.id.buttonDivide2:
                no = 5;
                openActivity();
                break;
        }
    }
}
