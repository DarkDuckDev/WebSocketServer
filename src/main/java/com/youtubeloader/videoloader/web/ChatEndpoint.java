package com.youtubeloader.videoloader.web;


import com.youtubeloader.videoloader.web.dto.Message;
import lombok.Getter;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat/{username}", encoders = MessageEncoder.class, decoders = MessageDecoder.class)
@Getter
public class ChatEndpoint {

    private Session session;
    private static Set<ChatEndpoint> endpoints = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        this.session = session;
        endpoints.add(this);
        Message message = new Message();
        message.setFrom(username);
        message.setContent("joined the session");

        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        System.out.println("");
        // Handle new messages
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("");
        // WebSocket connection closes
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("");
        // Do error handling here
    }

    private static void broadcast(Message message) {
        endpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.getSession().getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
