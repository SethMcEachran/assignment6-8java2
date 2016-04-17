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
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
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

    /**
     *
     * @param start
     * @param end
     * @return
     */
    public Message getMessageByDate(String start, String end) {
        for (Message m : List) {
            try {
                //T throws an error
               // DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ", Locale.ENGLISH);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date result = df.parse(m.getSenttime());
                Date star = df.parse(start);
                Date en = df.parse(end);
                if (result.before(en) == true && (result.after(star) == true)) {
                     ArrayList<Message> day  = new ArrayList<>();
               
                       
                     
                    
                   
                    return m;
                }
            } catch (ParseException ex) {
                Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
        
    }

    /**
     *
     * @return
     */
    public List<Message> getAll() {
        this.List = new ArrayList<>();
        try {
            Connection conn = DBConnection.GetAConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT id ,title, contents, author, senttime FROM Messages");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Message mess = new Message(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("author"),
                        rs.getString("senttime")
                );
//                System.out.println(mess.MessageToJson().toString());
                List.add(mess);
//                conn.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return List;
        
    }

    /**
     *
     * @param msg
     */
    public void MessageAdd(Message msg) {
        
        try {
            Connection conn = DBConnection.GetAConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Messages (title, contents, author, senttime) VALUES (?,?,?,?);",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, msg.title);
            pstmt.setString(2, msg.contents);
            pstmt.setString(3, msg.author);
            pstmt.setString(4, msg.senttime);
            int rows = pstmt.executeUpdate();
            if (rows != 0) {
                try (ResultSet key = pstmt.getGeneratedKeys()) {
                    if (key.next()) {
                        int newId = key.getInt(1);
                        msg.setId(newId);
                        List.add(msg);
//                        return newId;
                    }
                }
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return 0;
    }

    /**
     *
     * @param id
     * @param msg
     */
    public void MessageEdit(int id, Message msg) {
        int count = 0;
        for (Message m : List) {
         
            if (m.getId() == id) {
               System.out.println("db call");
                try {
                    List.set(count, msg);
                    Connection conn = DBConnection.GetAConnection();
                    PreparedStatement pstmt = conn.prepareStatement("UPDATE Messages SET title = ?, contents = ?, author = ?, senttime = ?  WHERE id = ?",Statement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, msg.title);
                    pstmt.setString(2, msg.contents);
                    pstmt.setString(3, msg.author);
                    pstmt.setString(4, msg.senttime);
                    pstmt.setInt(5, id);
//                    pstmt.execute();
                
                     int rows = pstmt.executeUpdate();
            if (rows != 0) {
  
     
                            System.out.println("db call2");
//                        return newId;
            
            }
                    conn.close();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
               count++;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Response MessageRemove(int id) {
        System.out.println(id);
        int count = 0;
        for (Message m : List) {
            count++;
            if (m.getId() == id) {
                
                List.remove(count);
                try {
                    Connection conn = DBConnection.GetAConnection();
                    PreparedStatement pstmt = conn.prepareStatement("DELETE FROM messages WHERE id = ?");
                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                    conn.close();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
                    return Response.status(500).entity("Database error").build();
                }
            }
        }
        return Response.status(200).entity("Succesful delete").build();
    }

    /**
     *
     *
     * @param mess
     * @return
     */
    public JsonArray controllerToJson(List<Message> mess) {
        JsonArrayBuilder object = Json.createArrayBuilder();
        for (Message m : mess) {
            
            object.add(m.MessageToJson());
            
        }
        
        return object.build();
    }
    
    Object controllerToJson() {
        JsonArrayBuilder object = Json.createArrayBuilder();
        for (Message m : List) {
//            System.out.println(m.MessageToJson().toString());
            object.add(m.MessageToJson());
        }
        
        return object.build();
    }
    
}
