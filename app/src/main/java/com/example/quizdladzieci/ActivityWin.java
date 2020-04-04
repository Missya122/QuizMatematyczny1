package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityWin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        int counter;
        TextView textView = (TextView)findViewById(R.id.textView2);

        Intent intent = getIntent();
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
        }
    }
}
