package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitySettings extends AppCompatActivity {
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    public static final String EXTRA_RANGE = "com.example.application.example.EXTRA_RANGE";
    RadioGroup radioGroupRange;
    RadioGroup radioGroup;
    RadioButton radioButton;
    RadioButton radioButtonRange;
    TextView textView;
    TextView textView2;
    TextView textView3;
    int no;
    int range;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        radioGroup = findViewById(R.id.radioGroup);
        radioGroupRange = findViewById(R.id.radioGroupRange);
        textView = findViewById(R.id.text_view_selected);
        textView2 = findViewById(R.id.textViewRange);
        textView3 = findViewById(R.id.textViewDzialanie);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);




        Button buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                int radioId = radioGroup.getCheckedRadioButtonId();
                int radioIdRange = radioGroupRange.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);
                radioButtonRange = findViewById(radioIdRange);

                textView.setText("Wybrałeś: " + radioButton.getText() + " i " + radioButtonRange.getText());

                switch(radioGroupRange.getCheckedRadioButtonId()) {
                    case R.id.radio100:
                        range = 999;
                        break;
                    case R.id.radio1000:
                        range = 9999;
                        break;
                    case R.id.radio10000:
                        range = 99999;
                        break;
                }
                openActivity();
            }
        });
    }

    public void openActivity() {
        Intent intent;
        switch(radioGroup.getCheckedRadioButtonId()) {
            case R.id.radioAdd:
                no = 6;
                intent = new Intent(this, ActivityAddK.class);
                intent.putExtra(EXTRA_RANGE, range);
                intent.putExtra(EXTRA_NUMBER, no);
                startActivity(intent);
                break;
            case R.id.radioMinus:
                no = 7;
                intent = new Intent(this, ActivityMinusK.class);
                intent.putExtra(EXTRA_RANGE, range);
                intent.putExtra(EXTRA_NUMBER, no);
                startActivity(intent);
                break;
        }
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Wybrałeś: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }

    public void checkButtonRange(View v) {
        int radioIdRange = radioGroupRange.getCheckedRadioButtonId();

        radioButtonRange = findViewById(radioIdRange);

        Toast.makeText(this, "Wybrany zakres to: " + radioButtonRange.getText(),
                Toast.LENGTH_SHORT).show();
    }
}
