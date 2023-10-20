package com.hacktivators.mentalhealth.Model;

public class Journal {

    private String id,journal,colorTxt,questionTxt;

    private boolean color,question;


    private long date;

    public Journal(String id, String journal, String colorTxt, String questionTxt, boolean color, boolean question, long date) {
        this.id = id;
        this.journal = journal;
        this.colorTxt = colorTxt;
        this.questionTxt = questionTxt;
        this.color = color;
        this.question = question;
        this.date = date;
    }

    public Journal() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getColorTxt() {
        return colorTxt;
    }

    public void setColorTxt(String colorTxt) {
        this.colorTxt = colorTxt;
    }

    public String getQuestionTxt() {
        return questionTxt;
    }

    public void setQuestionTxt(String questionTxt) {
        this.questionTxt = questionTxt;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public boolean isQuestion() {
        return question;
    }

    public void setQuestion(boolean question) {
        this.question = question;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
