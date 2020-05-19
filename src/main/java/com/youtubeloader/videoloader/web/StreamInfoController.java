package com.youtubeloader.videoloader.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StreamInfoController {

    @MessageMapping("/connect")
    @SendTo("/topic/connect")
    public String greeting(@Payload String name) {
        return name + " entered the room";
    }

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public String message(String message) {
        return "Message: " + message;
    }

}
