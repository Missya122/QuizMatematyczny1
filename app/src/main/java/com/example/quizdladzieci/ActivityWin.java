package com.example.quizdladzieci;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityWin extends MenuForAllAcitivity {
    private Toolbar toolbar;

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


        final Intent intent = getIntent();
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
        }

        buttonOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.start();
                backToFirstScreen();
            }
        });
    }

    public void backToFirstScreen() {
        Intent intent = new Intent(this, FirstScreenActivity.class);
        startActivity(intent);
    }
}
