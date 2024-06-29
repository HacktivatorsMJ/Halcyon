package com.hacktivators.mentalhealth.Model;

import java.io.Serializable;

public class User implements Serializable {

    private String id,username,email,gender,imageurl,age;
    private Integer depression_score,stress_score,sleep_score;

    public User(String id, String username, String email, String gender, String imageurl, String age, Integer depression_score, Integer stress_score,Integer sleep_score) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.imageurl = imageurl;
        this.age = age;
        this.depression_score = depression_score;
        this.stress_score = stress_score;
        this.sleep_score = sleep_score;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getDepression_score() {
        return depression_score;
    }

    public void setDepression_score(Integer depression_score) {
        this.depression_score = depression_score;
    }

    public Integer getStress_score() {
        return stress_score;
    }

    public void setStress_score(Integer stress_score) {
        this.stress_score = stress_score;
    }

    public Integer getSleep_score() {
        return sleep_score;
    }

    public void setSleep_score(Integer sleep_score) {
        this.sleep_score = sleep_score;
    }
}
