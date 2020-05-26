package com.example.quizdladzieci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ActivityMinusK extends MenuForAllAcitivity {
    public static final String EXTRA_COUNTER = "com.example.application.example.EXTRA_COUNTER";
    public static final String EXTRA_NUMBER = "com.example.application.example.EXTRA_NUMBER";
    private  int no;
    private  int range;
    private int counter;
    private int goodAns;
    private int wrongAns;
    private int gameCounter;
    private int flaga = 0;
    private boolean pom;
    final Random myRandom = new Random();
    private boolean answered;
    private Toolbar toolbar;

    private TextView textViewLicznik;
    private EditText editText1_2;
    private EditText editText1_3;
    private EditText editText1_4;
    private EditText editText1_5;
    private EditText editText1_6;
    private TextView textView_1;
    private TextView textView_2;
    private TextView textView_3;
    private TextView textView_4;
    private TextView textView_5;
    private TextView textView_6;
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
        setContentView(R.layout.activity_minus_k);

        Intent intent = getIntent();
        no = intent.getIntExtra(ActivitySettings.EXTRA_NUMBER, 0);
        range = intent.getIntExtra(ActivitySettings.EXTRA_RANGE, 0);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.sample);
        toolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textViewLicznik = (TextView)findViewById(R.id.textViewLicznik);
        editText1_2 = (EditText)findViewById(R.id.editText1_2);
        editText1_3 = (EditText)findViewById(R.id.editText1_3);
        editText1_4 = (EditText)findViewById(R.id.editText1_4);
        editText1_5 = (EditText)findViewById(R.id.editText1_5);
        editText1_6 = (EditText)findViewById(R.id.editText1_6);
        textView_1 = (TextView)findViewById(R.id.textView_1);
        textView_2 = (TextView)findViewById(R.id.textView_2);
        textView_3 = (TextView)findViewById(R.id.textView_3);
        textView_4 = (TextView)findViewById(R.id.textView_4);
        textView_5 = (TextView)findViewById(R.id.textView_5);
        textView_6 = (TextView)findViewById(R.id.textView_6);
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
        goodAns = 0;
        wrongAns = 0;
        gameCounter = 0;
        newGame();

        editText1_6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textView2_6.setTextColor(Color.parseColor("#550B0A0A"));
                textView2_5.setTextColor(Color.parseColor("#550B0A0A"));
                textView_5.setText(String.valueOf(Integer.parseInt(textView2_5.getText().toString()) - 1));
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editText1_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                textView2_5.setTextColor(Color.parseColor("#550B0A0A"));
                textView2_4.setTextColor(Color.parseColor("#550B0A0A"));
                textView_4.setText(String.valueOf(Integer.parseInt(textView2_4.getText().toString()) - 1));
            }
        });

        editText1_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(textView2_3.getText().toString().trim().length() != 0) {
                    textView2_4.setTextColor(Color.parseColor("#550B0A0A"));
                    textView2_3.setTextColor(Color.parseColor("#550B0A0A"));
                    textView_3.setText(String.valueOf(Integer.parseInt(textView2_3.getText().toString()) - 1));
                }
            }
        });

        editText1_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if(textView2_2.getText().toString().trim().length() != 0) {
                    textView2_3.setTextColor(Color.parseColor("#550B0A0A"));
                    textView2_2.setTextColor(Color.parseColor("#550B0A0A"));
                    textView_2.setText(String.valueOf(Integer.parseInt(textView2_2.getText().toString()) - 1));
                }
            }
        });

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
                    if(Integer.parseInt(textView2_6.getText().toString()) < Integer.parseInt(textView3_6.getText().toString())) {

                        if ((Integer.parseInt(editText1_6.getText().toString()) - Integer.parseInt(textView3_6.getText().toString())) ==
                                Integer.parseInt(editText5_6.getText().toString())) {
                            flaga += 0;
                        } else flaga += 1;
                        pom = true;
                    } else {
                        if ((Integer.parseInt(textView2_6.getText().toString()) - Integer.parseInt(textView3_6.getText().toString())) ==
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
                        if(editText1_5.getText().toString().trim().length() != 0) {
                            if ((Integer.parseInt(editText1_5.getText().toString()) - Integer.parseInt(textView3_5.getText().toString())) ==
                                    Integer.parseInt(editText5_5.getText().toString())) {
                                flaga += 0;
                            } else flaga += 1;
                            pom = true;

                        } else {
                            if ((Integer.parseInt(textView_5.getText().toString()) - Integer.parseInt(textView3_5.getText().toString())) ==
                                    Integer.parseInt(editText5_5.getText().toString())) {
                                flaga += 0;
                            } else flaga += 1;
                            pom = false;
                        }

                    } else {
                        if(editText1_5.getText().toString().trim().length() != 0) {
                            if ((Integer.parseInt(editText1_5.getText().toString()) - Integer.parseInt(textView3_5.getText().toString())) ==
                                    Integer.parseInt(editText5_5.getText().toString())) {
                                flaga += 0;
                            } else flaga += 1;
                            pom = true;
                        } else {
                            if ((Integer.parseInt(textView2_5.getText().toString()) - Integer.parseInt(textView3_5.getText().toString())) ==
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
                        if(pom == true) {
                            if(editText1_4.getText().toString().trim().length() != 0) {
                                if ((Integer.parseInt(editText1_4.getText().toString()) - Integer.parseInt(textView3_4.getText().toString())) ==
                                        Integer.parseInt(editText5_4.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = true;

                            } else {
                                if ((Integer.parseInt(textView_4.getText().toString()) - Integer.parseInt(textView3_4.getText().toString())) ==
                                        Integer.parseInt(editText5_4.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = false;
                            }
                        } else {
                            if(editText1_4.getText().toString().trim().length() != 0) {
                                if ((Integer.parseInt(editText1_4.getText().toString()) - Integer.parseInt(textView3_4.getText().toString())) ==
                                        Integer.parseInt(editText5_4.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = true;
                            } else {
                                if ((Integer.parseInt(textView2_4.getText().toString()) - Integer.parseInt(textView3_4.getText().toString())) ==
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
                        if(pom == true) {
                            if(editText1_3.getText().toString().trim().length() != 0) {
                                if ((Integer.parseInt(editText1_3.getText().toString()) - Integer.parseInt(textView3_3.getText().toString())) ==
                                        Integer.parseInt(editText5_3.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = true;

                            } else {
                                if ((Integer.parseInt(textView_3.getText().toString()) - Integer.parseInt(textView3_3.getText().toString())) ==
                                        Integer.parseInt(editText5_3.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = false;
                            }

                        } else {
                            if(editText1_3.getText().toString().trim().length() != 0) {
                                if ((Integer.parseInt(editText1_3.getText().toString()) - Integer.parseInt(textView3_3.getText().toString())) ==
                                        Integer.parseInt(editText5_3.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = true;
                            } else {
                                if ((Integer.parseInt(textView2_3.getText().toString()) - Integer.parseInt(textView3_3.getText().toString())) ==
                                        Integer.parseInt(editText5_3.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = false;
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
                        if(pom == true) {
                            if(editText1_2.getText().toString().trim().length() != 0) {
                                if ((Integer.parseInt(editText1_2.getText().toString()) - Integer.parseInt(textView3_2.getText().toString())) ==
                                        Integer.parseInt(editText5_2.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = true;

                            } else {
                                if ((Integer.parseInt(textView_2.getText().toString()) - Integer.parseInt(textView3_2.getText().toString())) ==
                                        Integer.parseInt(editText5_2.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = false;
                            }

                        } else {
                            if(editText1_2.getText().toString().trim().length() != 0) {
                                if ((Integer.parseInt(editText1_2.getText().toString()) - Integer.parseInt(textView3_2.getText().toString())) ==
                                        Integer.parseInt(editText5_2.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = true;
                            } else {
                                if ((Integer.parseInt(textView2_2.getText().toString()) - Integer.parseInt(textView3_2.getText().toString())) ==
                                        Integer.parseInt(editText5_2.getText().toString())) {
                                    flaga += 0;
                                } else flaga += 1;
                                pom = false;
                            }
                        }

                    editText5_1.setFocusableInTouchMode(true);
                    editText5_1.requestFocus();
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
        flaga = 0;
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
            goodAns++;
        } else if(flaga > 0){
            buttonNext.setBackgroundColor(Color.RED);
            if (gameCounter <= 10) {
                buttonNext.setText("Dalej!");
            } else {
                buttonNext.setText("Sprawdź");
            }
            wrongAns++;
        }
    }

    public void clear() {
        editText5_1.setText("");
        editText5_2.setText("");
        editText5_3.setText("");
        editText5_4.setText("");
        editText5_5.setText("");
        editText5_6.setText("");
        editText1_2.setText("");
        editText1_3.setText("");
        editText1_4.setText("");
        editText1_5.setText("");
        editText1_6.setText("");
        editText5_1.setBackgroundColor(Color.WHITE);
        editText5_2.setBackgroundColor(Color.WHITE);
        editText5_3.setBackgroundColor(Color.WHITE);
        editText5_4.setBackgroundColor(Color.WHITE);
        editText5_5.setBackgroundColor(Color.WHITE);
        editText5_6.setBackgroundColor(Color.WHITE);
        textView2_1.setTextColor(Color.parseColor("#FF3045B8"));
        textView2_2.setTextColor(Color.parseColor("#FF3045B8"));
        textView2_3.setTextColor(Color.parseColor("#FF3045B8"));
        textView2_4.setTextColor(Color.parseColor("#FF3045B8"));
        textView2_5.setTextColor(Color.parseColor("#FF3045B8"));
        textView2_6.setTextColor(Color.parseColor("#FF3045B8"));
        textView_1.setText("");
        textView_2.setText("");
        textView_3.setText("");
        textView_4.setText("");
        textView_5.setText("");
        textView_6.setText("");
    }

    public void losuj() {
        if(range == 999) {
            int los = myRandom.nextInt(9)+1;
            textView2_4.setText(String.valueOf(los));
            textView2_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_5.setText(String.valueOf(myRandom.nextInt(10)));

            textView3_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_5.setText(String.valueOf(myRandom.nextInt(10)));
            int los2 = myRandom.nextInt(los);
            if (los2 == 0) {
                textView3_4.setTextColor(Color.WHITE);
                textView3_4.setText(String.valueOf(los2));
            } else {
                textView3_4.setTextColor(Color.parseColor("#FF3045B8"));
                textView3_4.setText(String.valueOf(los2));
            }

        } else if(range == 9999) {
            int los = myRandom.nextInt(9)+1;
            textView2_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_5.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_4.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_3.setText(String.valueOf(los));

            textView3_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_5.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_4.setText(String.valueOf(myRandom.nextInt(10)));
            int los2 = myRandom.nextInt(los);
            if (los2 == 0) {
                textView3_3.setTextColor(Color.WHITE);
                textView3_3.setText(String.valueOf(los2));
            } else {
                textView3_3.setTextColor(Color.parseColor("#FF3045B8"));
                textView3_3.setText(String.valueOf(los2));
            }

        } else if(range == 99999) {
            int los = myRandom.nextInt(9)+1;
            textView2_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_5.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_4.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_3.setText(String.valueOf(myRandom.nextInt(10)));
            textView2_2.setText(String.valueOf(los));

            textView3_6.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_5.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_4.setText(String.valueOf(myRandom.nextInt(10)));
            textView3_3.setText(String.valueOf(myRandom.nextInt(10)));
            int los2 = myRandom.nextInt(los);
            if (los2 == 0) {
                textView3_2.setTextColor(Color.WHITE);
                textView3_2.setText(String.valueOf(los2));
            } else {
                textView3_2.setTextColor(Color.parseColor("#FF3045B8"));
                textView3_2.setText(String.valueOf(los2));
            }

        }

    }

    public void showMsg() {
        Toast.makeText(this, "Wpisz wynik!", Toast.LENGTH_SHORT).show();
    }

    public void openWin() {
        Intent intent = new Intent(this, ActivityWin.class);
        intent.putExtra(EXTRA_NUMBER, no);
        intent.putExtra(EXTRA_COUNTER, counter);
        intent.putExtra("wrongAns_MinusK", wrongAns);
        intent.putExtra("goodAns_MinusK", goodAns);
        startActivity(intent);
    }
}
