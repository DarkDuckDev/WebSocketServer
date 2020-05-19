package com.youtubeloader.videoloader.config;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthChannelInterceptorHandler implements ChannelInterceptor {

    private static final String USERNAME_HEADER = "login";
    private static final String PASSWORD_HEADER = "passcode";
    private final WebSocketAuthenticationService webSocketAuthenticatorService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return message;
    }
}
