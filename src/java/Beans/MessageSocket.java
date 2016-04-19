/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.core.Response;

/**
 *
 * @author Seth
 */
@ServerEndpoint("/MessageSocket")
public class MessageSocket {
       @Inject
    private MessageController controller;
       
       @Inject
       private MessageService service;
       
   @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        System.out.println(message);
        JsonObject json = Json.createReader(new StringReader(message)).readObject();

        RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (json.containsKey("getAll")) {

            boolean isGetAll = json.getBoolean("getAll");
            if (isGetAll) {
                List<Message> allMessages = controller.getAll();
                if (allMessages != null) {
                    basic.sendText(controller.controllerToJson().toString());
                } else {
                    basic.sendText("{ 'error' : 'could not get all messages.' }");
                }
            } else {
                basic.sendText("{ 'error' : 'could not get all messages.' }");
            }

        } else if (json.containsKey("getById")) {

            int id = json.getInt("getById");
            Message msg = controller.getMessageById(id);
            if (msg != null) {
                basic.sendText(msg.MessageToJson().toString());
            } else {
                basic.sendText("{ 'error' : 'id does not appear to exist.' }");
            }

        } else if (json.containsKey("daySorter")) {

            JsonArray array = json.getJsonArray("daySorter");
            String star = array.getJsonString(0).getString();
            String en = array.getJsonString(1).getString();

            MessageController messagesbydate = (MessageController) controller.getMessageByDate(star, en);
            if (messagesbydate != null) {
                basic.sendText(messagesbydate.controllerToJson().toString());
            } else {
                basic.sendText("{ 'error' : 'Failed to get messages in that date range.' }");
            }

        } else if (json.containsKey("post")) {

            String str = json.getJsonObject("post").toString();
            Response newMessage = service.NewMessage(str);
            if (newMessage != null) {
                basic.sendText(newMessage.toString());
            } else {
                basic.sendText("{ 'error' : 'Message not added.' }");
            }

        } else if (json.containsKey("put")) {

            int id = json.getJsonObject("put").getInt("id");
            String str = json.getJsonObject("put").toString();
            Response updatedMessage = service.editMessage(id, str);
            if (updatedMessage != null) {
                basic.sendText("{ 'ok' : Message is updated }");
            } else {
                basic.sendText("{ 'error' : 'Message not edited.' }");
            }

        } else if (json.containsKey("delete")) {

            int id = json.getInt("delete");
          
            controller.MessageRemove(id);
            
            basic.sendText("{ 'error' : 'Could not remove that message or does not exist.' }");
        } else {

            basic.sendText("{ 'error' : 'did not call a method.' }");

        }

    }
       
    
}
