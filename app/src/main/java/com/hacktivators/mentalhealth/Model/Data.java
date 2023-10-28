package com.hacktivators.mentalhealth.Model;

public class Data {


    private String title;
    private String body;

    private String chatid;

    public Data(String title, String body, String chatid) {
        this.title = title;
        this.body = body;
        chatid = chatid;
    }

    public Data() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }
}
