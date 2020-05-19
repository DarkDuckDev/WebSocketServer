package com.youtubeloader.videoloader.config;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class WebSocketAuthenticationService {


    public UsernamePasswordAuthenticationToken getAuthenticationOrFail(final String  username, final String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Username was null or empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Password was null or empty.");
        }
//        // Add your own logic for retrieving user in fetchUserFromDb()
//        if (fetchUserFromDb(username, password) == null) {
//            throw new BadCredentialsException("Bad credentials for user " + username);
//        }

        // null credentials, we do not pass the password along
        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                Collections.singleton((GrantedAuthority) () -> "USER") // MUST provide at least one role
        );
    }

}
