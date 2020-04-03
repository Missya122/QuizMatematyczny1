package com.example.quizdladzieci;

import androidx.annotation.NonNull;

public class Sets {

    public static final int I = 1;
    public static final int II = 2;
    public static final int III = 3;
    public static final int  IV = 4;

    private int id_s;
    private String name;

    public Sets() {

    }

    public Sets(String name) {
        this.name = name;
    }

    public int getId() {
        return id_s;
    }

    public void setId(int id_s) {
        this.id_s = id_s;
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
