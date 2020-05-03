package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActivityAddK extends AppCompatActivity {
    public static final String EXTRA_COUNTER = "com.example.application.example.EXTRA_COUNTER";
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    private  int no;
    private  int range;
    private int counter;
    private int gameCounter;
    private int flaga = 0;
    private boolean pom;
    final Random myRandom = new Random();
    private boolean answered;
    private Toolbar toolbar;

    private TextView textViewLicznik;
    private TextView textView1_1;
    private TextView textView1_2;
    private TextView textView1_3;
    private TextView textView1_4;
    private TextView textView1_5;
    private TextView textView1_6;
    private TextView textView2_1;
    private TextView textView2_2;
    private TextView textView2_3;
    private TextView textView2_4;
    private TextView textView2_5;
    private TextView textView2_6;
    private TextView textView3_1;
    private TextView textView3_2;
    private TextView textView3_3;
    private TextView textView3_4;
    private TextView textView3_5;
    private TextView textView3_6;
    private EditText editText5_1;
    private EditText editText5_2;
    private EditText editText5_3;
    private EditText editText5_4;
    private EditText editText5_5;
    private EditText editText5_6;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_k);

        Intent intent = getIntent();
        no = intent.getIntExtra(ActivitySettings.EXTRA_NUMBER, 0);
        range = intent.getIntExtra(ActivitySettings.EXTRA_RANGE, 0);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);
        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);

        textViewLicznik = (TextView)findViewById(R.id.textViewLicznik);
        textView1_1 = (TextView)findViewById(R.id.textView1_1);
        textView1_2 = (TextView)findViewById(R.id.textView1_2);
        textView1_3 = (TextView)findViewById(R.id.textView1_3);
        textView1_4 = (TextView)findViewById(R.id.textView1_4);
        textView1_5 = (TextView)findViewById(R.id.textView1_5);
        textView1_6 = (TextView)findViewById(R.id.textView1_6);
        textView2_1 = (TextView)findViewById(R.id.textView2_1);
        textView2_2 = (TextView)findViewById(R.id.textView2_2);
        textView2_3 = (TextView)findViewById(R.id.textView2_3);
        textView2_4 = (TextView)findViewById(R.id.textView2_4);
        textView2_5 = (TextView)findViewById(R.id.textView2_5);
        textView2_6 = (TextView)findViewById(R.id.textView2_6);
        textView3_1 = (TextView)findViewById(R.id.textView3_1);
        textView3_2 = (TextView)findViewById(R.id.textView3_2);
        textView3_3 = (TextView)findViewById(R.id.textView3_3);
        textView3_4 = (TextView)findViewById(R.id.textView3_4);
        textView3_5 = (TextView)findViewById(R.id.textView3_5);
        textView3_6 = (TextView)findViewById(R.id.textView3_6);
        editText5_1 = (EditText) findViewById(R.id.editText5_1);
        editText5_2 = (EditText) findViewById(R.id.editText5_2);
        editText5_3 = (EditText) findViewById(R.id.editText5_3);
        editText5_4 = (EditText) findViewById(R.id.editText5_4);
        editText5_5 = (EditText) findViewById(R.id.editText5_5);
        editText5_6 = (EditText) findViewById(R.id.editText5_6);
        buttonNext = (Button)findViewById(R.id.buttonNext);

        counter = 0;
        gameCounter = 0;
        newGame();

        editText5_6.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (editText5_6.getText().toString().trim().length() != 0) {
                    if (Integer.parseInt(textView2_6.getText().toString()) + Integer.parseInt(textView3_6.getText().toString()) > 9) {
                        textView1_5.setText("1");
                        pom = true;

                        if ((Integer.parseInt(textView2_6.getText().toString()) + Integer.parseInt(textView3_6.getText().toString())) - 10 ==
                                Integer.parseInt(editText5_6.getText().toString())) {
                            flaga += 0;
                        } else flaga += 1;
                    } else {
                        if ((Integer.parseInt(textView2_6.getText().toString()) + Integer.parseInt(textView3_6.getText().toString())) ==
                                Integer.parseInt(editText5_6.getText().toString())) {
                            flaga += 0;
                        } else flaga += 1;
                        pom = false;
                    }

                    editText5_5.setFocusableInTouchMode(true);
                    editText5_5.requestFocus();
                }
            }
        });

        editText5_5.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (editText5_5.getText().toString().trim().length() != 0) {
                    if(pom == true) {
                    if (Integer.parseInt(textView2_5.getText().toString()) + Integer.parseInt(textView3_5.getText().toString()) +
                            Integer.parseInt(textView1_5.getText().toString()) > 9) {
                        textView1_4.setText("1");
                        pom = true;

                        if ((Integer.parseInt(textView2_5.getText().toString()) + Integer.parseInt(textView3_5.getText().toString()) +
                                Integer.parseInt(textView1_5.getText().toString())) - 10 ==
                                Integer.parseInt(editText5_5.getText().toString())) {
                            flaga += 0;
                        } else flaga += 1;
                    } else {
                        if ((Integer.parseInt(textView2_5.getText().toString()) + Integer.parseInt(textView3_5.getText().toString()) +
                                Integer.parseInt(textView1_5.getText().toString())) ==
                                Integer.parseInt(editText5_5.getText().toString())) {
                            flaga += 0;
                        } else flaga += 1;
                        pom = false;
                    }
                } else {
                    if (Integer.parseInt(textView2_5.getText().toString()) + Integer.parseInt(textView3_5.getText().toString()) > 9) {
                        textView1_4.setText("1");
                        pom = true;

                        if ((Integer.parseInt(textView2_5.getText().toString()) + Integer.parseInt(textView3_5.getText().toString())) - 10 ==
                                Integer.parseInt(editText5_5.getText().toString())) {
                            flaga += 0;
                        } else flaga += 1;
                    } else {
                        if ((Integer.parseInt(textView2_5.getText().toString()) + Integer.parseInt(textView3_5.getText().toString())) ==
                                Integer.parseInt(editText5_5.getText().toString())) {
                            flaga += 0;
                        } else flaga += 1;
                        pom = false;
                    }
                }
                editText5_4.setFocusableInTouchMode(true);
                editText5_4.requestFocus();
            }
            }
        });

        editText5_4.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (editText5_4.getText().toString().trim().length() != 0) {
                    if (pom == true) {
                        if (Integer.parseInt(textView2_4.getText().toString()) + Integer.parseInt(textView3_4.getText().toString()) +
                                Integer.parseInt(textView1_4.getText().toString()) > 9) {
                            textView1_3.setText("1");
                            pom = true;

                            if ((Integer.parseInt(textView2_4.getText().toString()) + Integer.parseInt(textView3_4.getText().toString()) +
                                    Integer.parseInt(textView1_4.getText().toString())) - 10 ==
                                    Integer.parseInt(editText5_4.getText().toString())) {
                                flaga += 0;
                            } else flaga += 1;
                        } else {
                            if ((Integer.parseInt(textView2_4.getText().toString()) + Integer.parseInt(textView3_4.getText().toString()) +
                                    Integer.parseInt(textView1_4.getText().toString())) ==
                                    Integer.parseInt(editText5_4.getText().toString())) {
                                flaga += 0;
                            } else flaga += 1;
                            pom = false;
                        }
                    } else {
                        if (Integer.parseInt(textView2_4.getText().toString()) + Integer.parseInt(textView3_4.getText().toString()) > 9) {
                            textView1_3.setText("1");
                            pom = true;

                            if ((Integer.parseInt(textView2_4.getText().toString()) + Integer.parseInt(textView3_4.getText().toString())) - 10 ==
                                    Integer.parseInt(editText5_4.getText().toString())) {
                                flaga += 0;
                            } else flaga += 1;
                        } else {
                            if ((Integer.parseInt(textView2_4.getText().toString()) + Integer.parseInt(textView3_4.getText().toString())) ==
                                    Integer.parseInt(editText5_4.getText().toString())) {
                                flaga += 0;
                            } else flaga += 1;
                            pom = false;
                        }
                    }
                    editText5_3.setFocusableInTouchMode(true);
                    editText5_3.requestFocus();
                }
            }
        });

        editText5_3.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (editText5_3.getText().toString().trim().length() != 0) {
                    if (range == 999) {
                        if (pom == true) {
                            if (Integer.parseInt(textView1_3.getText().toString()) ==
                                    Integer.parseInt(editText5_3.getText().toString())) {
                                flaga += 0;
                            } else flaga += 1;
                        }
                    } else {

                        if (pom == true) {
                            if (Integer.parseInt(textView2_3.getText().toString()) + Integer.parseInt(textView3_3.getText().toString()) +
                                    Integer.parseInt(textView1_3.getText().toString()) > 9) {
                                textView1_2.setText("1");
                                pom = true;

                                if ((Integer.parseInt(textView2_3.getText().toString()) + Integer.parseInt(textView3_3.getText().toString()) +
                                        Integer.parseInt(textView1_3.getText().toString())) - 10 ==
                                        Integer.parseInt(editText5_3.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                            } else {
                                if ((Integer.parseInt(textView2_3.getText().toString()) + Integer.parseInt(textView3_3.getText().toString()) +
                                        Integer.parseInt(textView1_3.getText().toString())) ==
                                        Integer.parseInt(editText5_3.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = false;
                            }
                        } else {
                            if (Integer.parseInt(textView2_3.getText().toString()) + Integer.parseInt(textView3_3.getText().toString()) > 9) {
                                textView1_2.setText("1");
                                pom = true;

                                if ((Integer.parseInt(textView2_3.getText().toString()) + Integer.parseInt(textView3_3.getText().toString())) - 10 ==
                                        Integer.parseInt(editText5_3.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                            } else {
                                if ((Integer.parseInt(textView2_3.getText().toString()) + Integer.parseInt(textView3_3.getText().toString())) ==
                                        Integer.parseInt(editText5_3.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = false;
                            }
                        }
                    }
                    editText5_2.setFocusableInTouchMode(true);
                    editText5_2.requestFocus();
                }
            }
        });

        editText5_2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (editText5_2.getText().toString().trim().length() != 0) {
                    if (range == 9999) {
                        if (pom == true) {
                            if (Integer.parseInt(textView1_2.getText().toString()) ==
                                    Integer.parseInt(editText5_2.getText().toString())) {
                                flaga += 0;
                            } else flaga += 1;
                        }
                    } else {

                        if (pom == true) {
                            if (Integer.parseInt(textView2_2.getText().toString()) + Integer.parseInt(textView3_2.getText().toString()) +
                                    Integer.parseInt(textView1_2.getText().toString()) > 9) {
                                textView1_1.setText("1");
                                pom = true;

                                if ((Integer.parseInt(textView2_2.getText().toString()) + Integer.parseInt(textView3_2.getText().toString()) +
                                        Integer.parseInt(textView1_2.getText().toString())) - 10 ==
                                        Integer.parseInt(editText5_2.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                            } else {
                                if ((Integer.parseInt(textView2_2.getText().toString()) + Integer.parseInt(textView3_2.getText().toString()) +
                                        Integer.parseInt(textView1_2.getText().toString())) ==
                                        Integer.parseInt(editText5_2.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = false;
                            }
                        } else {
                            if (Integer.parseInt(textView2_2.getText().toString()) + Integer.parseInt(textView3_2.getText().toString()) > 9) {
                                textView1_1.setText("1");
                                pom = true;

                                if ((Integer.parseInt(textView2_2.getText().toString()) + Integer.parseInt(textView3_2.getText().toString())) - 10 ==
                                        Integer.parseInt(editText5_2.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                            } else {
                                if ((Integer.parseInt(textView2_2.getText().toString()) + Integer.parseInt(textView3_2.getText().toString())) ==
                                        Integer.parseInt(editText5_2.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = false;
                            }
                        }
                    }
                    editText5_1.setFocusableInTouchMode(true);
                    editText5_1.requestFocus();
                }
            }
        });

        editText5_1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (editText5_1.getText().toString().trim().length() != 0) {
                    if (range == 99999) {
                        if (pom == true) {
                            if (Integer.parseInt(textView1_1.getText().toString()) ==
                                    Integer.parseInt(editText5_1.getText().toString())) {
                                flaga += 0;
                            } else flaga += 1;
                        }
                    }
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if (!answered) {
                    if (editText5_6.getText().toString().trim().length() == 0) {
                        showMsg();
                    } else {
                        checkResult();
                    }
                } else {
                    newGame();
                }
            }
        });
    }

    public void newGame() {
        clear();
        editText5_6.setFocusableInTouchMode(true);
        editText5_6.requestFocus();

        if (gameCounter < 10) {
            losuj();

            gameCounter++;
            textViewLicznik.setText(gameCounter + "/10");
            answered = false;
            buttonNext.setBackgroundColor(Color.parseColor("#FF00BCD4"));
            buttonNext.setText("Potwierdź!");
        } else {
            openWin();
        }
    }

    public void checkResult() {
        answered = true;
        if(flaga == 0) {
            buttonNext.setBackgroundColor(Color.GREEN);
            if (gameCounter <= 10) {
                buttonNext.setText("Dalej!");
            } else {
                buttonNext.setText("Sprawdź");
            }
            counter++;
        } else if(flaga > 0){
            buttonNext.setBackgroundColor(Color.RED);
            if (gameCounter <= 10) {
                buttonNext.setText("Dalej!");
            } else {
                buttonNext.setText("Sprawdź");
            }
        }
    }

    public void clear() {
        editText5_1.setText("");
        editText5_1.setBackgroundColor(Color.WHITE);
        editText5_2.setBackgroundColor(Color.WHITE);
        editText5_3.setBackgroundColor(Color.WHITE);
        editText5_4.setBackgroundColor(Color.WHITE);
        editText5_5.setBackgroundColor(Color.WHITE);
        editText5_6.setBackgroundColor(Color.WHITE);
        textView1_1.setText("");
        editText5_2.setText("");
        textView1_2.setText("");
        editText5_3.setText("");
        textView1_3.setText("");
        editText5_4.setText("");
        textView1_4.setText("");
        editText5_5.setText("");
        textView1_5.setText("");
        editText5_6.setText("");
        textView1_6.setText("");
    }

    public void losuj() {
        if(range == 999) {
            textView2_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_5.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_4.setText(String.valueOf(myRandom.nextInt(9) + 1));

            textView3_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_5.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_4.setText(String.valueOf(myRandom.nextInt(9) + 1));
        } else if(range == 9999) {
            textView2_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_5.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_4.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_3.setText(String.valueOf(myRandom.nextInt(9) + 1));

            textView3_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_5.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_4.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_3.setText(String.valueOf(myRandom.nextInt(9) + 1));
        } else if(range == 99999) {
            textView2_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_5.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_4.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_3.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_2.setText(String.valueOf(myRandom.nextInt(9) + 1));

            textView3_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_5.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_4.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_3.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_2.setText(String.valueOf(myRandom.nextInt(9) + 1));
        }

    }

    public void showMsg() {
        Toast.makeText(this, "Wpisz wynik!", Toast.LENGTH_SHORT).show();
    }

    public void openWin() {
        Intent intent = new Intent(this, ActivityWin.class);
        intent.putExtra(EXTRA_NUMBER, no);
        intent.putExtra(EXTRA_COUNTER, counter);
        startActivity(intent);
    }
}
