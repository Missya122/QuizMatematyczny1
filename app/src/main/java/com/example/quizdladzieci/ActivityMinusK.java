package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

public class ActivityMinusK extends AppCompatActivity {

    private  int no;
    private  int range;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minus_k);

        Intent intent = getIntent();
        no = intent.getIntExtra(ActivitySettings.EXTRA_NUMBER, 0);
        range = intent.getIntExtra(ActivitySettings.EXTRA_RANGE, 0);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);
        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);

        Toast.makeText(this, "Numer aktywności: " + no + ", zakres: " + range,
                Toast.LENGTH_SHORT).show();
    }
}
