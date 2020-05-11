package com.example.quizdladzieci;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizdladzieci.CalculatorContract.QuestionRestTable;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="CalculatorDivideRest.db";
    private static final int DATABASE_VERSION=2;

    private SQLiteDatabase db;

    public DbHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_REST_TABLE = "CREATE TABLE " +
                CalculatorContract.QuestionRestTable.TABLE_NAME + " ( " +
                CalculatorContract.QuestionRestTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CalculatorContract.QuestionRestTable.COLUMN_QUESTION + " TEXT, " +
                CalculatorContract.QuestionRestTable.COLUMN_RESULT + " INTEGER, " +
                CalculatorContract.QuestionRestTable.COLUMN_REST + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_REST_TABLE);
        fillQuestionsRestTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CalculatorContract.QuestionRestTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsRestTable() {
        DivideRestQuestions q1 = new DivideRestQuestions("13 : 3 = ", 4, 1);
        addQuestion(q1);
        DivideRestQuestions q2 = new DivideRestQuestions("34 : 7 = ", 4, 6);
        addQuestion(q2);
        DivideRestQuestions q3 = new DivideRestQuestions("29 : 3 = ", 9, 2);
        addQuestion(q3);
        DivideRestQuestions q4 = new DivideRestQuestions("88 : 9 = ", 9, 7);
        addQuestion(q4);
        DivideRestQuestions q5 = new DivideRestQuestions("5 : 3 = ", 1, 2);
        addQuestion(q5);
        DivideRestQuestions q6 = new DivideRestQuestions("76 : 8 = ", 9, 4);
        addQuestion(q6);
        DivideRestQuestions q7 = new DivideRestQuestions("25 : 4 = ", 6, 1);
        addQuestion(q7);
        DivideRestQuestions q8 = new DivideRestQuestions("65 : 8 = ", 8, 1);
        addQuestion(q8);
        DivideRestQuestions q9 = new DivideRestQuestions("70 : 8 = ", 8, 6);
        addQuestion(q9);
        DivideRestQuestions q10 = new DivideRestQuestions("43 : 6 = ", 7, 1);
        addQuestion(q10);
        DivideRestQuestions q11 = new DivideRestQuestions("34 : 6 = ", 5, 4);
        addQuestion(q11);
        DivideRestQuestions q12 = new DivideRestQuestions("31 : 7 = ", 4, 3);
        addQuestion(q12);
        DivideRestQuestions q13 = new DivideRestQuestions("77 : 10 = ", 7, 7);
        addQuestion(q13);
        DivideRestQuestions q14 = new DivideRestQuestions("25 : 3 = ", 8, 1);
        addQuestion(q14);
        DivideRestQuestions q15 = new DivideRestQuestions("11 : 5 = ", 2, 1);
        addQuestion(q15);
        DivideRestQuestions q16 = new DivideRestQuestions("91 : 9 = ", 10, 1);
        addQuestion(q16);
    }

    private void addQuestion(DivideRestQuestions questions){
        ContentValues cv = new ContentValues();
        cv.put(CalculatorContract.QuestionRestTable.COLUMN_QUESTION, questions.getQuestion());
        cv.put(CalculatorContract.QuestionRestTable.COLUMN_RESULT, questions.getResult());
        cv.put(CalculatorContract.QuestionRestTable.COLUMN_REST, questions.getRest());

        db.insert(CalculatorContract.QuestionRestTable.TABLE_NAME, null, cv);
    }

    public List<DivideRestQuestions> getAllDivideRestQuestions() {
        List<DivideRestQuestions> divideRestQuestionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CalculatorContract.QuestionRestTable.TABLE_NAME, null);
        if(c.moveToFirst()) {
            do {
                DivideRestQuestions questions = new DivideRestQuestions();
                questions.setQuestion(c.getString(c.getColumnIndex(CalculatorContract.QuestionRestTable.COLUMN_QUESTION)));
                questions.setResult(c.getInt(c.getColumnIndex(CalculatorContract.QuestionRestTable.COLUMN_RESULT)));
                questions.setRest(c.getInt(c.getColumnIndex(CalculatorContract.QuestionRestTable.COLUMN_REST)));
                divideRestQuestionsList.add(questions);
            }while(c.moveToNext());
        }
        c.close();
        return divideRestQuestionsList;
    }
}
