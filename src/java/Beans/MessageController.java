/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Seth
 */
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
}


