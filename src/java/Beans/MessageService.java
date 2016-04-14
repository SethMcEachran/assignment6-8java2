/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.StringReader;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;





/**
 *
 * @author c0611751
 */
@Path("MessageService")
@ApplicationScoped
public class MessageService {

    @Inject
    private MessageController controller;

  @GET
  @Produces("application/json")
  public Response GetAll(){ 
    controller.getAll();
        return Response.ok(controller.controllerToJson()).build();
      
  }
  
  @GET
  @Path("{id}")
  @Produces("application/json")
  public Response GetById(@PathParam("id") int id){
      controller.getMessageById(id);
      
       return Response.ok(controller.controllerToJson()).build();
  }
      
  @GET
  @Path("{start}/{end}")
  @Produces
  public Response GetByDate(@PathParam("start") String start, @PathParam("end") String end){
      controller.getMessageByDate(start, end);
       return Response.ok(controller.controllerToJson()).build();
      
  }
  
  @POST
   @Consumes("application/json")
   @Produces("application/json")
  public Response NewMessage(String str ){
       Message newMessage;
       JsonObject json = Json.createReader(new StringReader(str)).readObject();
       
       newMessage = new Message();
       newMessage.setId(json.getInt("id"));
       newMessage.setAuthor(json.getString("author"));
       newMessage.setContents(json.getString("contents"));
       newMessage.setTitle(json.getString("title"));
       newMessage.setSenttime(json.getString("senttime")); 
       controller.MessageAdd(newMessage);
       return Response.ok(newMessage).build();
        
  }
  
  @PUT
  @Path("{id}")
  public Response editMessage(@PathParam("id") int id, String str){
      
        Message newMessage;
       JsonObject json = Json.createReader(new StringReader(str)).readObject();
       
       newMessage = new Message();
       newMessage.setId(json.getInt("id"));
       newMessage.setAuthor(json.getString("author"));
       newMessage.setContents(json.getString("contents"));
       newMessage.setTitle(json.getString("title"));
       newMessage.setSenttime(json.getString("senttime")); 
       int count = 0;
       for(int i = 0; i < controller.getAll().size(); i++ ){
           if (controller.getAll().get(i).getId() == id) {
                 controller.MessageEdit(id ,newMessage);
               return Response.ok(controller.controllerToJson()).build();
       }
      
      
      
  }
   return Response.ok(newMessage).build();
} 
  
  @DELETE
  @Path("{id}")
  public Response DeleteMessage(@PathParam("{id}") int id){
       int count = 0;
       for(int i = 0; i < controller.getAll().size(); i++ ){
           if (controller.getAll().get(i).getId() == id) {
                 controller.MessageRemove(id);
           }}
    return Response.ok().build();
  }
 }

  