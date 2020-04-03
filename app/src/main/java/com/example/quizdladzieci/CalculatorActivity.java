package com.example.quizdladzieci;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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

public class CalculatorActivity extends AppCompatActivity {

    private EditText editTextResult;
    private Button buttonCheck;
    private TextView textViewMsg;
    private TextView editTextOne;
    private TextView editTextTwo;

    public void checkSum() {
        int sum = Integer.parseInt(editTextOne.getText().toString()) + Integer.parseInt(editTextTwo.getText().toString());
        String msg = "";
        int result = Integer.parseInt(editTextResult.getText().toString());
        if(result == sum) {
            msg = "Brawo! To prawidłowy wynik, zagraj jeszcze raz!";
            newGame();
        }
        else {
            msg = "Niestety, zły wynik. Spróbuj ponownie";
        }
        textViewMsg.setText(msg);
        editTextResult.requestFocus();
        editTextResult.selectAll();
    }
    public void newGame() {
        int theNumberOne = (int)(Math.random() * 100 + 1);
        editTextOne.setText(Integer.toString(theNumberOne));
        int theNumberTwo = (int)(Math.random() * (100 - theNumberOne));
        editTextTwo.setText(Integer.toString(theNumberTwo));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        editTextResult = (EditText) findViewById(R.id.editTextResult);
        editTextOne = (TextView) findViewById(R.id.editTextOne);
        editTextTwo = (TextView) findViewById(R.id.editTextTwo);
        buttonCheck = (Button) findViewById(R.id.buttonCheck);
        textViewMsg = (TextView) findViewById(R.id.textViewMsg);
        newGame();
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSum();
            }
        });
        editTextResult.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkSum();
                return true;
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
