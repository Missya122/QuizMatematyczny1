package com.example.quizdladzieci;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;


public class MainActivity extends MenuForAllAcitivity {
    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String EXTRA_DIFFICULTY = "extraDifficulty";
    public static final String EXTRA_SETS_ID = "extraSetsID";
    public static final String EXTRA_SETS_NAME = "extraSetsName";
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";

    public TextView textViewHighscore;
    private Spinner spinnerCategory;
    private Spinner spinnerSets;

    private Spinner spinnerDifficulty;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerSets = findViewById(R.id.spinner_sets);
        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerDifficulty = findViewById(R.id.spinner_difficulty);
        loadSets();
        loadCategories();
        loadDifficultyLevels();


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


        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                startQuiz();
            }
        });
    }
    private void startQuiz() {

        Sets selectedSets = (Sets) spinnerSets.getSelectedItem();
        int setsID = selectedSets.getId();
        String setsName = selectedSets.getName();


        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
        int categoryID = selectedCategory.getId();
        String categoryName = selectedCategory.getName();

        String difficulty = spinnerDifficulty.getSelectedItem().toString();
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_SETS_ID,setsID);
        intent.putExtra(EXTRA_SETS_NAME,setsName);
        intent.putExtra(EXTRA_CATEGORY_ID,categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME,categoryName);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }


    private void loadSets() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Sets> sets = dbHelper.getAllSets();

        ArrayAdapter<Sets> adapterSets = new ArrayAdapter<>(this, R.layout.custom_spinner
                , sets);
        adapterSets.setDropDownViewResource(R.layout.custom_spinner);
        spinnerSets.setAdapter(adapterSets);
    }
    private void loadCategories() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,
                R.layout.custom_spinner,categories);
        adapterCategories.setDropDownViewResource(R.layout.custom_spinner);
        spinnerCategory.setAdapter(adapterCategories);

    }

    private void loadDifficultyLevels() {
        String[] difficultyLevels = Question.getAllDifficultyLevels();
        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                R.layout.custom_spinner, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(R.layout.custom_spinner);
        spinnerDifficulty.setAdapter(adapterDifficulty);
    }

}
