/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author Seth
 */
@ApplicationScoped
public class MessageController {
    //controls stuff

    private List<Message> List;
 
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

    public Message getMessageByDate(Date start, Date end){
      for (Message m : List) {
            if (m.getDay().before(end)==true && (m.getDay().after(start) == true)) {
                return (Message) m;
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
}


