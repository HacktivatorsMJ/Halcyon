package com.hacktivators.mentalhealth.Model;

public class ChatItem {

    private String createdby,id,statement,acceptedby;
    private boolean status;

    public ChatItem(String createdby, String id, String statement, boolean status,String acceptedby) {
        this.createdby = createdby;
        this.id = id;
        this.statement = statement;
        this.status = status;
        this.acceptedby = acceptedby;
    }

    public ChatItem() {
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getAcceptedby() {
        return acceptedby;
    }

    public void setAcceptedby(String acceptedby) {
        this.acceptedby = acceptedby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
