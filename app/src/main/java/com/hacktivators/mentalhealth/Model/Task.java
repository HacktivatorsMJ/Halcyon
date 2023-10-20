package com.hacktivators.mentalhealth.Model;

public class Task {

    private String title,description,id;

    String time,date;



    public Task(String title, String description,String time,String date,String id) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.date = date;
        this.id = id;
    }


    public Task() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
