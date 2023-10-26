package com.hacktivators.mentalhealth.Model;

public class Stack {

    String statement,createdby,id;

    public Stack(String statement, String createdby,String id) {
        this.statement = statement;
        this.createdby = createdby;
        this.id = id;
    }

    public Stack() {
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
