package com.example.quizdladzieci;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizdladzieci.CalculatorContract.QestionTable;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class CalculatorDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="CalculatorDivide.db";
    private static final int DATABASE_VERSION=2;

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
        DivideQuestions q22 = new DivideQuestions("60 : 10 = ", 6);
        addQuestion(q22);
        DivideQuestions q23 = new DivideQuestions("1 : 1 = ", 1);
        addQuestion(q23);
        DivideQuestions q24 = new DivideQuestions("18 : 2 = ", 9);
        addQuestion(q24);
        DivideQuestions q25 = new DivideQuestions("16 : 8 = ", 2);
        addQuestion(q25);
        DivideQuestions q26 = new DivideQuestions("21 : 3 = ", 7);
        addQuestion(q26);
        DivideQuestions q27 = new DivideQuestions("54 : 9 = ", 6);
        addQuestion(q27);
        DivideQuestions q28 = new DivideQuestions("30 : 6 = ", 5);
        addQuestion(q28);
        DivideQuestions q29 = new DivideQuestions("56 : 7 = ", 8);
        addQuestion(q29);
        DivideQuestions q30 = new DivideQuestions("10 : 1 = ", 10);
        addQuestion(q30);
        DivideQuestions q31 = new DivideQuestions("24 : 4 = ", 6);
        addQuestion(q31);
        DivideQuestions q32 = new DivideQuestions("5 : 1 = ", 5);
        addQuestion(q32);
        DivideQuestions q33 = new DivideQuestions("48 : 6 = ", 8);
        addQuestion(q33);
        DivideQuestions q34 = new DivideQuestions("40 : 8 = ", 5);
        addQuestion(q34);
        DivideQuestions q35 = new DivideQuestions("14 : 7 = ", 2);
        addQuestion(q35);
        DivideQuestions q36 = new DivideQuestions("12 : 6 = ", 2);
        addQuestion(q36);
        DivideQuestions q37 = new DivideQuestions("63 : 9 = ", 7);
        addQuestion(q37);
        DivideQuestions q38 = new DivideQuestions("20 : 5 = ", 4);
        addQuestion(q38);
        DivideQuestions q39 = new DivideQuestions("28 : 7 = ", 4);
        addQuestion(q39);
        DivideQuestions q40 = new DivideQuestions("4 : 2 = ", 2);
        addQuestion(q40);
        DivideQuestions q41 = new DivideQuestions("36 : 4 = ", 9);
        addQuestion(q41);
        DivideQuestions q42 = new DivideQuestions("72 : 8 = ", 9);
        addQuestion(q42);
        DivideQuestions q43 = new DivideQuestions("45 : 9 = ", 5);
        addQuestion(q43);
        DivideQuestions q44 = new DivideQuestions("3 : 1 = ", 3);
        addQuestion(q44);
        DivideQuestions q45 = new DivideQuestions("14 : 2 = ", 7);
        addQuestion(q45);
        DivideQuestions q46 = new DivideQuestions("6 : 3 = ", 2);
        addQuestion(q46);
        DivideQuestions q47 = new DivideQuestions("35 : 5 = ", 7);
        addQuestion(q47);
        DivideQuestions q48 = new DivideQuestions("40 : 10 = ", 4);
        addQuestion(q48);
        DivideQuestions q49 = new DivideQuestions("27 : 3 = ", 9);
        addQuestion(q49);
        DivideQuestions q50 = new DivideQuestions("12 : 4 = ", 3);
        addQuestion(q50);
        DivideQuestions q51 = new DivideQuestions("20 : 2 = ", 10);
        addQuestion(q51);
        DivideQuestions q52 = new DivideQuestions("9 : 1 = ", 9);
        addQuestion(q52);
        DivideQuestions q53 = new DivideQuestions("2 : 2 = ", 1);
        addQuestion(q53);
        DivideQuestions q54 = new DivideQuestions("48 : 8 = ", 6);
        addQuestion(q54);
        DivideQuestions q55 = new DivideQuestions("70 : 10 = ", 7);
        addQuestion(q55);
        DivideQuestions q56 = new DivideQuestions("10 : 5 = ", 2);
        addQuestion(q56);
        DivideQuestions q57 = new DivideQuestions("80 : 8 = ", 10);
        addQuestion(q57);
        DivideQuestions q58 = new DivideQuestions("24 : 3 = ", 8);
        addQuestion(q58);
        DivideQuestions q59 = new DivideQuestions("4 : 4 = ", 1);
        addQuestion(q59);
        DivideQuestions q60 = new DivideQuestions("72 : 9 = ", 8);
        addQuestion(q60);
        DivideQuestions q61 = new DivideQuestions("18 : 3 = ", 6);
        addQuestion(q61);
        DivideQuestions q62 = new DivideQuestions("3 : 3 = ", 1);
        addQuestion(q62);
        DivideQuestions q63 = new DivideQuestions("63 : 7 = ", 9);
        addQuestion(q63);
        DivideQuestions q64 = new DivideQuestions("20 : 10 = ", 2);
        addQuestion(q64);
        DivideQuestions q65 = new DivideQuestions("6 : 2 = ", 3);
        addQuestion(q65);
        DivideQuestions q66 = new DivideQuestions("21 : 7 = ", 3);
        addQuestion(q66);
        DivideQuestions q67 = new DivideQuestions("2 : 1 = ", 2);
        addQuestion(q67);
        DivideQuestions q68 = new DivideQuestions("30 : 3 = ", 10);
        addQuestion(q68);
        DivideQuestions q69 = new DivideQuestions("40 : 5 = ", 8);
        addQuestion(q69);
        DivideQuestions q70 = new DivideQuestions("4 : 1 = ", 4);
        addQuestion(q70);
        DivideQuestions q71 = new DivideQuestions("36 : 9 = ", 4);
        addQuestion(q71);
        DivideQuestions q72 = new DivideQuestions("30 : 5 = ", 6);
        addQuestion(q72);
        DivideQuestions q73 = new DivideQuestions("32 : 8 = ", 4);
        addQuestion(q73);
        DivideQuestions q74 = new DivideQuestions("50 : 10 = ", 5);
        addQuestion(q74);
        DivideQuestions q75 = new DivideQuestions("8 : 2 = ", 4);
        addQuestion(q75);
        DivideQuestions q76 = new DivideQuestions("15 : 3 = ", 5);
        addQuestion(q76);
        DivideQuestions q77 = new DivideQuestions("24 : 8 = ", 3);
        addQuestion(q77);
        DivideQuestions q78 = new DivideQuestions("24 : 6 = ", 4);
        addQuestion(q78);
        DivideQuestions q79 = new DivideQuestions("35 : 7 = ", 5);
        addQuestion(q79);
        DivideQuestions q80 = new DivideQuestions("70 : 7 = ", 10);
        addQuestion(q80);
        DivideQuestions q81 = new DivideQuestions("42 : 6 = ", 7);
        addQuestion(q81);
        DivideQuestions q82 = new DivideQuestions("20 : 4 = ", 5);
        addQuestion(q82);
        DivideQuestions q83 = new DivideQuestions("12 : 2 = ", 6);
        addQuestion(q83);
        DivideQuestions q84 = new DivideQuestions("49 : 7 = ", 7);
        addQuestion(q84);
        DivideQuestions q85 = new DivideQuestions("36 : 6 = ", 6);
        addQuestion(q85);
        DivideQuestions q86 = new DivideQuestions("8 : 8 = ", 1);
        addQuestion(q86);
        DivideQuestions q87 = new DivideQuestions("30 : 10 = ", 3);
        addQuestion(q87);
        DivideQuestions q88 = new DivideQuestions("54 : 6 = ", 9);
        addQuestion(q88);
        DivideQuestions q89 = new DivideQuestions("40 : 4 = ", 10);
        addQuestion(q89);
        DivideQuestions q90 = new DivideQuestions("16 : 4 = ", 4);
        addQuestion(q90);
        DivideQuestions q91 = new DivideQuestions("6 : 1 = ", 6);
        addQuestion(q91);
        DivideQuestions q92 = new DivideQuestions("50 : 5 = ", 10);
        addQuestion(q92);
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
