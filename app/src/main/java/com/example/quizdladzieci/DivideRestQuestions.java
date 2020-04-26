package com.example.quizdladzieci;

public class DivideRestQuestions {
    private int result;
    private int rest;
    private String question;

    public DivideRestQuestions() {}

    public DivideRestQuestions(String question, int result, int rest) {
        this.question = question;
        this.result = result;
        this.rest = rest;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
