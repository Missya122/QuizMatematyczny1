package com.example.quizdladzieci;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityChose extends MenuForAllAcitivity implements View.OnClickListener{
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    private Button buttonAdd;
    private Button buttonMinus;
    private Button buttonMultiple;
    private Button buttonDivide;
    private Button buttonDivide2;
    private Button buttonKreska;
    public int no = 0;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonMultiple = (Button) findViewById(R.id.buttonMultiple);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonDivide2 = (Button) findViewById(R.id.buttonDivide2);
        buttonKreska = (Button) findViewById(R.id.buttonK);

        final MediaPlayer mp;
        mp = MediaPlayer.create(this, R.raw.sample);
        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);


        buttonAdd.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonMultiple.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonDivide2.setOnClickListener(this);
        buttonKreska.setOnClickListener(this);


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
            intent = new Intent(this, ActivityDivide2.class);
            intent.putExtra(EXTRA_NUMBER, no);
            startActivity(intent);
        } else {
            intent = new Intent(this, ActivitySettings.class);
            startActivity(intent);
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
            case R.id.buttonK:
                openActivity();
                break;
        }
    }
}
