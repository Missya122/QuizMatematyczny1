package com.example.quizdladzieci;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.quizdladzieci.QuizContract.*;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizDlaDzieci2.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;


    private SQLiteDatabase db;
    public QuizDbHelper( Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if(instance == null )
        {
            instance = new QuizDbHelper((context.getApplicationContext()));
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_SETS_TABLE = "CREATE TABLE " +
                SetsTable.TABLE_NAME + "( " +
                SetsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
               SetsTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE="CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_SETS_ID + " INTEGER, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_SETS_ID + ") REFERENCES " +
                SetsTable.TABLE_NAME + "(" + SetsTable._ID + ")" + " ON DELETE CASCADE," +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + " ON DELETE CASCADE"+
                ")";


        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_SETS_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillSetsTable();
        fillCategoriesTable();
        fillQuestionsTable();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + SetsTable.TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
            db.execSQL(" DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);

            onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
    private void fillSetsTable() {
        Sets s1 = new Sets("Zestaw 1");
        addSets(s1);
        Sets s2 = new Sets("Zestaw 2");
        addSets(s2);
        Sets s3 = new Sets("Zestaw 3");
        addSets(s3);
        Sets s4 = new Sets("Zestaw 4");
        addSets(s4);


    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Dodawanie");
        addCategory(c1);
        Category c2 = new Category("Odejmowanie");
        addCategory(c2);
        Category c3 = new Category("Mnożenie");
        addCategory(c3);
        Category c4 = new Category("Dzielenie");
        addCategory(c4);

    }

    private void addCategory( Category category)
    {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null,cv);
    }
    private void addSets( Sets sets)
    {
        ContentValues cv = new ContentValues();
        cv.put(SetsTable.COLUMN_NAME, sets.getName());
        db.insert(SetsTable.TABLE_NAME, null,cv);
    }

    private void fillQuestionsTable() {


        //zapytania ręczne
        Question q1 = new Question(" 5 + 5 = ? ", "12 ", "8 ", "10 ", 3, Question.DIFFICULTY_EASY,Sets.I, Category.DODAWANIE);
        addQuestion(q1);
        Question q2 = new Question(" 5 + 20 = ? ", "25 ", "52 ", "23 ", 1, Question.DIFFICULTY_EASY, Sets.II, Category.DODAWANIE);
        addQuestion(q2);
        Question q3 = new Question(" 14 + 4 = ? ", "12 ", "18 ", "24 ", 2, Question.DIFFICULTY_EASY, Sets.III, Category.DODAWANIE);
        addQuestion(q3);
        Question q4 = new Question(" 1 + 4 = ? ", "5 ", "3 ", "6 ", 1, Question.DIFFICULTY_EASY, Sets.IV, Category.DODAWANIE);
        addQuestion(q4);
        Question q5 = new Question(" 13 + 2 = ? ", "14 ", "15 ", "16 ", 2, Question.DIFFICULTY_EASY, Sets.I, Category.DODAWANIE);
        addQuestion(q5);
        Question q6 = new Question(" 25 + 39 = ? ", "58 ", "64 ", "76 ", 2, Question.DIFFICULTY_MEDIUM, Sets.II, Category.DODAWANIE);
        addQuestion(q6);
        Question q7 = new Question(" 34 + 8 + 2 = ? ", "44 ", "39 ", "54 ", 1, Question.DIFFICULTY_MEDIUM, Sets.III, Category.DODAWANIE);
        addQuestion(q7);
        Question q8 = new Question(" 23 + 7 + 5 = ? ", "32 ", "36 ", "35 ", 3, Question.DIFFICULTY_MEDIUM, Sets.IV, Category.DODAWANIE);
        addQuestion(q8);
        Question q9 = new Question(" 18 + 41 = ? ", "56 ", "59 ", "60 ", 2, Question.DIFFICULTY_MEDIUM, Sets.I, Category.DODAWANIE);
        addQuestion(q9);
        Question q10 = new Question(" 12 + ? + 7 = 19 ", "1 ", "0 ", "2 ", 2, Question.DIFFICULTY_HARD, Sets.II, Category.DODAWANIE);
        addQuestion(q10);
        Question q11 = new Question(" 25 + 14 + 33 = ? ", "58 ", "72 ", "76 ", 2, Question.DIFFICULTY_HARD, Sets.III, Category.DODAWANIE);
        addQuestion(q11);
        Question q12 = new Question(" ? + 8 + 12 = 25 ", "4 ", "7 ", "5 ", 3, Question.DIFFICULTY_HARD, Sets.IV, Category.DODAWANIE);
        addQuestion(q12);
        Question q13 = new Question(" 12 + ? + ? + 6 = 22 ", "2 ", "3 ", "4 ", 1, Question.DIFFICULTY_HARD, Sets.I, Category.DODAWANIE);
        addQuestion(q13);
        Question q14 = new Question(" 66 + 48 + 32 = ? ", "146 ", "153 ", "136 ", 1, Question.DIFFICULTY_HARD, Sets.II, Category.DODAWANIE);
        addQuestion(q14);
        Question q15 = new Question(" ? + ? + 8 = 9 ", "0 ", "0.5 ", "1 ", 2, Question.DIFFICULTY_HARD, Sets.III, Category.DODAWANIE);
        addQuestion(q15);
        Question q16 = new Question(" 12 + 21 + 156 = ?  ", "199 ", "179 ", "189 ", 3, Question.DIFFICULTY_HARD, Sets.IV, Category.DODAWANIE);
        addQuestion(q16);
        Question q17 = new Question(" 10 - 8 = ?  ", "3 ", "1 ", "2 ", 3, Question.DIFFICULTY_EASY, Sets.I, Category.ODEJMOWANIE);
        addQuestion(q17);
        Question q18 = new Question(" 22 - 7 - 2 = ?  ", "13 ", "14 ", "15 ", 1, Question.DIFFICULTY_MEDIUM, Sets.II, Category.ODEJMOWANIE);
        addQuestion(q18);
        Question q19 = new Question(" 55- 22 - ?  =  30  ", "3 ", "1 ", "2 ", 1, Question.DIFFICULTY_HARD, Sets.III, Category.ODEJMOWANIE);
        addQuestion(q19);
        Question q20 = new Question(" 2 * 7 = ?  ", "10 ", "14 ", "15 ", 2, Question.DIFFICULTY_EASY, Sets.IV, Category.MNOŻENIE);
        addQuestion(q20);
        Question q21 = new Question(" 2 * 6 * 2 = ?  ", "24 ", "20 ", "25 ", 1, Question.DIFFICULTY_MEDIUM, Sets.I, Category.MNOŻENIE);
        addQuestion(q21);
        Question q22 = new Question(" 7 * 4 * ? = 84  ", "2 ", "4 ", "3 ", 3, Question.DIFFICULTY_HARD, Sets.II, Category.MNOŻENIE);
        addQuestion(q22);
        Question q23 = new Question(" 4 / 2  = ?  ", "2 ", "4 ", "1 ", 1, Question.DIFFICULTY_EASY, Sets.III, Category.DZIELENIE);
        addQuestion(q23);
        Question q24 = new Question(" 15 / 3 / 5  = ?  ", "3 ", "5 ", "1 ", 3, Question.DIFFICULTY_MEDIUM, Sets.IV, Category.DZIELENIE);
        addQuestion(q24);
        Question q25 = new Question(" 32 / ? = 4  ", "8 ", "12 ", "4 ", 1, Question.DIFFICULTY_HARD, Sets.I, Category.DZIELENIE);
        addQuestion(q25);
        Question q26 = new Question(" 10 + 8 = ?  ", "18 ", "16 ", "20 ", 1, Question.DIFFICULTY_EASY, Sets.II, Category.DODAWANIE);
        addQuestion(q26);
        Question q27 = new Question(" 23 + 4 = ?  ", "25 ", "27 ", "30 ", 2, Question.DIFFICULTY_EASY, Sets.III, Category.DODAWANIE);
        addQuestion(q27);
        Question q28 = new Question(" 28 + 4 = ?  ", "29 ", "35 ", "32 ", 3, Question.DIFFICULTY_EASY, Sets.IV, Category.DODAWANIE);
        addQuestion(q28);
        Question q29 = new Question(" 14 + 3 = ?  ", "15  ", "17  ", "20  ", 2, Question.DIFFICULTY_EASY, Sets.I, Category.DODAWANIE);
        addQuestion(q29);
        Question q30 = new Question(" 7 + 5 = ?  ", "10  ", "15  ", "12  ", 3, Question.DIFFICULTY_EASY, Sets.II, Category.DODAWANIE);
        addQuestion(q30);
        Question q31 = new Question(" 12 + 10 = ?  ", "20 ", "30  ", "22  ", 3, Question.DIFFICULTY_EASY, Sets.III, Category.DODAWANIE);
        addQuestion(q31);
        Question q32 = new Question(" 17 + 4 = ?  ", "19 ", "21 ", "23 ", 2, Question.DIFFICULTY_EASY, Sets.IV, Category.DODAWANIE);
        addQuestion(q32);
        Question q33 = new Question(" 25 + 11 = ?  ", "26 ", "30 ", "36 ", 3, Question.DIFFICULTY_MEDIUM, Sets.I, Category.DODAWANIE);
        addQuestion(q33);
        Question q34 = new Question(" 14 + 17 = ?  ", "21 ", "31 ", "35 ", 2, Question.DIFFICULTY_MEDIUM, Sets.II, Category.DODAWANIE);
        addQuestion(q34);
        Question q35 = new Question(" 37 - 14 = ?  ", "23 ", "25 ", "26 ", 1, Question.DIFFICULTY_HARD, Sets.III, Category.ODEJMOWANIE);
        addQuestion(q35);
        Question q36 = new Question(" 14 / 2 = ?  ", "7 ", "4 ", "8 ", 1, Question.DIFFICULTY_MEDIUM, Sets.IV, Category.DZIELENIE);
        addQuestion(q36);
        Question q37 = new Question(" 10 * 4 = ?  ", "40 ", "30 ", "44 ", 1, Question.DIFFICULTY_EASY, Sets.I, Category.MNOŻENIE);
        addQuestion(q37);
        Question q38 = new Question(" 3 * 3 = ?  ", "7 ", "6 ", "9 ", 3, Question.DIFFICULTY_EASY, Sets.II, Category.MNOŻENIE);
        addQuestion(q38);
        Question q39 = new Question(" 3 * 5 = ?  ", "10 ", "20 ", "15 ", 3, Question.DIFFICULTY_EASY, Sets.III, Category.MNOŻENIE);
        addQuestion(q39);
        Question q40 = new Question(" 9 * 2 = ?  ", "18 ", "9 ", "27 ", 1, Question.DIFFICULTY_MEDIUM, Sets.IV, Category.MNOŻENIE);
        addQuestion(q40);
        Question q41 = new Question(" 4 * 4 = ?  ", "18  ", "16 ", "20 ", 2, Question.DIFFICULTY_MEDIUM, Sets.I, Category.MNOŻENIE);
        addQuestion(q41);
        Question q42 = new Question(" 2 * 8 = ?  ", "18  ", "14 ", "16 ", 1, Question.DIFFICULTY_EASY, Sets.II, Category.MNOŻENIE);
        addQuestion(q42);
        Question q43 = new Question(" 3 * 9 = ?  ", "27  ", " 9 ", "29 ", 1, Question.DIFFICULTY_MEDIUM, Sets.III, Category.MNOŻENIE);
        addQuestion(q43);
        Question q44 = new Question(" 3 * 11 = ?  ", "33  ", "9 ", "29 ", 1, Question.DIFFICULTY_MEDIUM, Sets.IV, Category.MNOŻENIE);
        addQuestion(q44);
        Question q45 = new Question(" 3 * 12 = ?  ", "36  ", "9 ", "29 ", 1, Question.DIFFICULTY_MEDIUM, Sets.I, Category.MNOŻENIE);
        addQuestion(q45);
        Question q46 = new Question(" 3 * 22 = ?  ", "66  ", "9 ", "29 ", 1, Question.DIFFICULTY_MEDIUM, Sets.II, Category.MNOŻENIE);
        addQuestion(q46);
        Question q47 = new Question(" 8 / 2 = ?  ", "3  ", "4   ", "5  ", 2, Question.DIFFICULTY_EASY, Sets.III, Category.DZIELENIE);
        addQuestion(q47);
        Question q48 = new Question(" 6 / 1 = ?  ", "3  ", "1 ", "6 ", 3, Question.DIFFICULTY_MEDIUM, Sets.IV, Category.DZIELENIE);
        addQuestion(q48);
        Question q49 = new Question(" 10 / 5 = ?  ", "5  ", "3 ", "2 ", 3, Question.DIFFICULTY_MEDIUM, Sets.I, Category.DZIELENIE);
        addQuestion(q49);
        Question q50 = new Question(" 12 / 6 = ?  ", "3  ", "4 ", "2 ", 3, Question.DIFFICULTY_HARD, Sets.II, Category.DZIELENIE);
        addQuestion(q50);
        Question q51 = new Question(" 14 / 7 = ?  ", "3  ", "2 ", "4 ", 2, Question.DIFFICULTY_HARD, Sets.III, Category.DZIELENIE);
        addQuestion(q51);
        Question q52 = new Question(" 6 / 3 = ?  ", "1  ", "2 ", "3 ", 3, Question.DIFFICULTY_EASY, Sets.IV, Category.DZIELENIE);
        addQuestion(q52);
        Question q53 = new Question(" 4 / 2 = ?  ", "1  ", "2 ", "3 ", 2, Question.DIFFICULTY_EASY, Sets.I, Category.DZIELENIE);
        addQuestion(q53);
        Question q54 = new Question(" 3 / 1 = ?  ", "3  ", "2 ", "1 ", 1, Question.DIFFICULTY_EASY, Sets.II, Category.DZIELENIE);
        addQuestion(q54);
        Question q55 = new Question(" 11 - 5 = ?  ", "3  ", "5 ", "6 ", 3, Question.DIFFICULTY_MEDIUM, Sets.III, Category.ODEJMOWANIE);
        addQuestion(q55);
        Question q56 = new Question(" 19 - 13 = ?  ", "11  ", "7 ", "6 ", 3, Question.DIFFICULTY_HARD, Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q56);
        Question q57 = new Question(" 7 - 5 = ?  ", "1  ", "2 ", "3 ", 2, Question.DIFFICULTY_EASY, Sets.I, Category.ODEJMOWANIE);
        addQuestion(q57);
        Question q58 = new Question(" 4 - 1 = ?  ", "3  ", "1 ", "2 ", 1, Question.DIFFICULTY_EASY, Sets.II, Category.ODEJMOWANIE);
        addQuestion(q58);
        Question q59 = new Question(" 6 - 6 = ?  ", "1  ", "3 ", "0 ", 3, Question.DIFFICULTY_MEDIUM, Sets.III, Category.ODEJMOWANIE);
        addQuestion(q59);
        Question q60 = new Question(" 18 - 13 = ?  ", "3  ", "5 ", "6 ", 2, Question.DIFFICULTY_HARD, Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q60);
        Question q61 = new Question(" 21 - 9 = ?  ", "10  ", "14 ", "12 ", 3, Question.DIFFICULTY_HARD, Sets.I, Category.ODEJMOWANIE);
        addQuestion(q61);
        Question q62 = new Question(" 7 * 6 = ?  ", "42  ", "47 ", "49 ", 1, Question.DIFFICULTY_HARD, Sets.II, Category.MNOŻENIE);
        addQuestion(q62);
        Question q63 = new Question(" 13 * 2 = ?  ", "26  ", "39 ", "15 ", 1, Question.DIFFICULTY_MEDIUM, Sets.III, Category.MNOŻENIE);
        addQuestion(q63);
        Question q64 = new Question(" 13 * 4 = ?  ", "52  ", "39 ", "15 ", 1, Question.DIFFICULTY_MEDIUM, Sets.IV, Category.MNOŻENIE);
        addQuestion(q64);
        Question q65 = new Question(" 14 * 5 = ?  ", "70  ", "39 ", "15 ", 1, Question.DIFFICULTY_MEDIUM, Sets.I, Category.MNOŻENIE);
        addQuestion(q65);
        Question q66 = new Question(" 12 * 4 = ?  ", "26  ", "48 ", "15 ", 2, Question.DIFFICULTY_MEDIUM, Sets.II, Category.MNOŻENIE);
        addQuestion(q66);
        Question q67 = new Question(" 19 * 2 = ?  ", "28  ", "36", "38 ", 3, Question.DIFFICULTY_MEDIUM, Sets.III, Category.MNOŻENIE);
        addQuestion(q67);
        Question q68 = new Question(" 18 * 3 = ?  ", "34  ", "48 ", "44 ", 3, Question.DIFFICULTY_MEDIUM, Sets.IV, Category.MNOŻENIE);
        addQuestion(q68);
        Question q69 = new Question(" 24 * 4 = ?  ", "96  ", "100 ", "88 ", 1, Question.DIFFICULTY_HARD, Sets.I, Category.MNOŻENIE);
        addQuestion(q69);
        Question q70 = new Question(" 12 * 2 = ?  ", "20  ", "24 ", "28 ", 2, Question.DIFFICULTY_EASY, Sets.II, Category.MNOŻENIE);
        addQuestion(q70);
        Question q71 = new Question(" 13 * 3 = ?  ", "31  ", "28 ", "39 ", 3, Question.DIFFICULTY_MEDIUM, Sets.III, Category.MNOŻENIE);
        addQuestion(q71);
        Question q72 = new Question(" 17 * 4 = ?  ", "58  ", "68 ", "72 ", 2, Question.DIFFICULTY_HARD, Sets.IV, Category.MNOŻENIE);
        addQuestion(q72);
        Question q73 = new Question(" 31 * 2 = ?  ", "31  ", "62 ", "53 ", 2, Question.DIFFICULTY_EASY, Sets.I, Category.MNOŻENIE);
        addQuestion(q73);
        Question q74 = new Question(" 27 - 14 = ?  ", "41  ", "13 ", "19 ", 2, Question.DIFFICULTY_MEDIUM, Sets.II, Category.ODEJMOWANIE);
        addQuestion(q74);
        Question q75 = new Question(" 36 - 18 = ?  ", "12  ", "20 ", "18 ", 3, Question.DIFFICULTY_EASY, Sets.III, Category.ODEJMOWANIE);
        addQuestion(q75);
        Question q76 = new Question(" 17 - 14 = ?  ", "5  ", "8 ", "3 ", 3, Question.DIFFICULTY_EASY, Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q76);
        Question q77 = new Question(" 11 - 9 = ?  ", "1  ", "4 ", "2 ", 3, Question.DIFFICULTY_EASY, Sets.I, Category.ODEJMOWANIE);
        addQuestion(q77);
        Question q78 = new Question(" 10 : 5 = ?  ", "1  ", "2 ", "3 ", 2, Question.DIFFICULTY_EASY, Sets.II, Category.DZIELENIE);
        addQuestion(q78);
        Question q79 = new Question(" 30 : 5 = ?  ", "6  ", "7 ", "8 ", 1, Question.DIFFICULTY_MEDIUM, Sets.III, Category.DZIELENIE);
        addQuestion(q79);
        Question q80 = new Question(" 57 : 8 = ?  ", "9  ", "7 ", "8 ", 3, Question.DIFFICULTY_HARD, Sets.IV, Category.DZIELENIE);
        addQuestion(q80);
        Question q81 = new Question(" 36 : 3 = ?  ", "12  ", "10 ", "8 ", 1, Question.DIFFICULTY_HARD, Sets.I, Category.DZIELENIE);
        addQuestion(q81);
        Question q82 = new Question(" 24 : 6 = ?  ", "3  ", "4 ", "5 ", 2, Question.DIFFICULTY_HARD, Sets.II, Category.DZIELENIE);
        addQuestion(q82);
        Question q83 = new Question(" 38 - 24 = ?  ", "12  ", "14 ", "16 ", 2, Question.DIFFICULTY_HARD, Sets.III, Category.ODEJMOWANIE);
        addQuestion(q83);
        Question q84 = new Question(" 56 - 18 = ?  ", "48  ", "38 ", "50 ", 2, Question.DIFFICULTY_HARD, Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q84);
        Question q85 = new Question(" 21 - 8 = ?  ", "12  ", "14 ", "13 ", 3, Question.DIFFICULTY_HARD, Sets.I, Category.ODEJMOWANIE);
        addQuestion(q85);
        Question q86 = new Question(" 37 - 25 = ?  ", "10  ", "12 ", "16 ", 2, Question.DIFFICULTY_HARD, Sets.II, Category.ODEJMOWANIE);
        addQuestion(q86);
        Question q87 = new Question(" 44 - 25 = ?  ", "25  ", "29 ", "19 ", 3, Question.DIFFICULTY_HARD, Sets.III, Category.ODEJMOWANIE);
        addQuestion(q87);
        Question q88 = new Question(" 57 - 25 = ?  ", "20  ", "25 ", "27 ", 3, Question.DIFFICULTY_HARD, Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q88);
        Question q89 = new Question(" 31 - 13 = ?  ", "18  ", "25 ", "13 ", 1, Question.DIFFICULTY_HARD, Sets.I, Category.ODEJMOWANIE);
        addQuestion(q89);
        Question q90 = new Question(" 51 - 18 = ?  ", "31  ", "33 ", "37 ", 2, Question.DIFFICULTY_HARD, Sets.II, Category.ODEJMOWANIE);
        addQuestion(q90);
        Question q91 = new Question(" 37 - 17 = ?  ", "20  ", "19 ", "21 ", 1, Question.DIFFICULTY_MEDIUM, Sets.III, Category.ODEJMOWANIE);
        addQuestion(q91);
        Question q92 = new Question(" 11 - 7 = ?  ", "3  ", "4 ", "5 ", 2, Question.DIFFICULTY_EASY, Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q92);
        Question q93 = new Question(" 20 : 10 = ?  ", "2  ", "3 ", "4 ", 1, Question.DIFFICULTY_EASY, Sets.I, Category.DZIELENIE);
        addQuestion(q93);
        Question q94 = new Question(" 30 : 2 = ?  ", "10  ", "13 ", "15 ", 3, Question.DIFFICULTY_EASY, Sets.II, Category.DZIELENIE);
        addQuestion(q94);
        Question q95 = new Question(" 38 : 2 = ?  ", "14  ", "17 ", "19 ", 3, Question.DIFFICULTY_MEDIUM, Sets.III, Category.DZIELENIE);
        addQuestion(q95);
        Question q96 = new Question(" 60 : 3 = ?  ", "20  ", "30 ", "15 ", 1, Question.DIFFICULTY_MEDIUM, Sets.IV, Category.DZIELENIE);
        addQuestion(q96);
        Question q97 = new Question(" 54 : 3 = ?  ", "18  ", "20 ", "16 ", 1, Question.DIFFICULTY_MEDIUM, Sets.I, Category.DZIELENIE);
        addQuestion(q97);
        Question q98 = new Question(" 81 : 9 = ?  ", "10  ", "8 ", "9 ", 3, Question.DIFFICULTY_HARD, Sets.II, Category.DZIELENIE);
        addQuestion(q98);
        Question q99 = new Question(" 52 : 4 = ?  ", "12  ", "11 ", "13 ", 3, Question.DIFFICULTY_HARD, Sets.III, Category.DZIELENIE);
        addQuestion(q99);
        Question q100 = new Question(" 54 : 9 = ?  ", "6  ", "8 ", "7 ", 1, Question.DIFFICULTY_HARD, Sets.IV, Category.DZIELENIE);
        addQuestion(q100);


        /*
        //zapytania losowe
        //zestawI_dodawanie_easy q10
        Question q1 = new Question(" 2 + 8 = ? ", "10 ", "8 ", "7 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.DODAWANIE);
        addQuestion(q1);
        Question q2 = new Question(" 10 + 5 = ? ", "13 ", "14 ", "15 ", 3, Question.DIFFICULTY_EASY,Sets.I, Category.DODAWANIE);
        addQuestion(q2);
        Question q3 = new Question(" 3 + 5 = ? ", "8 ", "6 ", "7 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.DODAWANIE);
        addQuestion(q3);
        Question q4 = new Question(" 2 + 8 = ? ", "8 ", "10 ", "11 ", 2, Question.DIFFICULTY_EASY,Sets.I, Category.DODAWANIE);
        addQuestion(q4);
        Question q5 = new Question(" 6 + 3 = ? ", "9 ", "8 ", "7 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.DODAWANIE);
        addQuestion(q5);
        Question q6 = new Question(" 2 + 5 = ? ", "7 ", "8 ", "6 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.DODAWANIE);
        addQuestion(q6);
        Question q7 = new Question(" 3 + 3 = ? ", "5 ", "6 ", "7 ", 2, Question.DIFFICULTY_EASY,Sets.I, Category.DODAWANIE);
        addQuestion(q7);
        Question q8 = new Question(" 9 + 6 = ? ", "14 ", "16 ", "15 ", 3, Question.DIFFICULTY_EASY,Sets.I, Category.DODAWANIE);
        addQuestion(q8);
        Question q9 = new Question(" 2 + 9 = ? ", "11 ", "12 ", "10 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.DODAWANIE);
        addQuestion(q9);
        Question q10 = new Question(" 8 + 10 = ? ", "16 ", "17 ", "18 ", 3, Question.DIFFICULTY_EASY,Sets.I, Category.DODAWANIE);
        addQuestion(q10);

        //zestawI_dodawanie_medium q20
        Question q11 = new Question(" 40 + 29 = ? ", "69 ", "80 ", "61 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.DODAWANIE);
        addQuestion(q11);
        Question q12 = new Question(" 32 + 15 = ? ", "51 ", "47 ", "39 ", 2, Question.DIFFICULTY_MEDIUM,Sets.I, Category.DODAWANIE);
        addQuestion(q12);
        Question q13 = new Question(" 31 + 48 = ? ", "87 ", "68 ", "79 ", 3, Question.DIFFICULTY_MEDIUM,Sets.I, Category.DODAWANIE);
        addQuestion(q13);
        Question q14 = new Question(" 35 + 27 = ? ", "62 ", "66 ", "58 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.DODAWANIE);
        addQuestion(q14);
        Question q15 = new Question(" 25 + 18 = ? ", "51 ", "43 ", "37 ", 2, Question.DIFFICULTY_MEDIUM,Sets.I, Category.DODAWANIE);
        addQuestion(q15);
        Question q16 = new Question(" 23 + 22 = ? ", "53 ", "36 ", "45 ", 3, Question.DIFFICULTY_MEDIUM,Sets.I, Category.DODAWANIE);
        addQuestion(q16);
        Question q17 = new Question(" 38 + 20 = ? ", "58 ", "63 ", "48 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.DODAWANIE);
        addQuestion(q17);
        Question q18 = new Question(" 17 + 43 = ? ", "69 ", "60 ", "52 ", 2, Question.DIFFICULTY_MEDIUM,Sets.I, Category.DODAWANIE);
        addQuestion(q18);
        Question q19 = new Question(" 26 + 26 = ? ", "46 ", "63 ", "52 ", 3, Question.DIFFICULTY_MEDIUM,Sets.I, Category.DODAWANIE);
        addQuestion(q19);
        Question q20 = new Question(" 43 + 29 = ? ", "72 ", "79 ", "66 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.DODAWANIE);
        addQuestion(q20);

        //zestawI_dodawanie_hard q30
        Question q21 = new Question(" 441 + 455 = ? ", "896 ", "912 ", "877 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.DODAWANIE);
        addQuestion(q21);
        Question q22 = new Question(" 394 + 330 = ? ", "746 ", "724 ", "700 ", 2, Question.DIFFICULTY_HARD,Sets.I, Category.DODAWANIE);
        addQuestion(q22);
        Question q23 = new Question(" 147 + 381 = ? ", "499 ", "544 ", "528 ", 3, Question.DIFFICULTY_HARD,Sets.I, Category.DODAWANIE);
        addQuestion(q23);
        Question q24 = new Question(" 134 + 149 = ? ", "283 ", "305 ", "263 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.DODAWANIE);
        addQuestion(q24);
        Question q25 = new Question(" 155 + 119 = ? ", "301 ", "274 ", "250 ", 2, Question.DIFFICULTY_HARD,Sets.I, Category.DODAWANIE);
        addQuestion(q25);
        Question q26 = new Question(" 418 + 217 = ? ", "620 ", "664 ", "635 ", 3, Question.DIFFICULTY_HARD,Sets.I, Category.DODAWANIE);
        addQuestion(q26);
        Question q27 = new Question(" 449 + 202 = ? ", "651 ", "677 ", "623 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.DODAWANIE);
        addQuestion(q27);
        Question q28 = new Question(" 238 + 423 = ? ", "683 ", "661 ", "637 ", 2, Question.DIFFICULTY_HARD,Sets.I, Category.DODAWANIE);
        addQuestion(q28);
        Question q29 = new Question(" 102 + 437 = ? ", "521 ", "565 ", "539 ", 3, Question.DIFFICULTY_HARD,Sets.I, Category.DODAWANIE);
        addQuestion(q29);
        Question q30 = new Question(" 190 + 403 = ? ", "593 ", "608 ", "573 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.DODAWANIE);
        addQuestion(q30);

        //zestawII_dodawanie_easy q40
        Question q31 = new Question(" 10 + 4 = ? ", "14 ", "12 ", "12 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.DODAWANIE);
        addQuestion(q31);
        Question q32 = new Question(" 7 + 7 = ? ", "11 ", "14 ", "12 ", 2, Question.DIFFICULTY_EASY,Sets.II, Category.DODAWANIE);
        addQuestion(q32);
        Question q33 = new Question(" 6 + 3 = ? ", "10 ", "8 ", "9 ", 3, Question.DIFFICULTY_EASY,Sets.II, Category.DODAWANIE);
        addQuestion(q33);
        Question q34 = new Question(" 7 + 4 = ? ", "11 ", "9 ", "12 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.DODAWANIE);
        addQuestion(q34);
        Question q35 = new Question(" 3 + 2 = ? ", "4 ", "5 ", "6 ", 2, Question.DIFFICULTY_EASY,Sets.II, Category.DODAWANIE);
        addQuestion(q35);
        Question q36 = new Question(" 9 + 6 = ? ", "17 ", "14 ", "15 ", 3, Question.DIFFICULTY_EASY,Sets.II, Category.DODAWANIE);
        addQuestion(q36);
        Question q37 = new Question(" 8 + 3 = ? ", "11 ", "9 ", "12 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.DODAWANIE);
        addQuestion(q37);
        Question q38 = new Question(" 10 + 9 = ? ", "21 ", "19 ", "17 ", 2, Question.DIFFICULTY_EASY,Sets.II, Category.DODAWANIE);
        addQuestion(q38);
        Question q39 = new Question(" 7 + 3 = ? ", "9 ", "8 ", "10 ", 3, Question.DIFFICULTY_EASY,Sets.II, Category.DODAWANIE);
        addQuestion(q39);
        Question q40 = new Question(" 3 + 9 = ? ", "12 ", "10 ", "10 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.DODAWANIE);
        addQuestion(q40);

        //zestawII_dodawanie_medium q50
        Question q41 = new Question(" 49 + 33 = ? ", "82 ", "87 ", "75 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.DODAWANIE);
        addQuestion(q41);
        Question q42 = new Question(" 16 + 35 = ? ", "55 ", "51 ", "41 ", 2, Question.DIFFICULTY_MEDIUM,Sets.II, Category.DODAWANIE);
        addQuestion(q42);
        Question q43 = new Question(" 29 + 34 = ? ", "55 ", "73 ", "63 ", 3, Question.DIFFICULTY_MEDIUM,Sets.II, Category.DODAWANIE);
        addQuestion(q43);
        Question q44 = new Question(" 18 + 17 = ? ", "35 ", "44 ", "31 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.DODAWANIE);
        addQuestion(q44);
        Question q45 = new Question(" 17 + 45 = ? ", "68 ", "62 ", "56 ", 2, Question.DIFFICULTY_MEDIUM,Sets.II, Category.DODAWANIE);
        addQuestion(q45);
        Question q46 = new Question(" 37 + 23 = ? ", "49 ", "68 ", "60 ", 3, Question.DIFFICULTY_MEDIUM,Sets.II, Category.DODAWANIE);
        addQuestion(q46);
        Question q47 = new Question(" 50 + 24 = ? ", "74 ", "83 ", "68 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.DODAWANIE);
        addQuestion(q47);
        Question q48 = new Question(" 18 + 19 = ? ", "48 ", "37 ", "31 ", 2, Question.DIFFICULTY_MEDIUM,Sets.II, Category.DODAWANIE);
        addQuestion(q48);
        Question q49 = new Question(" 22 + 23 = ? ", "38 ", "49 ", "45 ", 3, Question.DIFFICULTY_MEDIUM,Sets.II, Category.DODAWANIE);
        addQuestion(q49);
        Question q50 = new Question(" 39 + 38 = ? ", "77 ", "82 ", "72 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.DODAWANIE);
        addQuestion(q50);

        //zestawII_dodawanie_hard q60
        Question q51 = new Question(" 341 + 473 = ? ", "814 ", "843 ", "799 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.DODAWANIE);
        addQuestion(q51);
        Question q52 = new Question(" 115 + 228 = ? ", "358 ", "343 ", "323 ", 2, Question.DIFFICULTY_HARD,Sets.II, Category.DODAWANIE);
        addQuestion(q52);
        Question q53 = new Question(" 368 + 495 = ? ", "836 ", "887 ", "863 ", 3, Question.DIFFICULTY_HARD,Sets.II, Category.DODAWANIE);
        addQuestion(q53);
        Question q54 = new Question(" 211 + 220 = ? ", "431 ", "456 ", "402 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.DODAWANIE);
        addQuestion(q54);
        Question q55 = new Question(" 459 + 223 = ? ", "698 ", "682 ", "662 ", 2, Question.DIFFICULTY_HARD,Sets.II, Category.DODAWANIE);
        addQuestion(q55);
        Question q56 = new Question(" 360 + 318 = ? ", "653 ", "703 ", "678 ", 3, Question.DIFFICULTY_HARD,Sets.II, Category.DODAWANIE);
        addQuestion(q56);
        Question q57 = new Question(" 375 + 498 = ? ", "873 ", "893 ", "845 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.DODAWANIE);
        addQuestion(q57);
        Question q58 = new Question(" 359 + 320 = ? ", "704 ", "679 ", "655 ", 2, Question.DIFFICULTY_HARD,Sets.II, Category.DODAWANIE);
        addQuestion(q58);
        Question q59 = new Question(" 267 + 273 = ? ", "519 ", "565 ", "540 ", 3, Question.DIFFICULTY_HARD,Sets.II, Category.DODAWANIE);
        addQuestion(q59);
        Question q60 = new Question(" 266 + 362 = ? ", "628 ", "651 ", "612 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.DODAWANIE);
        addQuestion(q60);

        //zestawIII_dodawanie_easy q70
        Question q61 = new Question(" 6 + 3 = ? ", "9 ", "13 ", "10 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.DODAWANIE);
        addQuestion(q61);
        Question q62 = new Question(" 3 + 4 = ? ", "6 ", "7 ", "8 ", 2, Question.DIFFICULTY_EASY,Sets.III, Category.DODAWANIE);
        addQuestion(q62);
        Question q63 = new Question(" 2 + 2 = ? ", "5 ", "6 ", "4 ", 3, Question.DIFFICULTY_EASY,Sets.III, Category.DODAWANIE);
        addQuestion(q63);
        Question q64 = new Question(" 7 + 8 = ? ", "15 ", "16 ", "14 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.DODAWANIE);
        addQuestion(q64);
        Question q65 = new Question(" 3 + 8 = ? ", "13 ", "11 ", "15 ", 2, Question.DIFFICULTY_EASY,Sets.III, Category.DODAWANIE);
        addQuestion(q65);
        Question q66 = new Question(" 9 + 6 = ? ", "14 ", "18 ", "15 ", 3, Question.DIFFICULTY_EASY,Sets.III, Category.DODAWANIE);
        addQuestion(q66);
        Question q67 = new Question(" 5 + 7 = ? ", "12 ", "14 ", "16 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.DODAWANIE);
        addQuestion(q67);
        Question q68 = new Question(" 7 + 4 = ? ", "12 ", "11 ", "13 ", 2, Question.DIFFICULTY_EASY,Sets.III, Category.DODAWANIE);
        addQuestion(q68);
        Question q69 = new Question(" 8 + 5 = ? ", "14 ", "17 ", "13 ", 3, Question.DIFFICULTY_EASY,Sets.III, Category.DODAWANIE);
        addQuestion(q69);
        Question q70 = new Question(" 8 + 4 = ? ", "12 ", "14 ", "13 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.DODAWANIE);
        addQuestion(q70);

        //zestawIII_dodawanie_medium q80
        Question q71 = new Question(" 17 + 21 = ? ", "38 ", "49 ", "29 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.DODAWANIE);
        addQuestion(q71);
        Question q72 = new Question(" 43 + 21 = ? ", "75 ", "64 ", "59 ", 2, Question.DIFFICULTY_MEDIUM,Sets.III, Category.DODAWANIE);
        addQuestion(q72);
        Question q73 = new Question(" 31 + 29 = ? ", "53 ", "64 ", "60 ", 3, Question.DIFFICULTY_MEDIUM,Sets.III, Category.DODAWANIE);
        addQuestion(q73);
        Question q74 = new Question(" 18 + 42 = ? ", "60 ", "70 ", "55 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.DODAWANIE);
        addQuestion(q74);
        Question q75 = new Question(" 30 + 44 = ? ", "82 ", "74 ", "66 ", 2, Question.DIFFICULTY_MEDIUM,Sets.III, Category.DODAWANIE);
        addQuestion(q75);
        Question q76 = new Question(" 30 + 26 = ? ", "51 ", "63 ", "56 ", 3, Question.DIFFICULTY_MEDIUM,Sets.III, Category.DODAWANIE);
        addQuestion(q76);
        Question q77 = new Question(" 37 + 15 = ? ", "52 ", "63 ", "46 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.DODAWANIE);
        addQuestion(q77);
        Question q78 = new Question(" 22 + 46 = ? ", "77 ", "68 ", "58 ", 2, Question.DIFFICULTY_MEDIUM,Sets.III, Category.DODAWANIE);
        addQuestion(q78);
        Question q79 = new Question(" 31 + 27 = ? ", "47 ", "64 ", "58 ", 3, Question.DIFFICULTY_MEDIUM,Sets.III, Category.DODAWANIE);
        addQuestion(q79);
        Question q80 = new Question(" 34 + 30 = ? ", "64 ", "74 ", "58 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.DODAWANIE);
        addQuestion(q80);

        //zestawIII_dodawanie_hard q90
        Question q81 = new Question(" 381 + 493 = ? ", "874 ", "902 ", "855 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.DODAWANIE);
        addQuestion(q81);
        Question q82 = new Question(" 401 + 435 = ? ", "856 ", "836 ", "814 ", 2, Question.DIFFICULTY_HARD,Sets.III, Category.DODAWANIE);
        addQuestion(q82);
        Question q83 = new Question(" 407 + 132 = ? ", "518 ", "564 ", "539 ", 3, Question.DIFFICULTY_HARD,Sets.III, Category.DODAWANIE);
        addQuestion(q83);
        Question q84 = new Question(" 254 + 136 = ? ", "390 ", "405 ", "374 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.DODAWANIE);
        addQuestion(q84);
        Question q85 = new Question(" 368 + 468 = ? ", "863 ", "836 ", "809 ", 2, Question.DIFFICULTY_HARD,Sets.III, Category.DODAWANIE);
        addQuestion(q85);
        Question q86 = new Question(" 154 + 310 = ? ", "444 ", "483 ", "464 ", 3, Question.DIFFICULTY_HARD,Sets.III, Category.DODAWANIE);
        addQuestion(q86);
        Question q87 = new Question(" 484 + 141 = ? ", "625 ", "643 ", "604 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.DODAWANIE);
        addQuestion(q87);
        Question q88 = new Question(" 307 + 192 = ? ", "516 ", "499 ", "471 ", 2, Question.DIFFICULTY_HARD,Sets.III, Category.DODAWANIE);
        addQuestion(q88);
        Question q89 = new Question(" 349 + 147 = ? ", "470 ", "514 ", "496 ", 3, Question.DIFFICULTY_HARD,Sets.III, Category.DODAWANIE);
        addQuestion(q89);
        Question q90 = new Question(" 410 + 287 = ? ", "697 ", "727 ", "672 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.DODAWANIE);
        addQuestion(q90);

        //zestawIV_dodawanie_easy q100
        Question q91 = new Question(" 10 + 3 = ? ", "13 ", "11 ", "12 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.DODAWANIE);
        addQuestion(q91);
        Question q92 = new Question(" 10 + 2 = ? ", "14 ", "12 ", "10 ", 2, Question.DIFFICULTY_EASY,Sets.IV, Category.DODAWANIE);
        addQuestion(q92);
        Question q93 = new Question(" 2 + 8 = ? ", "11 ", "9 ", "10 ", 3, Question.DIFFICULTY_EASY,Sets.IV, Category.DODAWANIE);
        addQuestion(q93);
        Question q94 = new Question(" 2 + 9 = ? ", "11 ", "14 ", "10 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.DODAWANIE);
        addQuestion(q94);
        Question q95 = new Question(" 8 + 8 = ? ", "14 ", "16 ", "18 ", 2, Question.DIFFICULTY_EASY,Sets.IV, Category.DODAWANIE);
        addQuestion(q95);
        Question q96 = new Question(" 10 + 2 = ? ", "11 ", "12 ", "14 ", 3, Question.DIFFICULTY_EASY,Sets.IV, Category.DODAWANIE);
        addQuestion(q96);
        Question q97 = new Question(" 4 + 3 = ? ", "7 ", "9 ", "6 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.DODAWANIE);
        addQuestion(q97);
        Question q98 = new Question(" 3 + 10 = ? ", "12 ", "13 ", "14 ", 2, Question.DIFFICULTY_EASY,Sets.IV, Category.DODAWANIE);
        addQuestion(q98);
        Question q99 = new Question(" 8 + 5 = ? ", "12 ", "14 ", "11 ", 3, Question.DIFFICULTY_EASY,Sets.IV, Category.DODAWANIE);
        addQuestion(q99);
        Question q100 = new Question(" 5 + 6 = ? ", "11 ", "13 ", "9 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.DODAWANIE);
        addQuestion(q100);

        //zestawIV_dodawanie_medium q110
        Question q101 = new Question(" 39 + 21 = ? ", "60 ", "65 ", "53 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.DODAWANIE);
        addQuestion(q101);
        Question q102 = new Question(" 43 + 16 = ? ", "68 ", "59 ", "50 ", 2, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.DODAWANIE);
        addQuestion(q102);
        Question q103 = new Question(" 33 + 35 = ? ", "62 ", "78 ", "68 ", 3, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.DODAWANIE);
        addQuestion(q103);
        Question q104 = new Question(" 15 + 47 = ? ", "62 ", "68 ", "53 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.DODAWANIE);
        addQuestion(q104);
        Question q105 = new Question(" 37 + 42 = ? ", "87 ", "79 ", "72 ", 2, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.DODAWANIE);
        addQuestion(q105);
        Question q106 = new Question(" 39 + 32 = ? ", "60 ", "78 ", "71 ", 3, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.DODAWANIE);
        addQuestion(q106);
        Question q107 = new Question(" 39 + 45 = ? ", "84 ", "93 ", "73 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.DODAWANIE);
        addQuestion(q107);
        Question q108 = new Question(" 45 + 45 = ? ", "98 ", "90 ", "79 ", 2, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.DODAWANIE);
        addQuestion(q108);
        Question q109 = new Question(" 29 + 21 = ? ", "42 ", "61 ", "50 ", 3, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.DODAWANIE);
        addQuestion(q109);
        Question q110 = new Question(" 41 + 45 = ? ", "86 ", "94 ", "78 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.DODAWANIE);
        addQuestion(q110);

        //zestawIV_dodawanie_hard q120
        Question q111 = new Question(" 447 + 226 = ? ", "673 ", "701 ", "656 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.DODAWANIE);
        addQuestion(q111);
        Question q112 = new Question(" 424 + 490 = ? ", "943 ", "914 ", "885 ", 2, Question.DIFFICULTY_HARD,Sets.IV, Category.DODAWANIE);
        addQuestion(q112);
        Question q113 = new Question(" 263 + 481 = ? ", "728 ", "770 ", "744 ", 3, Question.DIFFICULTY_HARD,Sets.IV, Category.DODAWANIE);
        addQuestion(q113);
        Question q114 = new Question(" 450 + 261 = ? ", "711 ", "733 ", "692 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.DODAWANIE);
        addQuestion(q114);
        Question q115 = new Question(" 282 + 201 = ? ", "511 ", "483 ", "460 ", 2, Question.DIFFICULTY_HARD,Sets.IV, Category.DODAWANIE);
        addQuestion(q115);
        Question q116 = new Question(" 186 + 182 = ? ", "349 ", "387 ", "368 ", 3, Question.DIFFICULTY_HARD,Sets.IV, Category.DODAWANIE);
        addQuestion(q116);
        Question q117 = new Question(" 379 + 262 = ? ", "641 ", "662 ", "613 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.DODAWANIE);
        addQuestion(q117);
        Question q118 = new Question(" 341 + 108 = ? ", "465 ", "449 ", "426 ", 2, Question.DIFFICULTY_HARD,Sets.IV, Category.DODAWANIE);
        addQuestion(q118);
        Question q119 = new Question(" 456 + 343 = ? ", "772 ", "827 ", "799 ", 3, Question.DIFFICULTY_HARD,Sets.IV, Category.DODAWANIE);
        addQuestion(q119);
        Question q120 = new Question(" 272 + 244 = ? ", "516 ", "546 ", "489 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.DODAWANIE);
        addQuestion(q120);

        //zestawI_odejmowanie_easy q130
        Question q121 = new Question(" 16 - 8 = ? ", "8 ", "11 ", "5 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q121);
        Question q122 = new Question(" 11 - 6 = ? ", "8 ", "5 ", "4 ", 2, Question.DIFFICULTY_EASY,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q122);
        Question q123 = new Question(" 16 - 9 = ? ", "4 ", "9 ", "7 ", 3, Question.DIFFICULTY_EASY,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q123);
        Question q124 = new Question(" 20 - 6 = ? ", "14 ", "15 ", "12 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q124);
        Question q125 = new Question(" 13 - 9 = ? ", "5 ", "4 ", "3 ", 2, Question.DIFFICULTY_EASY,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q125);
        Question q126 = new Question(" 18 - 6 = ? ", "9 ", "13 ", "12 ", 3, Question.DIFFICULTY_EASY,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q126);
        Question q127 = new Question(" 17 - 14 = ? ", "3 ", "2 ", "1 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q127);
        Question q128 = new Question(" 12 - 4 = ? ", "10 ", "8 ", "7 ", 2, Question.DIFFICULTY_EASY,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q128);
        Question q129 = new Question(" 16 - 11 = ? ", "3 ", "7 ", "5 ", 3, Question.DIFFICULTY_EASY,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q129);
        Question q130 = new Question(" 15 - 12 = ? ", "3 ", "2 ", "4 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q130);

        //zestawI_odejmowanie_medium q140
        Question q131 = new Question(" 36 - 23 = ? ", "13 ", "14 ", "16 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q131);
        Question q132 = new Question(" 86 - 68 = ? ", "20 ", "18 ", "13 ", 2, Question.DIFFICULTY_MEDIUM,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q132);
        Question q133 = new Question(" 98 - 52 = ? ", "37 ", "57 ", "46 ", 3, Question.DIFFICULTY_MEDIUM,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q133);
        Question q134 = new Question(" 99 - 63 = ? ", "36 ", "46 ", "31 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q134);
        Question q135 = new Question(" 96 - 61 = ? ", "41 ", "35 ", "25 ", 2, Question.DIFFICULTY_MEDIUM,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q135);
        Question q136 = new Question(" 82 - 52 = ? ", "19 ", "34 ", "30 ", 3, Question.DIFFICULTY_MEDIUM,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q136);
        Question q137 = new Question(" 79 - 54 = ? ", "25 ", "36 ", "15 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q137);
        Question q138 = new Question(" 84 - 47 = ? ", "45 ", "37 ", "31 ", 2, Question.DIFFICULTY_MEDIUM,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q138);
        Question q139 = new Question(" 71 - 48 = ? ", "13 ", "27 ", "23 ", 3, Question.DIFFICULTY_MEDIUM,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q139);
        Question q140 = new Question(" 66 - 36 = ? ", "30 ", "41 ", "26 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q140);

        //zestawI_odejmowanie_hard q150
        Question q141 = new Question(" 576 - 428 = ? ", "148 ", "169 ", "133 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q141);
        Question q142 = new Question(" 648 - 435 = ? ", "230 ", "213 ", "185 ", 2, Question.DIFFICULTY_HARD,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q142);
        Question q143 = new Question(" 767 - 683 = ? ", "64 ", "103 ", "84 ", 3, Question.DIFFICULTY_HARD,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q143);
        Question q144 = new Question(" 449 - 414 = ? ", "35 ", "37 ", "10 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q144);
        Question q145 = new Question(" 736 - 304 = ? ", "457 ", "432 ", "405 ", 2, Question.DIFFICULTY_HARD,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q145);
        Question q146 = new Question(" 975 - 222 = ? ", "731 ", "771 ", "753 ", 3, Question.DIFFICULTY_HARD,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q146);
        Question q147 = new Question(" 593 - 280 = ? ", "313 ", "336 ", "293 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q147);
        Question q148 = new Question(" 775 - 619 = ? ", "180 ", "156 ", "137 ", 2, Question.DIFFICULTY_HARD,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q148);
        Question q149 = new Question(" 600 - 429 = ? ", "149 ", "190 ", "171 ", 3, Question.DIFFICULTY_HARD,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q149);
        Question q150 = new Question(" 318 - 131 = ? ", "187 ", "210 ", "167 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.ODEJMOWANIE);
        addQuestion(q150);

        //zestawII_odejmowanie_easy q160
        Question q151 = new Question(" 13 - 4 = ? ", "9 ", "10 ", "6 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q151);
        Question q152 = new Question(" 6 - 4 = ? ", "3 ", "2 ", "1 ", 2, Question.DIFFICULTY_EASY,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q152);
        Question q153 = new Question(" 18 - 11 = ? ", "6 ", "10 ", "7 ", 3, Question.DIFFICULTY_EASY,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q153);
        Question q154 = new Question(" 20 - 14 = ? ", "6 ", "8 ", "5 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q154);
        Question q155 = new Question(" 14 - 10 = ? ", "7 ", "4 ", "3 ", 2, Question.DIFFICULTY_EASY,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q155);
        Question q156 = new Question(" 16 - 9 = ? ", "5 ", "8 ", "7 ", 3, Question.DIFFICULTY_EASY,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q156);
        Question q157 = new Question(" 16 - 5 = ? ", "11 ", "14 ", "10 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q157);
        Question q158 = new Question(" 15 - 6 = ? ", "10 ", "9 ", "6 ", 2, Question.DIFFICULTY_EASY,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q158);
        Question q159 = new Question(" 12 - 9 = ? ", "2 ", "4 ", "3 ", 3, Question.DIFFICULTY_EASY,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q159);
        Question q160 = new Question(" 12 - 10 = ? ", "2 ", "4 ", "3 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q160);

        //zestawII_odejmowanie_medium q170
        Question q161 = new Question(" 75 - 48 = ? ", "27 ", "34 ", "22 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q161);
        Question q162 = new Question(" 92 - 51 = ? ", "48 ", "41 ", "32 ", 2, Question.DIFFICULTY_MEDIUM,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q162);
        Question q163 = new Question(" 79 - 24 = ? ", "60 ", "55 ", "50 ", 3, Question.DIFFICULTY_MEDIUM,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q163);
        Question q164 = new Question(" 84 - 36 = ? ", "48 ", "55 ", "37 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q164);
        Question q165 = new Question(" 76 - 66 = ? ", "15 ", "10 ", "5 ", 2, Question.DIFFICULTY_MEDIUM,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q165);
        Question q166 = new Question(" 54 - 41 = ? ", "18 ", "13 ", "10 ", 3, Question.DIFFICULTY_MEDIUM,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q166);
        Question q167 = new Question(" 27 - 22 = ? ", "5 ", "4 ", "6 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q167);
        Question q168 = new Question(" 62 - 27 = ? ", "44 ", "35 ", "30 ", 2, Question.DIFFICULTY_MEDIUM,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q168);
        Question q169 = new Question(" 81 - 71 = ? ", "7 ", "13 ", "10 ", 3, Question.DIFFICULTY_MEDIUM,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q169);
        Question q170 = new Question(" 74 - 51 = ? ", "23 ", "30 ", "17 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q170);

        //zestawII_odejmowanie_hard q180
        Question q171 = new Question(" 734 - 507 = ? ", "227 ", "256 ", "199 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q171);
        Question q172 = new Question(" 968 - 128 = ? ", "861 ", "840 ", "822 ", 2, Question.DIFFICULTY_HARD,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q172);
        Question q173 = new Question(" 951 - 136 = ? ", "799 ", "839 ", "815 ", 3, Question.DIFFICULTY_HARD,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q173);
        Question q174 = new Question(" 289 - 197 = ? ", "92 ", "109 ", "75 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q174);
        Question q175 = new Question(" 332 - 257 = ? ", "97 ", "75 ", "58 ", 2, Question.DIFFICULTY_HARD,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q175);
        Question q176 = new Question(" 706 - 218 = ? ", "461 ", "512 ", "488 ", 3, Question.DIFFICULTY_HARD,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q176);
        Question q177 = new Question(" 404 - 370 = ? ", "34 ", "58 ", "17 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q177);
        Question q178 = new Question(" 300 - 167 = ? ", "153 ", "13 ", "104 ", 2, Question.DIFFICULTY_HARD,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q178);
        Question q179 = new Question(" 468 - 352 = ? ", "97 ", "136 ", "116 ", 3, Question.DIFFICULTY_HARD,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q179);
        Question q180 = new Question(" 615 - 111 = ? ", "504 ", "523 ", "480 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.ODEJMOWANIE);
        addQuestion(q180);

        //zestawIII_odejmowanie_easy q190
        Question q181 = new Question(" 14 - 8 = ? ", "6 ", "9 ", "3 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q181);
        Question q182 = new Question(" 20 - 5 = ? ", "17 ", "15 ", "13 ", 2, Question.DIFFICULTY_EASY,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q182);
        Question q183 = new Question(" 16 - 9 = ? ", "6 ", "9 ", "7 ", 3, Question.DIFFICULTY_EASY,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q183);
        Question q184 = new Question(" 12 - 6 = ? ", "6 ", "9 ", "5 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q184);
        Question q185 = new Question(" 13 - 6 = ? ", "11 ", "7 ", "6 ", 2, Question.DIFFICULTY_EASY,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q185);
        Question q186 = new Question(" 7 - 5 = ? ", "1 ", "3 ", "2 ", 3, Question.DIFFICULTY_EASY,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q186);
        Question q187 = new Question(" 20 - 10 = ? ", "10 ", "12 ", "7 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q187);
        Question q188 = new Question(" 17 - 4 = ? ", "14 ", "13 ", "10 ", 2, Question.DIFFICULTY_EASY,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q188);
        Question q189 = new Question(" 16 - 13 = ? ", "4 ", "6 ", "3 ", 3, Question.DIFFICULTY_EASY,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q189);
        Question q190 = new Question(" 17 - 10 = ? ", "7 ", "10 ", "4 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q190);

        //zestawIII_odejmowanie_medium q200
        Question q191 = new Question(" 61 - 42 = ? ", "19 ", "29 ", "11 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q191);
        Question q192 = new Question(" 73 - 17 = ? ", "66 ", "56 ", "46 ", 2, Question.DIFFICULTY_MEDIUM,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q192);
        Question q193 = new Question(" 81 - 24 = ? ", "47 ", "64 ", "57 ", 3, Question.DIFFICULTY_MEDIUM,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q193);
        Question q194 = new Question(" 62 - 40 = ? ", "22 ", "32 ", "17 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q194);
        Question q195 = new Question(" 34 - 23 = ? ", "12 ", "11 ", "9 ", 2, Question.DIFFICULTY_MEDIUM,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q195);
        Question q196 = new Question(" 90 - 40 = ? ", "40 ", "58 ", "50 ", 3, Question.DIFFICULTY_MEDIUM,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q196);
        Question q197 = new Question(" 80 - 19 = ? ", "61 ", "66 ", "50 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q197);
        Question q198 = new Question(" 97 - 28 = ? ", "74 ", "69 ", "59 ", 2, Question.DIFFICULTY_MEDIUM,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q198);
        Question q199 = new Question(" 88 - 32 = ? ", "48 ", "67 ", "56 ", 3, Question.DIFFICULTY_MEDIUM,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q199);
        Question q200 = new Question(" 91 - 42 = ? ", "49 ", "60 ", "39 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q200);

        //zestawIII_odejmowanie_hard q210
        Question q201 = new Question(" 662 - 472 = ? ", "190 ", "207 ", "161 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q201);
        Question q202 = new Question(" 709 - 181 = ? ", "547 ", "528 ", "503 ", 2, Question.DIFFICULTY_HARD,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q202);
        Question q203 = new Question(" 729 - 121 = ? ", "583 ", "636 ", "608 ", 3, Question.DIFFICULTY_HARD,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q203);
        Question q204 = new Question(" 669 - 137 = ? ", "532 ", "548 ", "513 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q204);
        Question q205 = new Question(" 734 - 171 = ? ", "581 ", "563 ", "539 ", 2, Question.DIFFICULTY_HARD,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q205);
        Question q206 = new Question(" 887 - 392 = ? ", "478 ", "512 ", "495 ", 3, Question.DIFFICULTY_HARD,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q206);
        Question q207 = new Question(" 966 - 454 = ? ", "512 ", "539 ", "484 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q207);
        Question q208 = new Question(" 508 - 298 = ? ", "234 ", "210 ", "189 ", 2, Question.DIFFICULTY_HARD,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q208);
        Question q209 = new Question(" 514 - 231 = ? ", "260 ", "310 ", "283 ", 3, Question.DIFFICULTY_HARD,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q209);
        Question q210 = new Question(" 702 - 194 = ? ", "508 ", "528 ", "481 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.ODEJMOWANIE);
        addQuestion(q210);

        //zestawIV_odejmowanie_easy q220
        Question q211 = new Question(" 16 - 10 = ? ", "6 ", "10 ", "3 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q211);
        Question q212 = new Question(" 11 - 9 = ? ", "1 ", "2 ", "3 ", 2, Question.DIFFICULTY_EASY,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q212);
        Question q213 = new Question(" 17 - 8 = ? ", "7 ", "8 ", "9 ", 3, Question.DIFFICULTY_EASY,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q213);
        Question q214 = new Question(" 9 - 7 = ? ", "2 ", "5 ", "3 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q214);
        Question q215 = new Question(" 12 - 6 = ? ", "4 ", "6 ", "5 ", 2, Question.DIFFICULTY_EASY,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q215);
        Question q216 = new Question(" 16 - 9 = ? ", "5 ", "9 ", "7 ", 3, Question.DIFFICULTY_EASY,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q216);
        Question q217 = new Question(" 14 - 10 = ? ", "4 ", "8 ", "1 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q217);
        Question q218 = new Question(" 10 - 7 = ? ", "1 ", "3 ", "2 ", 2, Question.DIFFICULTY_EASY,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q218);
        Question q219 = new Question(" 13 - 5 = ? ", "5 ", "11 ", "8 ", 3, Question.DIFFICULTY_EASY,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q219);
        Question q220 = new Question(" 20 - 17 = ? ", "3 ", "7 ", "1 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q220);

        //zestawIV_odejmowanie_medium q230
        Question q221 = new Question(" 83 - 40 = ? ", "43 ", "50 ", "39 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q221);
        Question q222 = new Question(" 97 - 72 = ? ", "34 ", "25 ", "14 ", 2, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q222);
        Question q223 = new Question(" 75 - 60 = ? ", "11 ", "22 ", "15 ", 3, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q223);
        Question q224 = new Question(" 56 - 41 = ? ", "15 ", "11 ", "13 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q224);
        Question q225 = new Question(" 93 - 27 = ? ", "71 ", "66 ", "56 ", 2, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q225);
        Question q226 = new Question(" 63 - 27 = ? ", "27 ", "47 ", "36 ", 3, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q226);
        Question q227 = new Question(" 80 - 53 = ? ", "27 ", "34 ", "20 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q227);
        Question q228 = new Question(" 98 - 23 = ? ", "79 ", "75 ", "69 ", 2, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q228);
        Question q229 = new Question(" 73 - 52 = ? ", "14 ", "31 ", "21 ", 3, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q229);
        Question q230 = new Question(" 95 - 49 = ? ", "46 ", "55 ", "40 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q230);

        //zestawIV_odejmowanie_hard q240
        Question q231 = new Question(" 634 - 530 = ? ", "104 ", "123 ", "77 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q231);
        Question q232 = new Question(" 805 - 640 = ? ", "193 ", "165 ", "148 ", 2, Question.DIFFICULTY_HARD,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q232);
        Question q233 = new Question(" 816 - 595 = ? ", "206 ", "245 ", "221 ", 3, Question.DIFFICULTY_HARD,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q233);
        Question q234 = new Question(" 952 - 202 = ? ", "750 ", "772 ", "731 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q234);
        Question q235 = new Question(" 937 - 605 = ? ", "355 ", "332 ", "304 ", 2, Question.DIFFICULTY_HARD,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q235);
        Question q236 = new Question(" 946 - 854 = ? ", "63 ", "117 ", "92 ", 3, Question.DIFFICULTY_HARD,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q236);
        Question q237 = new Question(" 678 - 214 = ? ", "464 ", "490 ", "447 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q237);
        Question q238 = new Question(" 679 - 431 = ? ", "266 ", "248 ", "225 ", 2, Question.DIFFICULTY_HARD,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q238);
        Question q239 = new Question(" 797 - 539 = ? ", "235 ", "280 ", "258 ", 3, Question.DIFFICULTY_HARD,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q239);
        Question q240 = new Question(" 581 - 499 = ? ", "82 ", "108 ", "62 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.ODEJMOWANIE);
        addQuestion(q240);


        //zestawI_mnozenie_easy q250
        Question q241 = new Question(" 2 * 3 = ? ", "6 ", "5 ", "4 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.MNOŻENIE);
        addQuestion(q241);
        Question q242 = new Question(" 3 * 3 = ? ", "7 ", "9 ", "8 ", 2, Question.DIFFICULTY_EASY,Sets.I, Category.MNOŻENIE);
        addQuestion(q242);
        Question q243 = new Question(" 3 * 4 = ? ", "13 ", "14 ", "12 ", 3, Question.DIFFICULTY_EASY,Sets.I, Category.MNOŻENIE);
        addQuestion(q243);
        Question q244 = new Question(" 5 * 3 = ? ", "15 ", "12 ", "13 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.MNOŻENIE);
        addQuestion(q244);
        Question q245 = new Question(" 6 * 2 = ? ", "15 ", "12 ", "13 ", 2, Question.DIFFICULTY_EASY,Sets.I, Category.MNOŻENIE);
        addQuestion(q245);
        Question q246 = new Question(" 5 * 4 = ? ", "18 ", "15 ", "20 ", 3, Question.DIFFICULTY_EASY,Sets.I, Category.MNOŻENIE);
        addQuestion(q246);
        Question q247 = new Question(" 4 * 2 = ? ", "8 ", "9 ", "5 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.MNOŻENIE);
        addQuestion(q247);
        Question q248 = new Question(" 2 * 2 = ? ", "3 ", "4 ", "6 ", 2, Question.DIFFICULTY_EASY,Sets.I, Category.MNOŻENIE);
        addQuestion(q248);
        Question q249 = new Question(" 6 * 3 = ? ", "12 ", "14 ", "18 ", 3, Question.DIFFICULTY_EASY,Sets.I, Category.MNOŻENIE);
        addQuestion(q249);
        Question q250 = new Question(" 4 * 4 = ? ", "16 ", "20 ", "12 ", 1, Question.DIFFICULTY_EASY,Sets.I, Category.MNOŻENIE);
        addQuestion(q250);

        //zestawI_mnozenie_medium q260
        Question q251 = new Question(" 9 * 8 = ? ", "72 ", "85 ", "74 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.MNOŻENIE);
        addQuestion(q251);
        Question q252 = new Question(" 5 * 9 = ? ", "47 ", "45 ", "50 ", 2, Question.DIFFICULTY_MEDIUM,Sets.I, Category.MNOŻENIE);
        addQuestion(q252);
        Question q253 = new Question(" 10 * 5 = ? ", "43 ", "56 ", "50 ", 3, Question.DIFFICULTY_MEDIUM,Sets.I, Category.MNOŻENIE);
        addQuestion(q253);
        Question q254 = new Question(" 7 * 6 = ? ", "42 ", "47 ", "34 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.MNOŻENIE);
        addQuestion(q254);
        Question q255 = new Question(" 7 * 4 = ? ", "14 ", "28 ", "21 ", 2, Question.DIFFICULTY_MEDIUM,Sets.I, Category.MNOŻENIE);
        addQuestion(q255);
        Question q256 = new Question(" 7 * 5 = ? ", "32 ", "41 ", "35 ", 3, Question.DIFFICULTY_MEDIUM,Sets.I, Category.MNOŻENIE);
        addQuestion(q256);
        Question q257 = new Question(" 10 * 7 = ? ", "70 ", "78 ", "59 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.MNOŻENIE);
        addQuestion(q257);
        Question q258 = new Question(" 7 * 6 = ? ", "50 ", "42 ", "33 ", 2, Question.DIFFICULTY_MEDIUM,Sets.I, Category.MNOŻENIE);
        addQuestion(q258);
        Question q259 = new Question(" 6 * 3 = ? ", "27 ", "23 ", "18 ", 3, Question.DIFFICULTY_MEDIUM,Sets.I, Category.MNOŻENIE);
        addQuestion(q259);
        Question q260 = new Question(" 7 * 7 = ? ", "49 ", "55 ", "41 ", 1, Question.DIFFICULTY_MEDIUM,Sets.I, Category.MNOŻENIE);
        addQuestion(q260);

        //zestawI_mnozenie_hard q270
        Question q261 = new Question(" 13 * 12 = ? ", "156 ", "184 ", "139 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.MNOŻENIE);
        addQuestion(q261);
        Question q262 = new Question(" 17 * 13 = ? ", "242 ", "221 ", "194 ", 2, Question.DIFFICULTY_HARD,Sets.I, Category.MNOŻENIE);
        addQuestion(q262);
        Question q263 = new Question(" 15 * 14 = ? ", "192 ", "237 ", "210 ", 3, Question.DIFFICULTY_HARD,Sets.I, Category.MNOŻENIE);
        addQuestion(q263);
        Question q264 = new Question(" 19 * 15 = ? ", "285 ", "314 ", "267 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.MNOŻENIE);
        addQuestion(q264);
        Question q265 = new Question(" 20 * 18 = ? ", "380 ", "360 ", "350 ", 2, Question.DIFFICULTY_HARD,Sets.I, Category.MNOŻENIE);
        addQuestion(q265);
        Question q266 = new Question(" 18 * 14 = ? ", "225 ", "278 ", "252 ", 3, Question.DIFFICULTY_HARD,Sets.I, Category.MNOŻENIE);
        addQuestion(q266);
        Question q267 = new Question(" 17 * 13 = ? ", "221 ", "251 ", "198 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.MNOŻENIE);
        addQuestion(q267);
        Question q268 = new Question(" 19 * 13 = ? ", "274 ", "247 ", "224 ", 2, Question.DIFFICULTY_HARD,Sets.I, Category.MNOŻENIE);
        addQuestion(q268);
        Question q269 = new Question(" 15 * 11 = ? ", "145 ", "185 ", "165 ", 3, Question.DIFFICULTY_HARD,Sets.I, Category.MNOŻENIE);
        addQuestion(q269);
        Question q270 = new Question(" 19 * 12 = ? ", "228 ", "246 ", "204 ", 1, Question.DIFFICULTY_HARD,Sets.I, Category.MNOŻENIE);
        addQuestion(q270);

        //zestawII_mnozenie_easy q280
        Question q271 = new Question(" 4 * 3 = ? ", "12 ", "14 ", "10 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.MNOŻENIE);
        addQuestion(q271);
        Question q272 = new Question(" 4 * 2 = ? ", "6 ", "8 ", "5 ", 2, Question.DIFFICULTY_EASY,Sets.II, Category.MNOŻENIE);
        addQuestion(q272);
        Question q273 = new Question(" 3 * 6 = ? ", "16 ", "20 ", "18 ", 3, Question.DIFFICULTY_EASY,Sets.II, Category.MNOŻENIE);
        addQuestion(q273);
        Question q274 = new Question(" 3 * 2 = ? ", "6 ", "9 ", "4 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.MNOŻENIE);
        addQuestion(q274);
        Question q275 = new Question(" 6 * 2 = ? ", "13 ", "12 ", "11 ", 2, Question.DIFFICULTY_EASY,Sets.II, Category.MNOŻENIE);
        addQuestion(q275);
        Question q276 = new Question(" 5 * 2 = ? ", "8 ", "11 ", "10 ", 3, Question.DIFFICULTY_EASY,Sets.II, Category.MNOŻENIE);
        addQuestion(q276);
        Question q277 = new Question(" 3 * 5 = ? ", "15 ", "13 ", "17 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.MNOŻENIE);
        addQuestion(q277);
        Question q278 = new Question(" 2 * 9 = ? ", "16 ", "18 ", "20 ", 2, Question.DIFFICULTY_EASY,Sets.II, Category.MNOŻENIE);
        addQuestion(q278);
        Question q279 = new Question(" 7 * 2 = ? ", "18 ", "16 ", "14 ", 3, Question.DIFFICULTY_EASY,Sets.II, Category.MNOŻENIE);
        addQuestion(q279);
        Question q280 = new Question(" 5 * 2 = ? ", "10 ", "12 ", "8 ", 1, Question.DIFFICULTY_EASY,Sets.II, Category.MNOŻENIE);
        addQuestion(q280);

        //zestawII_mnozenie_medium q290
        Question q281 = new Question(" 8 * 7 = ? ", "56 ", "65 ", "51 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.MNOŻENIE);
        addQuestion(q281);
        Question q282 = new Question(" 9 * 8 = ? ", "82 ", "72 ", "66 ", 2, Question.DIFFICULTY_MEDIUM,Sets.II, Category.MNOŻENIE);
        addQuestion(q282);
        Question q283 = new Question(" 9 * 4 = ? ", "45 ", "40 ", "36 ", 3, Question.DIFFICULTY_MEDIUM,Sets.II, Category.MNOŻENIE);
        addQuestion(q283);
        Question q284 = new Question(" 8 * 5 = ? ", "40 ", "49 ", "32 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.MNOŻENIE);
        addQuestion(q284);
        Question q285 = new Question(" 5 * 4 = ? ", "25 ", "20 ", "15 ", 2, Question.DIFFICULTY_MEDIUM,Sets.II, Category.MNOŻENIE);
        addQuestion(q285);
        Question q286 = new Question(" 9 * 5 = ? ", "35 ", "55 ", "45 ", 3, Question.DIFFICULTY_MEDIUM,Sets.II, Category.MNOŻENIE);
        addQuestion(q286);
        Question q287 = new Question(" 7 * 5 = ? ", "35 ", "39 ", "30 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.MNOŻENIE);
        addQuestion(q287);
        Question q288 = new Question(" 9 * 7 = ? ", "67 ", "63 ", "55 ", 2, Question.DIFFICULTY_MEDIUM,Sets.II, Category.MNOŻENIE);
        addQuestion(q288);
        Question q289 = new Question(" 6 * 5 = ? ", "36 ", "34 ", "30 ", 3, Question.DIFFICULTY_MEDIUM,Sets.II, Category.MNOŻENIE);
        addQuestion(q289);
        Question q290 = new Question(" 9 * 9 = ? ", "81 ", "71 ", "91 ", 1, Question.DIFFICULTY_MEDIUM,Sets.II, Category.MNOŻENIE);
        addQuestion(q290);

        //zestawII_mnozenie_hard q300
        Question q291 = new Question(" 17 * 15 = ? ", "255 ", "279 ", "232 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.MNOŻENIE);
        addQuestion(q291);
        Question q292 = new Question(" 17 * 12 = ? ", "228 ", "204 ", "178 ", 2, Question.DIFFICULTY_HARD,Sets.II, Category.MNOŻENIE);
        addQuestion(q292);
        Question q293 = new Question(" 15 * 11 = ? ", "139 ", "192 ", "165 ", 3, Question.DIFFICULTY_HARD,Sets.II, Category.MNOŻENIE);
        addQuestion(q293);
        Question q294 = new Question(" 18 * 12 = ? ", "216 ", "241 ", "199 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.MNOŻENIE);
        addQuestion(q294);
        Question q295 = new Question(" 20 * 14 = ? ", "320 ", "280 ", "261 ", 2, Question.DIFFICULTY_HARD,Sets.II, Category.MNOŻENIE);
        addQuestion(q295);
        Question q296 = new Question(" 16 * 15 = ? ", "214 ", "262 ", "240 ", 3, Question.DIFFICULTY_HARD,Sets.II, Category.MNOŻENIE);
        addQuestion(q296);
        Question q297 = new Question(" 19 * 12 = ? ", "228 ", "258 ", "211 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.MNOŻENIE);
        addQuestion(q297);
        Question q298 = new Question(" 18 * 13 = ? ", "257 ", "234 ", "205 ", 2, Question.DIFFICULTY_HARD,Sets.II, Category.MNOŻENIE);
        addQuestion(q298);
        Question q299 = new Question(" 17 * 16 = ? ", "260 ", "289 ", "272 ", 3, Question.DIFFICULTY_HARD,Sets.II, Category.MNOŻENIE);
        addQuestion(q299);
        Question q300 = new Question(" 19 * 14 = ? ", "266 ", "294 ", "241 ", 1, Question.DIFFICULTY_HARD,Sets.II, Category.MNOŻENIE);
        addQuestion(q300);

        //zestawIII_mnozenie_easy q310
        Question q301 = new Question(" 2 * 8 = ? ", "16 ", "14 ", "12 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.MNOŻENIE);
        addQuestion(q301);
        Question q302 = new Question(" 9 * 2 = ? ", "16 ", "14 ", "18 ", 2, Question.DIFFICULTY_EASY,Sets.III, Category.MNOŻENIE);
        addQuestion(q302);
        Question q303 = new Question(" 2 * 2 = ? ", "6 ", "5 ", "4 ", 3, Question.DIFFICULTY_EASY,Sets.III, Category.MNOŻENIE);
        addQuestion(q303);
        Question q304 = new Question(" 4 * 3 = ? ", "12 ", "13 ", "10 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.MNOŻENIE);
        addQuestion(q304);
        Question q305 = new Question(" 3 * 2 = ? ", "5 ", "6 ", "4 ", 2, Question.DIFFICULTY_EASY,Sets.III, Category.MNOŻENIE);
        addQuestion(q305);
        Question q306 = new Question(" 3 * 3 = ? ", "6 ", "7 ", "9 ", 3, Question.DIFFICULTY_EASY,Sets.III, Category.MNOŻENIE);
        addQuestion(q306);
        Question q307 = new Question(" 5 * 2 = ? ", "10 ", "12 ", "8 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.MNOŻENIE);
        addQuestion(q307);
        Question q308 = new Question(" 7 * 1 = ? ", "9 ", "7 ", "14 ", 2, Question.DIFFICULTY_EASY,Sets.III, Category.MNOŻENIE);
        addQuestion(q308);
        Question q309 = new Question(" 7 * 2 = ? ", "12 ", "18 ", "14 ", 3, Question.DIFFICULTY_EASY,Sets.III, Category.MNOŻENIE);
        addQuestion(q309);
        Question q310 = new Question(" 3 * 4 = ? ", "12 ", "14 ", "10 ", 1, Question.DIFFICULTY_EASY,Sets.III, Category.MNOŻENIE);
        addQuestion(q310);

        //zestawIII_mnozenie_medium q320
        Question q311 = new Question(" 5 * 4 = ? ", "20 ", "24 ", "26 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.MNOŻENIE);
        addQuestion(q311);
        Question q312 = new Question(" 9 * 7 = ? ", "70 ", "63 ", "57 ", 2, Question.DIFFICULTY_MEDIUM,Sets.III, Category.MNOŻENIE);
        addQuestion(q312);
        Question q313 = new Question(" 9 * 5 = ? ", "55 ", "51 ", "45 ", 3, Question.DIFFICULTY_MEDIUM,Sets.III, Category.MNOŻENIE);
        addQuestion(q313);
        Question q314 = new Question(" 8 * 4 = ? ", "32 ", "43 ", "24 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.MNOŻENIE);
        addQuestion(q314);
        Question q315 = new Question(" 6 * 4 = ? ", "28 ", "24 ", "16 ", 2, Question.DIFFICULTY_MEDIUM,Sets.III, Category.MNOŻENIE);
        addQuestion(q315);
        Question q316 = new Question(" 7 * 5 = ? ", "25 ", "44 ", "35 ", 3, Question.DIFFICULTY_MEDIUM,Sets.III, Category.MNOŻENIE);
        addQuestion(q316);
        Question q317 = new Question(" 9 * 4 = ? ", "36 ", "40 ", "44 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.MNOŻENIE);
        addQuestion(q317);
        Question q318 = new Question(" 8 * 7 = ? ", "60 ", "56 ", "48 ", 2, Question.DIFFICULTY_MEDIUM,Sets.III, Category.MNOŻENIE);
        addQuestion(q318);
        Question q319 = new Question(" 7 * 4 = ? ", "18 ", "38 ", "28 ", 3, Question.DIFFICULTY_MEDIUM,Sets.III, Category.MNOŻENIE);
        addQuestion(q319);
        Question q320 = new Question(" 11 * 5 = ? ", "55 ", "44 ", "66 ", 1, Question.DIFFICULTY_MEDIUM,Sets.III, Category.MNOŻENIE);
        addQuestion(q320);

        //zestawIII_mnozenie_hard q330
        Question q321 = new Question(" 19 * 15 = ? ", "285 ", "309 ", "259 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.MNOŻENIE);
        addQuestion(q321);
        Question q322 = new Question(" 17 * 15 = ? ", "275 ", "255 ", "234 ", 2, Question.DIFFICULTY_HARD,Sets.III, Category.MNOŻENIE);
        addQuestion(q322);
        Question q323 = new Question(" 18 * 16 = ? ", "273 ", "303 ", "288 ", 3, Question.DIFFICULTY_HARD,Sets.III, Category.MNOŻENIE);
        addQuestion(q323);
        Question q324 = new Question(" 19 * 18 = ? ", "342 ", "361 ", "326 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.MNOŻENIE);
        addQuestion(q324);
        Question q325 = new Question(" 18 * 13 = ? ", "225 ", "234 ", "213 ", 2, Question.DIFFICULTY_HARD,Sets.III, Category.MNOŻENIE);
        addQuestion(q325);
        Question q326 = new Question(" 12 * 16 = ? ", "192 ", "224 ", "240 ", 3, Question.DIFFICULTY_HARD,Sets.III, Category.MNOŻENIE);
        addQuestion(q326);
        Question q327 = new Question(" 15 * 11 = ? ", "165 ", "189 ", "142 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.MNOŻENIE);
        addQuestion(q327);
        Question q328 = new Question(" 15 * 13 = ? ", "212 ", "195 ", "178 ", 2, Question.DIFFICULTY_HARD,Sets.III, Category.MNOŻENIE);
        addQuestion(q328);
        Question q329 = new Question(" 20 * 15 = ? ", "280 ", "315 ", "300 ", 3, Question.DIFFICULTY_HARD,Sets.III, Category.MNOŻENIE);
        addQuestion(q329);
        Question q330 = new Question(" 16 * 15 = ? ", "240 ", "269 ", "213 ", 1, Question.DIFFICULTY_HARD,Sets.III, Category.MNOŻENIE);
        addQuestion(q330);

        //zestawIV_mnozenie_easy q340
        Question q331 = new Question(" 3 * 4 = ? ", "12 ", "16 ", "14 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.MNOŻENIE);
        addQuestion(q331);
        Question q332 = new Question(" 9 * 2 = ? ", "20 ", "18 ", "19 ", 2, Question.DIFFICULTY_EASY,Sets.IV, Category.MNOŻENIE);
        addQuestion(q332);
        Question q333 = new Question(" 8 * 2 = ? ", "14 ", "20 ", "16 ", 3, Question.DIFFICULTY_EASY,Sets.IV, Category.MNOŻENIE);
        addQuestion(q333);
        Question q334 = new Question(" 5 * 3 = ? ", "15 ", "12 ", "16 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.MNOŻENIE);
        addQuestion(q334);
        Question q335 = new Question(" 10 * 2 = ? ", "10 ", "20 ", "15 ", 2, Question.DIFFICULTY_EASY,Sets.IV, Category.MNOŻENIE);
        addQuestion(q335);
        Question q336 = new Question(" 4 * 2 = ? ", "6 ", "10 ", "8 ", 3, Question.DIFFICULTY_EASY,Sets.IV, Category.MNOŻENIE);
        addQuestion(q336);
        Question q337 = new Question(" 7 * 2 = ? ", "14 ", "16 ", "13 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.MNOŻENIE);
        addQuestion(q337);
        Question q338 = new Question(" 5 * 2 = ? ", "14 ", "10 ", "9 ", 2, Question.DIFFICULTY_EASY,Sets.IV, Category.MNOŻENIE);
        addQuestion(q338);
        Question q339 = new Question(" 2 * 3 = ? ", "5 ", "7 ", "6 ", 3, Question.DIFFICULTY_EASY,Sets.IV, Category.MNOŻENIE);
        addQuestion(q339);
        Question q340 = new Question(" 5 * 4 = ? ", "20 ", "15 ", "10 ", 1, Question.DIFFICULTY_EASY,Sets.IV, Category.MNOŻENIE);
        addQuestion(q340);

        //zestawIV_mnozenie_medium q350
        Question q341 = new Question(" 15 * 6 = ? ", "90 ", "100 ", "80 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.MNOŻENIE);
        addQuestion(q341);
        Question q342 = new Question(" 11 * 7 = ? ", "70 ", "77 ", "64 ", 2, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.MNOŻENIE);
        addQuestion(q342);
        Question q343 = new Question(" 12 * 4 = ? ", "40 ", "60 ", "48 ", 3, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.MNOŻENIE);
        addQuestion(q343);
        Question q344 = new Question(" 9 * 5 = ? ", "45 ", "51 ", "40 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.MNOŻENIE);
        addQuestion(q344);
        Question q345 = new Question(" 12 * 5 = ? ", "68 ", "60 ", "54 ", 2, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.MNOŻENIE);
        addQuestion(q345);
        Question q346 = new Question(" 11 * 6 = ? ", "56 ", "73 ", "66 ", 3, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.MNOŻENIE);
        addQuestion(q346);
        Question q347 = new Question(" 8 * 7 = ? ", "56 ", "65 ", "52 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.MNOŻENIE);
        addQuestion(q347);
        Question q348 = new Question(" 12 * 6 = ? ", "82 ", "72 ", "62 ", 2, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.MNOŻENIE);
        addQuestion(q348);
        Question q349 = new Question(" 14 * 7 = ? ", "91 ", "95 ", "98 ", 3, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.MNOŻENIE);
        addQuestion(q349);
        Question q350 = new Question(" 7 * 13 = ? ", "91 ", "99 ", "95 ", 1, Question.DIFFICULTY_MEDIUM,Sets.IV, Category.MNOŻENIE);
        addQuestion(q350);

        //zestawIV_mnozenie_hard q360
        Question q351 = new Question(" 15 * 13 = ? ", "195 ", "220 ", "173 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.MNOŻENIE);
        addQuestion(q351);
        Question q352 = new Question(" 13 * 11 = ? ", "165 ", "143 ", "121 ", 2, Question.DIFFICULTY_HARD,Sets.IV, Category.MNOŻENIE);
        addQuestion(q352);
        Question q353 = new Question(" 18 * 15 = ? ", "242 ", "290 ", "270 ", 3, Question.DIFFICULTY_HARD,Sets.IV, Category.MNOŻENIE);
        addQuestion(q353);
        Question q354 = new Question(" 20 * 12 = ? ", "240 ", "268 ", "210 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.MNOŻENIE);
        addQuestion(q354);
        Question q355 = new Question(" 18 * 15 = ? ", "291 ", "270 ", "240 ", 2, Question.DIFFICULTY_HARD,Sets.IV, Category.MNOŻENIE);
        addQuestion(q355);
        Question q356 = new Question(" 20 * 19 = ? ", "357 ", "399 ", "380 ", 3, Question.DIFFICULTY_HARD,Sets.IV, Category.MNOŻENIE);
        addQuestion(q356);
        Question q357 = new Question(" 20 * 16 = ? ", "320 ", "347 ", "291 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.MNOŻENIE);
        addQuestion(q357);
        Question q358 = new Question(" 19 * 18 = ? ", "359 ", "342 ", "327 ", 2, Question.DIFFICULTY_HARD,Sets.IV, Category.MNOŻENIE);
        addQuestion(q358);
        Question q359 = new Question(" 20 * 19 = ? ", "365 ", "409 ", "380 ", 3, Question.DIFFICULTY_HARD,Sets.IV, Category.MNOŻENIE);
        addQuestion(q359);
        Question q360 = new Question(" 19 * 17 = ? ", "323 ", "339 ", "300 ", 1, Question.DIFFICULTY_HARD,Sets.IV, Category.MNOŻENIE);
        addQuestion(q360);


        //zestawI_dzielenie_easy q370

        //zestawI_dzielenie_medium q380

        //zestawI_dzielenie_hard q390

        //zestawII_dzielenie_easy q400

        //zestawII_dzielenie_medium q410

        //zestawII_dzielenie_hard q420

        //zestawIII_dzielenie_easy q430

        //zestawIII_dzielenie_medium q440

        //zestawIII_dzielenie_hard q450

        //zestawIV_dzielenie_easy q460

        //zestawIV_dzielenie_medium q470

        //zestawIV_dzielenie_hard q480
        */
    }
    private void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_SETS_ID, question.getSetsID());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }
    public List<Sets> getAllSets() {
        List<Sets> setsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + SetsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                Sets sets = new Sets();
                sets.setId(c.getInt(c.getColumnIndex(SetsTable._ID)));
                sets.setName(c.getString(c.getColumnIndex(SetsTable.COLUMN_NAME)));
               setsList.add(sets);
            } while (c.moveToNext());
        }
        c.close();
        return setsList;
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if(c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setSetsID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SETS_ID)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while( c.moveToNext());
        }
        c.close();
        return questionList;
    }
    public ArrayList<Question> getQuestions(int setsID, int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

       String selection = QuestionsTable.COLUMN_SETS_ID + " = ? " + " AND " +
               QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
               " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
       String[] selectionArgs = new String[] {
               String.valueOf(setsID), String.valueOf(categoryID), difficulty
       };
       Cursor c = db.query(
               QuestionsTable.TABLE_NAME,
               null,
               selection,
               selectionArgs,
               null,
               null,
               null
       );
        if(c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setSetsID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_SETS_ID)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            }while( c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
