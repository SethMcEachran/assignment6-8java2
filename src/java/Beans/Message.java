/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author c0611751
 */
public class Message {
   // { "id" : 1, "title" : "Sample Title", "contents" : "Some sample contents for the message.", "author" : "A. Sample", "senttime" : "2016-03-31T13:24:11.135Z" }
    private int id;
    private String title; 
    private Date day;
    private String contents;
    private String author;
    private Time senttime;

    public Message(int id, String title, Date day, String contents, String author, Time senttime) {
        this.id = id;
        this.title = title;
        this.day = day;
        this.contents = contents;
        this.author = author;
        this.senttime = senttime;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Time getSenttime() {
        return senttime;
    }

    public void setSenttime(Time senttime) {
        this.senttime = senttime;
    }

    @Override
    public String toString() {
        return "{ \"id\" : "+id+", \"title\" : \""+title+"\", \"contents\" : \""+contents+"\", \"author\" : \""+author+"\", \"senttime\" : \""+senttime+"\" }";
    }
    
    
    
    
    
    
    
}
