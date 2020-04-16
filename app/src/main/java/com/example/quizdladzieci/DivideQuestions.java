package com.example.quizdladzieci;

public class DivideQuestions {
    private String question;
    private int result;

    public DivideQuestions() {}

    public DivideQuestions(String question, int result) {
        this.question = question;
        this.result = result;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
