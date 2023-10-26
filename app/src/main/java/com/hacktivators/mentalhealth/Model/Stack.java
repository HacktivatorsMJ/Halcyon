package com.hacktivators.mentalhealth.Model;

public class Stack {

    String statement,createdby,id;

    boolean status;

    public Stack(String statement, String createdby,String id,boolean status) {
        this.statement = statement;
        this.createdby = createdby;
        this.id = id;
        this.status = status;
    }

    public Stack() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }
}
