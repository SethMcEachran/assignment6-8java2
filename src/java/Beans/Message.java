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
    int id;
    String title; 

    String contents;
    String author;
    String senttime;
/**
 * 
 * @param id
 * @param title
 * @param contents
 * @param author
 * @param senttime 
 */
    public Message(int id, String title, String contents, String author, String senttime) {
        this.id = id;
        this.title = title;
        
        this.contents = contents;
        this.author = author;
        this.senttime = senttime;
    }
/**
 * 
 */
    public Message() {
    }
/**
 * 
 * @return 
 */
    public int getId() {
        return id;
    }
/**
 * 
 * @param id 
 */
    public void setId(int id) {
        this.id = id;
    }
/**
 * 
 * @return 
 */
    public String getTitle() {
        return title;
    }
/**
 * 
 * @param title 
 */
    public void setTitle(String title) {
        this.title = title;
    }

  /**
   * 
   * @return 
   */ 
    public String getContents() {
        return contents;
    }
/**
 * 
 * @param contents 
 */
    public void setContents(String contents) {
        this.contents = contents;
    }
/**
 * 
 * @return 
 */
    public String getAuthor() {
        return author;
    }
/**
 * 
 * @param author 
 */
    public void setAuthor(String author) {
        this.author = author;
    }
/**
 * 
 * @return 
 */
    public String getSenttime() {
        return senttime;
    }
/**
 * 
 * @param senttime 
 */
    public void setSenttime(String senttime) {
        this.senttime = senttime;
    }
/**
 * 
 * @return 
 */
    @Override
    public String toString() {
        return "{ \"id\" : "+id+", \"title\" : \""+title+"\", \"contents\" : \""+contents+"\", \"author\" : \""+author+"\", \"senttime\" : \""+senttime+"\" }";
    }
  /**
   * 
   * @return 
   */  
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
            
    
    
    
    
    

