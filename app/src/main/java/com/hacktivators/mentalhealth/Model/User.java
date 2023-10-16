package com.hacktivators.mentalhealth.Model;

public class User {

    private String username,gender,dob;


    public User(String username, String gender, String dob) {
        this.username = username;
        this.gender = gender;
        this.dob = dob;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
