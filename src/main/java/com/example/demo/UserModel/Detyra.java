package com.example.demo.UserModel;

import org.springframework.data.mongodb.core.mapping.Field;

public class  Detyra {
    @Field
    private String date;
    @Field
    private String duties;

    public Detyra(String date, String duties) {
        this.date = date;
        this.duties = duties;
    }

    public Detyra() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String data) {
        date = data;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String emerDetyre) {
        this.duties = emerDetyre;
    }
}
