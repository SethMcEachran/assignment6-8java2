/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.ws.rs.core.Response;


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
        List<Message> result = null;
        try {
     Connection conn = DBConnection.GetAConnection();
  PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Messages");
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            result.add( new Message(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("contents"),
                    rs.getString("author"),
                    rs.getString("senttime")
            ));
        }
        
       } catch (SQLException ex) {
                Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
          return result;
        
    }
    
    public void MessageAdd(Message msg){
            List.add(msg);
            try {
               Connection conn = DBConnection.GetAConnection();
               PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Messages (title, contents, author, senttime) VALUES (?,?,?,?);");
                pstmt.setString(1, msg.title);
                pstmt.setString(2, msg.contents);
                pstmt.setString(3, msg.author);
                pstmt.setString(4, msg.senttime);
         } catch (SQLException ex) {
                Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void MessageEdit(int id, Message msg){
        int count=0;
    for(Message m : List){
        count++;
        if(m.getId() ==id){
            try {
                List.set(count, m);
                Connection conn = DBConnection.GetAConnection();
                PreparedStatement pstmt = conn.prepareStatement("UPDATE Messages SET title = ?, contents = ?, author = ?, senttime = ?  WHERE id = ?");
                pstmt.setString(1, m.title);
                pstmt.setString(2, m.contents);
                pstmt.setString(3, m.author);
                pstmt.setString(4, m.senttime);
            } catch (SQLException ex) {
                Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    }
    
    public Response MessageRemove(int id){
        int count=0;
        for(Message m : List){
        count++;
        if(m.getId() ==id){
          List.remove(count);
           try {
              Connection conn = DBConnection.GetAConnection();
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM messages WHERE id = ?");
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
                return Response.status(500).entity("Database error").build();
            }
        }
    }
        return Response.status(200).entity("Succesful delete").build();
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


