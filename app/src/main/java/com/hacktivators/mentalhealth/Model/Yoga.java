package com.hacktivators.mentalhealth.Model;

public class Yoga {

    private String id;
    private String plan;
    private String title;


    public Yoga(String id, String plan, String title) {
        this.id = id;
        this.plan = plan;
        this.title = title;
    }

    public Yoga() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
