package com.youtubeloader.videoloader.web;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.Instant;

@Controller
@RequiredArgsConstructor
public class StreamInfoController {

    private final SimpMessagingTemplate template;
    private final SimpUserRegistry simpUserRegistry;

    @MessageMapping("/connect")
    @SendTo("/topic/connect")
    public String greeting(@Payload String name, SimpMessageHeaderAccessor headerAccessor) {
        return name + " entered the room";
    }

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public String message(String message, SimpMessageHeaderAccessor headerAccessor) {
        return "Message: " + message;
    }

    @Scheduled(fixedDelay = 5000)
    public void batchSend() {
        template.convertAndSend("/topic/message", Instant.now().toString());
    }

}
