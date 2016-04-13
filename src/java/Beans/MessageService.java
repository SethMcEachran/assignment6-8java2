/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Beans.MessageController;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;





/**
 *
 * @author c0611751
 */
 @Path("MessageService")
@ApplicationScoped
public class MessageService {

   //  @Inject
   //  private MessageController controller;

  @GET
  @Produces("application/json")
  public Response GetAll(){ 
     // JsonArrayBuilder json = Json.createArrayBuilder();
     // for (Message message: MessageController.getAll()) {
      //      json.add(message);
      
     return Response.status(404).entity("Hello").build();
  }
      
}  

 