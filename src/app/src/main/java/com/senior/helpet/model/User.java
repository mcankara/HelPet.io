package com.senior.helpet.model;

import java.util.ArrayList;

public class User {

    private String name;
    private String surname;
    private String email;
    private String userId;

    private ArrayList<Pet> pets;

    //private String imageURL;

    public User(String userId, String name, String surname, String email, ArrayList<Pet> pets ) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        pets = new ArrayList<Pet>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
