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
