package com.example.quizdladzieci;

import androidx.annotation.NonNull;

public class Category {

    public static final int  DODAWANIE = 1;
    public static final int ODEJMOWANIE = 2;
    public static final int MNOŻENIE = 3;
    public static final int DZIELENIE = 4;

    private int id;
    private String name;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return getName();
    }
}
