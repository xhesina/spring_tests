package com.example.demo.UserModel;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

public class Days {
    @Field
    private String dayName;
    @Field
    private ArrayList<Detyra> duties;
    public Days(String dayName) {
        this.dayName=dayName;
        this.duties=new ArrayList<Detyra>();
    }
    @PersistenceConstructor
   public Days(String dayName,ArrayList<Detyra> duties) {
        this.dayName = dayName;
        this.duties = duties;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String emerdite) {
        this.dayName = emerdite;
    }

    public  List<Detyra> getDuties() {
        return duties;
    }

    public void setDutiesa(ArrayList<Detyra> listedetyra) {
        this.duties= listedetyra;
    }
    public void shtoDetyre(Detyra detyra){
        duties.add(detyra);
    }
}
