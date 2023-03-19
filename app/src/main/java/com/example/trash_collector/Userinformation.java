package com.example.trash_collector;

public class Userinformation {
public String userKey;
    public String name;
    public String surname;
    public String phoneno;

    public Userinformation(){
    }

    public Userinformation(String name,String surname, String phoneno){
        this.name = name;
        this.surname = surname;
        this.phoneno = phoneno;
    }

    public Userinformation(String userKey, String name, String surname, String phoneno) {
        this.userKey = userKey;
        this.name = name;
        this.surname = surname;
        this.phoneno = phoneno;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserName() {
        return name;
    }
    public String getUserSurname() {
        return surname;
    }
    public String getUserPhoneno() {

        return phoneno;
    }
}

