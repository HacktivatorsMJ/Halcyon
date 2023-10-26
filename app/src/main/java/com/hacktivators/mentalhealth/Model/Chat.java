package com.hacktivators.mentalhealth.Model;

public class Chat {

    private String message;
    private String sentby;

    private long time;


    public Chat(String message, String sentby, long time) {
        this.message = message;
        this.sentby = sentby;
        this.time = time;
    }

    public Chat() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentby() {
        return sentby;
    }

    public void setSentby(String sentby) {
        this.sentby = sentby;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
