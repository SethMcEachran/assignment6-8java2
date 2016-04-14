/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;


/**
 *
 * @author Seth
 */
@ApplicationScoped
public class MessageController {
    //controls stuff

    List<Message> List;
 
    public MessageController() {
        this.List = new ArrayList<>();

    }

    public Message getMessageById(int id) {
 
   for (Message m : List) {
            if (m.getId() == id) {
                return (Message) m;
            }
        }
        return null;
     
    }

    public Message getMessageByDate(String start, String end){
      for (Message m : List) {
          try {                                             
              DateFormat df = new SimpleDateFormat("yyyy-MM-ddThh:mm:ss.SSSZ", Locale.ENGLISH);
              Date result =  df.parse(m.getSenttime());
              Date star = df.parse(start);
              Date en = df.parse(end);
              if (result.before(en)==true && (result.after(star) == true)) {
                  return (Message) m;
              }
          } catch (ParseException ex) {
              Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        return null;
     
    }
    

    public List<Message> getAll() {
     
       List<Message> result = new ArrayList<>();
       
          return result;
        
    }
    
    public void MessageAdd(Message msg){
            List.add(msg);
        
    }
    
    public void MessageEdit(int id, Message msg){
        int count=0;
    for(Message m : List){
        count++;
        if(m.getId() ==id){
          List.set(count, m);
        }
    }
    }
    
    public void MessageRemove(int id){
        int count=0;
        for(Message m : List){
        count++;
        if(m.getId() ==id){
          List.remove(count);
        }
    }
}
    public JsonArray controllerToJson(){
        JsonArrayBuilder object = Json.createArrayBuilder();
        for(Message m :  List){
         
         object.add(m.MessageToJson());
              
    }
       object.build();
       return object.build();
    }
}


