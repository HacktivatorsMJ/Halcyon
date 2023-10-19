package com.hacktivators.mentalhealth.Model;

public class Doc {


    private String name,fees;

    private int exp;
    private long phone_no;


    public Doc(String name, long phone_no, String fees, int exp) {
        this.name = name;
        this.phone_no = phone_no;
        this.fees = fees;
        this.exp = exp;
    }

    public Doc() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(long phone_no) {
        this.phone_no = phone_no;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
