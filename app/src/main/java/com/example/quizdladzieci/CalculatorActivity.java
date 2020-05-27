package com.example.quizdladzieci;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends MenuForAllAcitivity implements View.OnClickListener{
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    public static final String EXTRA_RANGE = "com.example.application.example.EXTRA_RANGE";
    public int no;
    public int range;
    private Button button10;
    private Button button20;
    private Button button100;
    private Button button1000;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Intent intent = getIntent();
        no = intent.getIntExtra(ActivityChose.EXTRA_NUMBER, 0);
        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        button10 = (Button)findViewById(R.id.button10);
        button20 = (Button)findViewById(R.id.button20);
        button100 = (Button)findViewById(R.id.button100);
        button1000 = (Button)findViewById(R.id.button1000);

        button10.setOnClickListener(this);
        button20.setOnClickListener(this);
        button100.setOnClickListener(this);
        button1000.setOnClickListener(this);
    }

    public void openCalculator() {
        Intent intent;
        switch(no){
            case 1:
                intent = new Intent(this, ActivityAdd.class);
                intent.putExtra(EXTRA_RANGE, range);
                intent.putExtra(EXTRA_NUMBER, no);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, ActivityMinus.class);
                intent.putExtra(EXTRA_RANGE, range);
                intent.putExtra(EXTRA_NUMBER, no);
                startActivity(intent);
                break;
        }

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button10:
                range = 10;
                openCalculator();
                break;
            case R.id.button20:
                range = 20;
                openCalculator();
                break;
            case R.id.button100:
                range = 100;
                openCalculator();
                break;
            case R.id.button1000:
                range = 1000;
                openCalculator();
                break;
        }
    }
}
