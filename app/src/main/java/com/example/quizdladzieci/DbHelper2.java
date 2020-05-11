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
        DivideRestQuestions q17 = new DivideRestQuestions("27 : 4 = ", 6, 3);
        addQuestion(q17);
        DivideRestQuestions q18 = new DivideRestQuestions("34 : 10 = ", 3, 4);
        addQuestion(q18);
        DivideRestQuestions q19 = new DivideRestQuestions("13 : 7 = ", 1, 6);
        addQuestion(q19);
        DivideRestQuestions q20 = new DivideRestQuestions("41 : 5 = ", 8, 1);
        addQuestion(q20);
        DivideRestQuestions q21 = new DivideRestQuestions("67 : 7 = ", 9, 4);
        addQuestion(q21);
        DivideRestQuestions q22 = new DivideRestQuestions("20 : 3 = ", 6, 2);
        addQuestion(q22);
        DivideRestQuestions q23 = new DivideRestQuestions("54 : 8 = ", 6, 6);
        addQuestion(q23);
        DivideRestQuestions q24 = new DivideRestQuestions("11 : 4 = ", 2, 3);
        addQuestion(q24);
        DivideRestQuestions q25 = new DivideRestQuestions("14 : 6 = ", 2, 2);
        addQuestion(q25);
        DivideRestQuestions q26 = new DivideRestQuestions("27 : 8 = ", 3, 3);
        addQuestion(q26);
        DivideRestQuestions q27 = new DivideRestQuestions("5 : 2 = ", 2, 1);
        addQuestion(q27);
        DivideRestQuestions q28 = new DivideRestQuestions("23: 6 = ", 3, 5);
        addQuestion(q28);
        DivideRestQuestions q29 = new DivideRestQuestions("34 : 8 = ", 4, 2);
        addQuestion(q29);
        DivideRestQuestions q30 = new DivideRestQuestions("20 : 3 = ", 6, 2);
        addQuestion(q30);
        DivideRestQuestions q31 = new DivideRestQuestions("9 : 2 = ", 4, 1);
        addQuestion(q31);
        DivideRestQuestions q32 = new DivideRestQuestions("17 : 4 = ", 4, 1);
        addQuestion(q32);
        DivideRestQuestions q33 = new DivideRestQuestions("11 : 5 = ", 2, 1);
        addQuestion(q33);
        DivideRestQuestions q34 = new DivideRestQuestions("19 : 7 = ", 2, 5);
        addQuestion(q34);
        DivideRestQuestions q35 = new DivideRestQuestions("25 : 4 = ", 6, 1);
        addQuestion(q35);
        DivideRestQuestions q36 = new DivideRestQuestions("29 : 9 = ", 3, 2);
        addQuestion(q36);
        DivideRestQuestions q37 = new DivideRestQuestions("51 : 5 = ", 10, 1);
        addQuestion(q37);
        DivideRestQuestions q38 = new DivideRestQuestions("65 : 6 = ", 10, 5);
        addQuestion(q38);
        DivideRestQuestions q39 = new DivideRestQuestions("21 : 2 = ", 10, 1);
        addQuestion(q39);
        DivideRestQuestions q40 = new DivideRestQuestions("40 : 5 = ", 8, 0);
        addQuestion(q40);
        DivideRestQuestions q41 = new DivideRestQuestions("61 : 7 = ", 8, 5);
        addQuestion(q41);
        DivideRestQuestions q42 = new DivideRestQuestions("19 : 4 = ", 4, 3);
        addQuestion(q42);
        DivideRestQuestions q43 = new DivideRestQuestions("27 : 2 = ", 13, 1);
        addQuestion(q43);
        DivideRestQuestions q44 = new DivideRestQuestions("35 : 6 = ", 5, 5);
        addQuestion(q44);
        DivideRestQuestions q45 = new DivideRestQuestions("31 : 2 = ", 15, 1);
        addQuestion(q45);
        DivideRestQuestions q46 = new DivideRestQuestions("40 : 6 = ", 6, 4);
        addQuestion(q46);
        DivideRestQuestions q47 = new DivideRestQuestions("39 : 8 = ", 4, 7);
        addQuestion(q47);
        DivideRestQuestions q48 = new DivideRestQuestions("55 : 2 = ", 27, 1);
        addQuestion(q48);
        DivideRestQuestions q49 = new DivideRestQuestions("11 : 2 = ", 5, 1);
        addQuestion(q49);
        DivideRestQuestions q50 = new DivideRestQuestions("17 : 8 = ", 2, 1);
        addQuestion(q50);
        DivideRestQuestions q51 = new DivideRestQuestions("28 : 11 = ", 2, 7);
        addQuestion(q51);
        DivideRestQuestions q52 = new DivideRestQuestions("13 : 3 = ", 4, 1);
        addQuestion(q52);
        DivideRestQuestions q53 = new DivideRestQuestions("8 : 3 = ", 2, 2);
        addQuestion(q53);
        DivideRestQuestions q54 = new DivideRestQuestions("4 : 3 = ", 1, 1);
        addQuestion(q54);
        DivideRestQuestions q55 = new DivideRestQuestions("18 : 5 = ", 3, 3);
        addQuestion(q55);
        DivideRestQuestions q56 = new DivideRestQuestions("34 : 3 = ", 11, 1);
        addQuestion(q56);
        DivideRestQuestions q57 = new DivideRestQuestions("29 : 4 = ", 7, 1);
        addQuestion(q57);
        DivideRestQuestions q58 = new DivideRestQuestions("14 : 5 = ", 2, 4);
        addQuestion(q58);
        DivideRestQuestions q59 = new DivideRestQuestions("28 : 8 = ", 3, 4);
        addQuestion(q59);
        DivideRestQuestions q60 = new DivideRestQuestions("43 : 6 = ", 7, 1);
        addQuestion(q60);
        DivideRestQuestions q61 = new DivideRestQuestions("57 : 8 = ", 7, 1);
        addQuestion(q61);
        DivideRestQuestions q62 = new DivideRestQuestions("57 : 7 = ", 8, 1);
        addQuestion(q62);
        DivideRestQuestions q63 = new DivideRestQuestions("20 : 6 = ", 3, 2);
        addQuestion(q63);
        DivideRestQuestions q64 = new DivideRestQuestions("23 : 9 = ", 2, 5);
        addQuestion(q64);
        DivideRestQuestions q65 = new DivideRestQuestions("61 : 7 = ", 8, 5);
        addQuestion(q65);
        DivideRestQuestions q66 = new DivideRestQuestions("43 : 5 = ", 8, 3);
        addQuestion(q66);
        DivideRestQuestions q67 = new DivideRestQuestions("19 : 9 = ", 2, 1);
        addQuestion(q67);
        DivideRestQuestions q68 = new DivideRestQuestions("13 : 2 = ", 6, 1);
        addQuestion(q68);
        DivideRestQuestions q69 = new DivideRestQuestions("9 : 6 = ", 1, 3);
        addQuestion(q69);
        DivideRestQuestions q70 = new DivideRestQuestions("12 : 5 = ", 2, 2);
        addQuestion(q70);



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
