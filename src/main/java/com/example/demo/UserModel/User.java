package com.example.demo.UserModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Users")
public class User {
    @Id
    private String id;
    @Field
    private String fName;
    @Field
    private String lName;
    @Field
    private String username;
    @Field
    private String  email;
    @Field
    private String password;
    @Field
   private Days[] days;
   // private List<Days> days;

    public User(String fName, String lName, String username, String email, String password) {
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.email = email;
        this.password = password;
       this.days= new Days[]{new Days("E hene"),new Days("E marte"),new Days("E merkure"),new Days("E enjte"),
       new Days("E premte"),new Days("E shtune"),new Days("E diel")};
       /* days= new ArrayList<Days>();
        days.add(new Days("Monday"));
        days.add(new Days("Tuesday"));
        days.add(new Days("Wednsday"));
        days.add(new Days("Thursday"));
        days.add(new Days("Friday"));
        days.add(new Days("Saturday"));
        days.add(new Days("Sunday"));*/
    }
    @PersistenceConstructor
   public User(String id, String fName, String lName, String username, String email, String password, Days[] days) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.days = days;
    }

    public void setDays(Days[] days) {
        this.days = days;
    }

    public Days[] getDays() {
        return days;
    }


    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
