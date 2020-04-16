package com.example.quizdladzieci;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizdladzieci.CalculatorContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class CalculatorDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="CalculatorDivide.db";
    private static final int DATABASE_VERSION=1;

    private SQLiteDatabase db;

    public CalculatorDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QestionTable.TABLE_NAME + " ( " +
                QestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QestionTable.COLUMN_QUESTION + " TEXT, " +
                QestionTable.COLUMN_RESULT + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QestionTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable(){
        DivideQuestions q1 = new DivideQuestions("100 : 10 = ", 10);
        addQuestion(q1);
        DivideQuestions q2 = new DivideQuestions("56 : 8 = ", 7);
        addQuestion(q2);
        DivideQuestions q3 = new DivideQuestions("81 : 9 = ", 9);
        addQuestion(q3);
        DivideQuestions q4 = new DivideQuestions("16 : 2 = ", 8);
        addQuestion(q4);
        DivideQuestions q5 = new DivideQuestions("18 : 6 = ", 3);
        addQuestion(q5);
        DivideQuestions q6 = new DivideQuestions("32 : 4 = ", 8);
        addQuestion(q6);
        DivideQuestions q7 = new DivideQuestions("27 : 9 = ", 3);
        addQuestion(q7);
        DivideQuestions q8 = new DivideQuestions("8 : 1 = ", 8);
        addQuestion(q8);
        DivideQuestions q9 = new DivideQuestions("45 : 5 = ", 9);
        addQuestion(q9);
        DivideQuestions q10 = new DivideQuestions("64 : 8 = ", 8);
        addQuestion(q10);
        DivideQuestions q11 = new DivideQuestions("25 : 5 = ", 5);
        addQuestion(q11);
        DivideQuestions q12 = new DivideQuestions("8 : 4 = ", 2);
        addQuestion(q12);
        DivideQuestions q13 = new DivideQuestions("10 : 2 = ", 5);
        addQuestion(q13);
        DivideQuestions q14 = new DivideQuestions("42 : 7 = ", 6);
        addQuestion(q14);
        DivideQuestions q15 = new DivideQuestions("7 : 1 = ", 7);
        addQuestion(q15);
        DivideQuestions q16 = new DivideQuestions("72 : 8 = ", 9);
        addQuestion(q16);
        DivideQuestions q17 = new DivideQuestions("12 : 3 = ", 4);
        addQuestion(q17);
        DivideQuestions q18 = new DivideQuestions("9 : 3 = ", 3);
        addQuestion(q18);
        DivideQuestions q19 = new DivideQuestions("15 : 5 = ", 3);
        addQuestion(q19);
        DivideQuestions q20 = new DivideQuestions("28 : 4 = ", 7);
        addQuestion(q20);
        DivideQuestions q21 = new DivideQuestions("60 : 6 = ", 10);
        addQuestion(q21);
    }

    private void addQuestion(DivideQuestions questions){
        ContentValues cv = new ContentValues();
        cv.put(QestionTable.COLUMN_QUESTION, questions.getQuestion());
        cv.put(QestionTable.COLUMN_RESULT, questions.getResult());

        db.insert(QestionTable.TABLE_NAME, null, cv);
    }

    public List<DivideQuestions> getAllDivideQuestions() {
        List<DivideQuestions> divideQuestionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QestionTable.TABLE_NAME, null);
        if(c.moveToFirst()) {
            do {
                DivideQuestions questions = new DivideQuestions();
                questions.setQuestion(c.getString(c.getColumnIndex(QestionTable.COLUMN_QUESTION)));
                questions.setResult(c.getInt(c.getColumnIndex(QestionTable.COLUMN_RESULT)));
                divideQuestionsList.add(questions);
            }while(c.moveToNext());
        }
        c.close();
        return divideQuestionsList;
    }
}
