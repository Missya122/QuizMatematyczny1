package com.example.quizdladzieci;

import android.provider.BaseColumns;

public final class CalculatorContract {

    private CalculatorContract() {}

    public static class QestionTable implements BaseColumns {
        public static final String TABLE_NAME = "divide_question";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_RESULT = "result";
    }
}
