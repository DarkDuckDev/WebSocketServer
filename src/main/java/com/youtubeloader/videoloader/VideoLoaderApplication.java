package com.youtubeloader.videoloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
//@EnableWebSocket
public class VideoLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoLoaderApplication.class, args);
	}

}
