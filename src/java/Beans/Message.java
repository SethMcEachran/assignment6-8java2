/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;



import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author c0611751
 */
public class Message {
   // { "id" : 1, "title" : "Sample Title", "contents" : "Some sample contents for the message.", "author" : "A. Sample", "senttime" : "2016-03-31T13:24:11.135Z" }
    private int id;
    String title; 

    String contents;
    String author;
    String senttime;

    public Message(int id, String title, String contents, String author, String senttime) {
        this.id = id;
        this.title = title;
        
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

    public String getSenttime() {
        return senttime;
    }

    public void setSenttime(String senttime) {
        this.senttime = senttime;
    }

    @Override
    public String toString() {
        return "{ \"id\" : "+id+", \"title\" : \""+title+"\", \"contents\" : \""+contents+"\", \"author\" : \""+author+"\", \"senttime\" : \""+senttime+"\" }";
    }
    
    public JsonObject MessageToJson(){
        JsonObject object = Json.createObjectBuilder()
        .add("id",id)
        .add("title",title)
        .add("contents",contents)
        .add("author", author)
        .add("senttime", senttime)
        .build();
        return object;
    }
}
            
    
    
    
    
    

